package net.pocketdreams.sequinland.net.translator;

import com.flowpowered.network.Message;
import net.pocketdreams.sequinland.net.PocketSession;
import net.pocketdreams.sequinland.net.protocol.packets.GamePacket;

/**
 * Created by daporkchop on 25.02.17.
 */
public interface PEToPCTranslator<P extends GamePacket> {
    public Message[] translate(PocketSession session, P packet);
}