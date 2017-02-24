package net.pocketdreams.sequinland.net;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import io.netty.channel.Channel;
import net.glowstone.GlowServer;
import net.glowstone.net.message.handshake.HandshakeMessage;
import net.glowstone.net.message.login.LoginStartMessage;
import net.glowstone.net.message.play.game.ChatMessage;
import net.glowstone.net.message.play.game.IncomingChatMessage;
import net.marfgamer.jraknet.Packet;
import net.marfgamer.jraknet.RakNetPacket;
import net.marfgamer.jraknet.client.RakNetClient;
import net.marfgamer.jraknet.identifier.MCPEIdentifier;
import net.marfgamer.jraknet.protocol.Reliability;
import net.marfgamer.jraknet.server.RakNetServer;
import net.marfgamer.jraknet.session.RakNetClientSession;
import net.pocketdreams.sequinland.net.protocol.ProtocolInfo;
import net.pocketdreams.sequinland.net.protocol.packets.BatchPacket;
import net.pocketdreams.sequinland.net.protocol.packets.ChunkRadiusUpdatedPacket;
import net.pocketdreams.sequinland.net.protocol.packets.GamePacket;
import net.pocketdreams.sequinland.net.protocol.packets.LoginPacket;
import net.pocketdreams.sequinland.net.protocol.packets.RequestChunkRadiusPacket;
import net.pocketdreams.sequinland.net.protocol.packets.TextPacket;
import net.pocketdreams.sequinland.util.ReflectionUtils;
import net.pocketdreams.sequinland.util.SequinUtils;
import net.pocketdreams.sequinland.util.nukkit.BinaryStream;

public class PocketNetworkManager extends RakNetServer {
    private final GlowServer server;
        
    static HashMap<RakNetClientSession, PocketSession> sessions = new HashMap<>();
    
    public PocketNetworkManager(int port, int maxConnections, GlowServer server) {
        super(port, maxConnections, new MCPEIdentifier(server.getMotd(), ProtocolInfo.CURRENT_PROTOCOL, ProtocolInfo.MINECRAFT_VERSION_NETWORK, server.getOnlinePlayers().size(),
                server.getMaxPlayers(), UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, "New World", "Survival"));
        this.server = server;
    }
    
    @Override
    public void onClientConnect(RakNetClientSession session) {
        // Reflection power!
        // TODO: Fork JRakNet so we can stop using reflection... or ask @MarfGamer to create a getter
        Channel channel = null;
        try {
            Field f = ReflectionUtils.findUnderlying(session.getClass(), "channel");
            f.setAccessible(true);
            channel = (Channel) f.get(session);
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        PocketSession pocketSession = new PocketSession(server, channel, server.networkServer, session);
        server.getSessionRegistry().add(pocketSession);
        sessions.put(session, pocketSession);
    }
    
    @Override
    public void handleMessage(RakNetClientSession session, RakNetPacket packet, int channel) {
        if (packet.getId() == 0xFE) { // Pocket protocol packet
            handleRakNetPacket(session, packet);
        }
    }
    
    @Override
    public void onClientDisconnect(RakNetClientSession session, String reason) {
        PocketSession pocketSession = sessions.get(session);
        pocketSession.disconnect(reason);
        sessions.remove(pocketSession);
    }
    
    public static void handleRakNetPacket(RakNetClientSession session, RakNetPacket packet) {
        short id = packet.readUByte();
        
        Class<? extends GamePacket> clazz = ProtocolInfo.getPacketById(id);
        
        System.out.println("Packet ID: " + id);
        
        if (clazz != null) {
            System.out.println("Recieved packet: " + clazz.getSimpleName());
            try {
                GamePacket pocketPacket = clazz.getDeclaredConstructor(Packet.class).newInstance(packet);
                
                pocketPacket.decode();
                
                PocketSession pocketSession = sessions.get(session);
                
                // TODO: Better translator system
                if (pocketPacket instanceof BatchPacket) {
                    SequinUtils.processBatch((BatchPacket) pocketPacket, session);
                    return;
                }
                if (pocketPacket instanceof LoginPacket) {
                    // TODO: Decode the login chain data
                    HandshakeMessage handshake = new HandshakeMessage(316, "127.0.0.1", 25565, 2);
                    pocketSession.messageReceived(handshake);
                    
                    LoginStartMessage login = new LoginStartMessage("Shantae");
                    pocketSession.messageReceived(login);
                    return;
                }
                if (pocketPacket instanceof TextPacket) {
                    TextPacket pePacket = (TextPacket) pocketPacket;
                    if (pePacket.message.startsWith("!")) {
                        // Remember the super workaround from the pre-0.16 days?
                        // Well, it is back since I'm dumb and I'm lazy to properly implement the available commands packet!
                        pePacket.message = pePacket.message.replaceFirst("!", "/");
                    }
                    IncomingChatMessage pcPacket = new IncomingChatMessage(pePacket.message);
                    pocketSession.messageReceived(pcPacket);
                    return;
                }
                if (pocketPacket instanceof RequestChunkRadiusPacket) {
                    ChunkRadiusUpdatedPacket pePacket = new ChunkRadiusUpdatedPacket();
                    pePacket.radius = 5;
                    session.sendMessage(Reliability.RELIABLE_ORDERED, pePacket);
                }
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void onSessionException(RakNetClientSession session, Throwable throwable) {
        throwable.printStackTrace();
    }

    public static byte[] getBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(10240);
        byte[] blocks = new byte[4096];
        byte[] data = new byte[2048];
        byte[] skyLight = new byte[2048];
        byte[] blockLight = new byte[2048];
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int i = (x << 7) | (z << 3);
                for (int y = 0; y < 16; y += 2) {
                    int id = 2;
                    int id2 = 2;
                    blocks[(i << 1) | y] = (byte) id;
                    blocks[(i << 1) | (y + 1)] = (byte) id2;
                    int b1 = 0;
                    int b2 = 0;
                    data[i | (y >> 1)] = (byte) ((b2 << 4) | b1);
                    b1 = 15;
                    b2 = 15;
                    skyLight[i | (y >> 1)] = (byte) ((b2 << 4) | b1);
                    b1 = 15;
                    b2 = 15;
                    blockLight[i | (y >> 1)] = (byte) ((b2 << 4) | b1);
                }
            }
        }
        return buffer
                .put(blocks)
                .put(data)
                .put(skyLight)
                .put(blockLight)
                .array();
    }
    
    public static byte[] requestChunkTask(int x, int z) {
        System.out.println("Chunk requested!");
        byte[] blockEntities = new byte[0];

        Map<Integer, Integer> extra = new HashMap<>();
        BinaryStream extraData;
        if (!extra.isEmpty()) {
            extraData = new BinaryStream();
            extraData.putVarInt(extra.size());
            for (Map.Entry<Integer, Integer> entry : extra.entrySet()) {
                extraData.putVarInt(entry.getKey());
                extraData.putLShort(entry.getValue());
            }
        } else {
            extraData = null;
        }

        BinaryStream stream = new BinaryStream();
        int count = 16;
        stream.putByte((byte) count);
        for (int i = 0; i < count; i++) {
            stream.putByte((byte) 0);
            stream.put(getBytes());
        }
        for (int height : new byte[256]) {
            stream.putByte((byte) height);
        }
        stream.put(new byte[256]);
        stream.put(new byte[256]);
        stream.putByte((byte) 0);
        if (extraData != null) {
            stream.put(extraData.getBuffer());
        } else {
            stream.putVarInt(0);
        }
        stream.put(blockEntities);

        return stream.getBuffer();
    }
}
