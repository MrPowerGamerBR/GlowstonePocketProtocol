package net.pocketdreams.sequinland.glowstone.net;

import java.net.InetSocketAddress;
import com.flowpowered.network.ConnectionManager;
import com.flowpowered.network.Message;

import io.netty.channel.Channel;
import net.glowstone.GlowServer;
import net.glowstone.entity.meta.profile.PlayerProfile;
import net.glowstone.net.GlowSession;
import net.glowstone.net.message.SetCompressionMessage;
import net.glowstone.net.pipeline.CodecsHandler;
import net.glowstone.net.pipeline.CompressionHandler;
import net.glowstone.net.protocol.GlowProtocol;
import net.glowstone.net.protocol.ProtocolType;
import net.marfgamer.jraknet.session.RakNetClientSession;
import net.pocketdreams.sequinland.glowstone.net.message.GamePacket;
import net.pocketdreams.sequinland.glowstone.net.message.PlayStatusPacket;
import net.pocketdreams.sequinland.glowstone.net.message.ResourcePacksInfoPacket;

/**
 * A single pocket connection to the server
 */
public class GlowPocketSession extends GlowSession {
    private RakNetClientSession session;

    public GlowPocketSession(GlowServer server, Channel channel, ConnectionManager connectionManager) {
        super(server, channel, connectionManager);
        this.setProtocol(ProtocolType.POCKET_EDITION.getProtocol());
    }
    
    // I know this is not as good as making a constructor argument but it will work
    protected void setRakNetClientSession(RakNetClientSession session){
    	this.session = session;
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
        this.send(new PlayStatusPacket(PlayStatusPacket.OK).andEncode());
        this.send(new ResourcePacksInfoPacket().andEncode());
    }

    @Override
    public InetSocketAddress getAddress() {
        return session.getAddress();
    }

    public RakNetClientSession getRakNetSession() {
        return session;
    }

    @Override
    public void send(Message message) {
        // TODO: Better message translator, come on, look at this mess!
        // TODO: This is only kept to translate PC -> PE packets, sending packets
        // should be handled in the GlowPlayer class!
        
        // So, this means that this is only kept for plugins that are sending packets
        // (ProtocolLib)
        System.out.println("Sending " + message.getClass().getSimpleName() + " to client... (Not)!");
        return;
    }
    
    //TODO: Remove these and finish the Message based packet system
    public void sendAllPocket(GamePacket[] array) {
        for (GamePacket packet : array) {
            sendPocket(packet);
        }
    }

    public void sendPocket(GamePacket packet) {
        session.sendMessage(packet.reliability, packet);
    }
}
