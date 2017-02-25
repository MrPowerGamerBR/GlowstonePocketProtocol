package net.pocketdreams.sequinland.net.translator;

import com.flowpowered.network.Message;
import net.marfgamer.jraknet.RakNetPacket;
import net.pocketdreams.sequinland.net.PocketSession;

/**
 * Created by daporkchop on 25.02.17.
 */
public interface PEToPCTranslator<P extends RakNetPacket> {
    public Message[] translate(PocketSession session, P packet);
}