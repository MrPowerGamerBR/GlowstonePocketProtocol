package net.pocketdreams.sequinland.net;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import com.flowpowered.network.ConnectionManager;
import com.flowpowered.network.Message;

import io.netty.channel.Channel;
import net.glowstone.GlowServer;
import net.glowstone.net.GlowSession;
import net.glowstone.net.message.SetCompressionMessage;
import net.glowstone.net.message.login.LoginSuccessMessage;
import net.glowstone.net.message.play.game.ChatMessage;
import net.glowstone.net.message.play.game.ChunkDataMessage;
import net.glowstone.net.message.play.game.JoinGameMessage;
import net.glowstone.net.message.play.game.PositionRotationMessage;
import net.glowstone.net.protocol.GlowProtocol;
import net.glowstone.net.protocol.ProtocolType;
import net.marfgamer.jraknet.protocol.Reliability;
import net.marfgamer.jraknet.session.RakNetClientSession;
import net.pocketdreams.sequinland.net.protocol.packets.AvailableCommandsPacket;
import net.pocketdreams.sequinland.net.protocol.packets.FullChunkDataPacket;
import net.pocketdreams.sequinland.net.protocol.packets.PlayStatusPacket;
import net.pocketdreams.sequinland.net.protocol.packets.ResourcePacksInfoPacket;
import net.pocketdreams.sequinland.net.protocol.packets.SetCommandsEnabledPacket;
import net.pocketdreams.sequinland.net.protocol.packets.StartGamePacket;
import net.pocketdreams.sequinland.net.protocol.packets.TextPacket;
import net.pocketdreams.sequinland.util.PocketChunkUtils;
import net.pocketdreams.sequinland.util.SequinUtils;

/**
 * A single pocket connection to the server
 */
public class PocketSession extends GlowSession {
    private RakNetClientSession session;

    public PocketSession(GlowServer server, Channel channel, ConnectionManager connectionManager, RakNetClientSession session) {
        super(server, channel, connectionManager);
        this.session = session;
    }

    private JoinGameMessage stored;
    
    @Override
    public void send(Message message) {
        // TODO: Better message translator, come on, look at this mess!
        System.out.println("Sending " + message.getClass().getSimpleName() + " to client!");
        if (message instanceof ChunkDataMessage) {
            ChunkDataMessage pcPacket = (ChunkDataMessage) message;
            FullChunkDataPacket pePacket = new FullChunkDataPacket();
            pePacket.chunkX = pcPacket.getX();
            pePacket.chunkZ = pcPacket.getZ();
            pePacket.payload = PocketChunkUtils.translateToPocket(pcPacket.getChunk());
            pePacket.encode();
            session.sendMessage(Reliability.RELIABLE, pePacket);
            return;
        }
        if (message instanceof ChatMessage) {
            ChatMessage pcPacket = (ChatMessage) message;
            TextPacket pkText = new TextPacket();
            pkText.message = pcPacket.getText().flatten();
            pkText.type = TextPacket.TYPE_SYSTEM;
            pkText.encode();
            session.sendMessage(Reliability.RELIABLE, pkText);
            return;
        }
        if (message instanceof LoginSuccessMessage) {
            PlayStatusPacket pkPlay = new PlayStatusPacket();
            pkPlay.status = PlayStatusPacket.OK;
            pkPlay.encode();
            session.sendMessage(Reliability.RELIABLE, pkPlay);

            ResourcePacksInfoPacket pkRp = new ResourcePacksInfoPacket();
            pkRp.encode();
            session.sendMessage(Reliability.RELIABLE, pkRp);
            return;
        }
        if (message instanceof JoinGameMessage) {
            // JoinGameMessage pcPacket = (JoinGameMessage) message;
            // StartGamePacket pkStart = new StartGamePacket();
            stored = (JoinGameMessage) message; // Store it for later
            return;
        }
        if (message instanceof PositionRotationMessage) {
            if (stored != null) {                
                // We are going to start the game then.
                JoinGameMessage joinPacket = stored;
                PositionRotationMessage posPacket = (PositionRotationMessage) message;
                StartGamePacket pkStart = new StartGamePacket();
                pkStart.x = (float) posPacket.getX();
                pkStart.y = (float) posPacket.getY() + 1.62F;
                pkStart.z = (float) posPacket.getZ();
                pkStart.commandsEnabled = true;
                pkStart.dayCycleStopTime = 0;
                pkStart.difficulty = joinPacket.getDifficulty();
                pkStart.dimension = (byte) joinPacket.getDimension();
                pkStart.eduMode = false;
                pkStart.entityRuntimeId = joinPacket.getId();
                pkStart.entityUniqueId = joinPacket.getId();
                pkStart.gamemode = 1; // Default to creative
                pkStart.spawnX = 0;
                pkStart.spawnY = 70;
                pkStart.spawnZ = 0;
                pkStart.worldName = "Shantae is cute"; // The client doesn't care about the world name anyway
                pkStart.encode();
                session.sendMessage(Reliability.RELIABLE, pkStart);

                PlayStatusPacket pkPlay = new PlayStatusPacket();
                pkPlay.status = PlayStatusPacket.SPAWNED;
                pkPlay.encode();
                session.sendMessage(Reliability.RELIABLE, pkPlay);
                stored = null;

                SetCommandsEnabledPacket enableCommandsPk = new SetCommandsEnabledPacket();
                enableCommandsPk.enabled = true;
                enableCommandsPk.encode();
                session.sendMessage(Reliability.RELIABLE, enableCommandsPk);

                AvailableCommandsPacket availableCommandsPk = new AvailableCommandsPacket();
                availableCommandsPk.commands = "{\"default3\":{\"versions\":[{\"aliases\":[],\"description\":\"\",\"overloads\":{\"default\":{\"input\":{\"parameters\":[{\"name\":\"args\",\"type\":\"rawtext\",\"optional\":true}]},\"output\":{\"format_strings\"[]}}},\"permission\":\"false\"}]}}";
                availableCommandsPk.encode();
                session.sendMessage(Reliability.RELIABLE, availableCommandsPk);
                return;
            }
        }
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
}
