package net.pocketdreams.sequinland.network.translator;

import com.flowpowered.network.Message;

import net.pocketdreams.sequinland.network.PocketSession;
import net.pocketdreams.sequinland.network.protocol.packets.GamePacket;

/**
 * Created by daporkchop on 25.02.17.
 */
public interface PEToPCTranslator<P extends GamePacket> {
    public Message[] translate(PocketSession session, P packet);
}