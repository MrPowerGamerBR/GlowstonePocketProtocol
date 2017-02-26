package net.pocketdreams.sequinland.network;

import java.net.InetSocketAddress;
import com.flowpowered.network.ConnectionManager;
import com.flowpowered.network.Message;

import io.netty.channel.Channel;
import net.glowstone.GlowServer;
import net.glowstone.entity.meta.profile.PlayerProfile;
import net.glowstone.net.GlowSession;
import net.glowstone.net.message.SetCompressionMessage;
import net.glowstone.net.protocol.GlowProtocol;
import net.glowstone.net.protocol.ProtocolType;
import net.marfgamer.jraknet.session.RakNetClientSession;
import net.pocketdreams.sequinland.network.protocol.packets.GamePacket;
import net.pocketdreams.sequinland.network.protocol.packets.PlayStatusPacket;
import net.pocketdreams.sequinland.network.protocol.packets.ResourcePacksInfoPacket;

/**
 * A single pocket connection to the server
 */
public class PocketSession extends GlowSession {
    private RakNetClientSession session;

    public PocketSession(GlowServer server, Channel channel, ConnectionManager connectionManager, RakNetClientSession session) {
        super(server, channel, connectionManager);
        this.session = session;
    }
    
    @Override
    public void send(Message message) {
        // TODO: Better message translator, come on, look at this mess!
        // TODO: This is only kept to translate PC -> PE packets, sending packets
        // should be handled in the GlowPlayer class!
        
        // So, this means that this is only kept for plugins that are sending packets
        // (ProtocolLib)
        System.out.println("Sending " + message.getClass().getSimpleName() + " to client!");
        return;
    }

    @Override
    public void messageReceived(Message message) {
        super.messageReceived(message);
    }

    @Override
    public void setProtocol(ProtocolType protocol) {
        getChannel().flush();

        GlowProtocol proto = protocol.getProtocol();
        // updatePipeline("codecs", new CodecsHandler(proto));
        setProtocol(proto);
    }

    @Override
    public InetSocketAddress getAddress() {
        return session.getAddress();
    }

    @Override
    public void enableCompression(int threshold) {
        // set compression can only be sent once
        if (!compresssionSent) {
            send(new SetCompressionMessage(threshold));
            // updatePipeline("compression", new CompressionHandler(threshold));
            compresssionSent = true;
        }
    }
    
    public RakNetClientSession getRakNetSession() {
        return session;
    }

    public void sendPocket(GamePacket packet) {
        session.sendMessage(packet.reliability, packet);
    }
    
    public void sendAllPocket(GamePacket[] array) {
        for (GamePacket packet : array) {
            sendPocket(packet);
        }
    }
    
    @Override
    protected void finalizeLogin(PlayerProfile profile) {
        // enable compression if needed
        /* int compression = getServer().getCompressionThreshold();
        if (compression > 0) {
            enableCompression(compression);
        } */

        // send login response
        // send(new LoginSuccessMessage(profile.getUniqueId().toString(), profile.getName()));
        this.sendPocket(new PlayStatusPacket(PlayStatusPacket.OK).andEncode());
        this.sendPocket(new ResourcePacksInfoPacket().andEncode());
        setProtocol(ProtocolType.PLAY);
    }
}
