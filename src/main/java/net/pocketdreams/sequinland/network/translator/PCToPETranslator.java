package net.pocketdreams.sequinland.network.translator;

import com.flowpowered.network.Message;
import net.glowstone.net.GlowSession;
import net.pocketdreams.sequinland.network.protocol.packets.GamePacket;

/**
 * Created by daporkchop on 25.02.17.
 */
public interface PCToPETranslator<P extends Message> {
    public GamePacket[] translate(GlowSession session, P packet);
}