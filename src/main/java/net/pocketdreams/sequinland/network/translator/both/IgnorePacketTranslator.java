package net.pocketdreams.sequinland.network.translator.both;

import com.flowpowered.network.Message;
import net.glowstone.GlowServer;
import net.glowstone.net.GlowSession;
import net.pocketdreams.sequinland.glowstone.net.GlowPocketSession;
import net.pocketdreams.sequinland.glowstone.net.message.GamePacket;
import net.pocketdreams.sequinland.network.translator.PCToPETranslator;
import net.pocketdreams.sequinland.network.translator.PEToPCTranslator;

import java.util.logging.Level;

/**
 * Created by daporkchop on 25.02.17.
 */
public final class IgnorePacketTranslator implements PCToPETranslator<Message>, PEToPCTranslator<GamePacket> {

    @Override
    public GamePacket[] translate(GlowSession session, Message packet)    {
        GlowServer.logger.log(Level.ALL, "Ignoring packet: " + packet.getClass().getCanonicalName());
        return new GamePacket[0];
    }

    @Override
    public Message[] translate(GlowPocketSession session, GamePacket packet)    {
        GlowServer.logger.log(Level.ALL, "Ignoring packet: " + packet.getClass().getCanonicalName());
        return new Message[0];
    }
}