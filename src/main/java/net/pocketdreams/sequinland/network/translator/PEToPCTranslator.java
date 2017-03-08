package net.pocketdreams.sequinland.network.translator;

import com.flowpowered.network.Message;

import net.pocketdreams.sequinland.glowstone.net.GlowPocketSession;
import net.pocketdreams.sequinland.glowstone.net.message.GamePacket;

/**
 * Created by daporkchop on 25.02.17.
 */
public interface PEToPCTranslator<P extends GamePacket> {
    public Message[] translate(GlowPocketSession session, P packet);
}