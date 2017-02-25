package net.pocketdreams.sequinland.net.translator.both;

import com.flowpowered.network.Message;
import net.glowstone.GlowServer;
import net.glowstone.net.GlowSession;
import net.marfgamer.jraknet.RakNetPacket;
import net.pocketdreams.sequinland.net.PocketSession;
import net.pocketdreams.sequinland.net.translator.PCToPETranslator;
import net.pocketdreams.sequinland.net.translator.PEToPCTranslator;

import java.util.logging.Level;

/**
 * Created by daporkchop on 25.02.17.
 */
public final class IgnorePacketTranslator implements PCToPETranslator<Message>, PEToPCTranslator<RakNetPacket> {

    @Override
    public RakNetPacket[] translate(GlowSession session, Message packet)    {
        GlowServer.logger.log(Level.ALL, "Ignoring packet: " + packet.getClass().getCanonicalName());
        return new RakNetPacket[0];
    }

    @Override
    public Message[] translate(PocketSession session, RakNetPacket packet)    {
        GlowServer.logger.log(Level.ALL, "Ignoring packet: " + packet.getClass().getCanonicalName());
        return new Message[0];
    }
}