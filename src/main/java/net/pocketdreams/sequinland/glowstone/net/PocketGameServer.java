package net.pocketdreams.sequinland.glowstone.net;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.UUID;

import com.flowpowered.network.ConnectionManager;
import com.flowpowered.network.Message;
import com.flowpowered.network.session.Session;

import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.concurrent.EventExecutorGroup;
import net.glowstone.GlowServer;
import net.glowstone.net.GlowSession;
import net.glowstone.net.handler.legacyping.LegacyPingHandler;
import net.glowstone.net.pipeline.CodecsHandler;
import net.glowstone.net.pipeline.FramingHandler;
import net.glowstone.net.pipeline.MessageHandler;
import net.glowstone.net.pipeline.NoopHandler;
import net.glowstone.net.protocol.ProtocolType;
import net.marfgamer.jraknet.Packet;
import net.marfgamer.jraknet.RakNetPacket;
import net.marfgamer.jraknet.identifier.MCPEIdentifier;
import net.marfgamer.jraknet.server.RakNetServer;
import net.marfgamer.jraknet.session.RakNetClientSession;
import net.pocketdreams.sequinland.glowstone.net.message.BatchPacket;
import net.pocketdreams.sequinland.glowstone.net.message.GamePacket;
import net.pocketdreams.sequinland.glowstone.net.message.MovePlayerPacket;
import net.pocketdreams.sequinland.glowstone.net.protocol.PocketProtocol;
import net.pocketdreams.sequinland.network.translator.PEToPCTranslator;
import net.pocketdreams.sequinland.network.translator.TranslatorRegistry;
import net.pocketdreams.sequinland.network.translator.pocket.PEBatchPacketTranslator;
import net.pocketdreams.sequinland.network.translator.pocket.PEMovePlayerPacketTranslator;
import net.pocketdreams.sequinland.util.ReflectionUtils;

public class PocketGameServer extends RakNetServer implements ConnectionManager {
    private final GlowServer server;
    private final EventExecutorGroup group;
        
    //TODO: Replace this with a RakNet Session UUID to GlowSession UUID mapping 
    static HashMap<RakNetClientSession, GlowPocketSession> sessions = new HashMap<>();
    
    public PocketGameServer(int port, int maxConnections, GlowServer server) {
    	this(port, maxConnections, server, 1492);
    }

    public PocketGameServer(int port, int maxConnections, GlowServer server, int mtu) {
        super(port, maxConnections, mtu, new MCPEIdentifier(server.getMotd(), PocketProtocol.CURRENT_PROTOCOL, PocketProtocol.MINECRAFT_VERSION_NETWORK, server.getOnlinePlayers().size(),
                server.getMaxPlayers(), UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, "New World", "Survival"));
        this.server = server;
        
        EventExecutorGroup group = null;
        try {
            Field f = ReflectionUtils.findUnderlying(getClass(), "group");
            f.setAccessible(true);
            group = (EventExecutorGroup) f.get(this);
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        this.group = group;
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
        
        
        
        GlowPocketSession pocketSession = (GlowPocketSession) newSession(channel); // This is for the sake of implementing ConnectionManager correctly
        pocketSession.setRakNetClientSession(session);
        
        // Copied and modified from GlowChannelInitializer
        int READ_TIMEOUT = 20;
        int WRITE_IDLE_TIMEOUT = 15;

        MessageHandler handler = new MessageHandler(this);
        CodecsHandler codecs = new CodecsHandler(ProtocolType.POCKET_EDITION.getProtocol());
        FramingHandler framing = new FramingHandler();

        try {
            channel.config().setOption(ChannelOption.IP_TOS, 0x18);
        } catch (ChannelException e) {
            // Not supported on all OSs, like Windows XP and lesser
            GlowServer.logger.warning("Your OS does not support type of service.");
        }
        channel.config().setAllocator(PooledByteBufAllocator.DEFAULT);

        channel.pipeline()
                .addLast(group, "encryption", NoopHandler.INSTANCE)
                .addLast(group, "framing", framing)
                .addLast(group, "compression", NoopHandler.INSTANCE)
                .addLast(group, "codecs", codecs)
                .addLast(group, "readtimeout", new ReadTimeoutHandler(READ_TIMEOUT))
                .addLast(group, "writeidletimeout", new IdleStateHandler(0, WRITE_IDLE_TIMEOUT, 0))
                .addLast(group, "handler", handler);
        // ====================
        
        sessions.put(session, pocketSession);
    }
    
    @Override
    public void onClientDisconnect(RakNetClientSession session, String reason) {
        GlowPocketSession pocketSession = sessions.get(session);
        sessionInactivated(pocketSession); // This is also for the sake of implementing ConnectionManager correctly
        sessions.remove(pocketSession);
    }
    
    @Override
    public void handleMessage(RakNetClientSession session, RakNetPacket packet, int channel) {
        if (packet.getId() == 0xFE) { // Pocket protocol packet
            handleRakNetPacket(session, packet);
        }
    }
    
    public static void handleRakNetPacket(RakNetClientSession session, RakNetPacket packet) {
        short id = packet.readUByte();
        
        Class<? extends GamePacket> clazz = PocketProtocol.getPacketById(id);
        
        if (!(id == 6) && !(id == 20)) {
            System.out.println("Packet ID: " + id);
        }
        
        if (clazz != null) {
            if (!(clazz == BatchPacket.class) && !(clazz == MovePlayerPacket.class)) {
                System.out.println("Recieved packet: " + clazz.getSimpleName());
            }
            try {
                GamePacket pocketPacket = clazz.getDeclaredConstructor(Packet.class).newInstance(packet);
                
                pocketPacket.decode();
                
                GlowPocketSession pocketSession = sessions.get(session);
                
                /*PEToPCTranslator<GamePacket> translator = TranslatorRegistry.PE_TO_PC_TRANSLATORS.get(pocketPacket.getClass());
                
                if (translator != null) {
                    if (!(translator.getClass() == PEBatchPacketTranslator.class) && !(translator.getClass() == PEMovePlayerPacketTranslator.class)) {
                        System.out.println("Using translator " + translator.getClass().getSimpleName());
                    }
                    Message[] messages = translator.translate(pocketSession, pocketPacket);
                    for (Message message : messages) {
                        pocketSession.messageReceived(message);
                    }
                    return;
                } else {
                    System.out.println("No valid translator found for " + pocketPacket.getClass().getSimpleName());
                }*/
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void onSessionException(RakNetClientSession session, Throwable throwable) {
        throwable.printStackTrace();
    }
    
    @Override
    public void onAddressBlocked(InetAddress address, long time) {
        System.out.println("Client " + address.getHostName() + " blocked for " + time);
    }

	@Override
	public Session newSession(Channel channel) {
        GlowPocketSession pocketSession = new GlowPocketSession(server, channel, server.pocketNetworkServer);
        server.getSessionRegistry().add(pocketSession);
        return pocketSession;
	}

	@Override
	public void sessionInactivated(Session pocketSession) {
        server.getSessionRegistry().remove((GlowSession) pocketSession);
	}
}
