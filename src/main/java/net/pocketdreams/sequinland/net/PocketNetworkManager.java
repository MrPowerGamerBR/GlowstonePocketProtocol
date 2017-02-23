package net.pocketdreams.sequinland.net;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
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
import net.marfgamer.jraknet.server.RakNetServer;
import net.marfgamer.jraknet.session.RakNetClientSession;
import net.pocketdreams.sequinland.net.protocol.ProtocolInfo;
import net.pocketdreams.sequinland.net.protocol.packets.BatchPacket;
import net.pocketdreams.sequinland.net.protocol.packets.GamePacket;
import net.pocketdreams.sequinland.net.protocol.packets.LoginPacket;
import net.pocketdreams.sequinland.net.protocol.packets.TextPacket;
import net.pocketdreams.sequinland.util.ReflectionUtils;
import net.pocketdreams.sequinland.util.SequinUtils;

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
                    IncomingChatMessage pcPacket = new IncomingChatMessage(pePacket.message);
                    pocketSession.messageReceived(pcPacket);
                    return;
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
}
