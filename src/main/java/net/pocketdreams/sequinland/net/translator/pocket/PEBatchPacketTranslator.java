package net.pocketdreams.sequinland.net.translator.pocket;

import com.flowpowered.network.Message;

import net.marfgamer.jraknet.RakNetPacket;
import net.pocketdreams.sequinland.net.PocketSession;
import net.pocketdreams.sequinland.net.protocol.packets.BatchPacket;
import net.pocketdreams.sequinland.net.translator.PEToPCTranslator;
import net.pocketdreams.sequinland.util.SequinUtils;

public class PEBatchPacketTranslator implements PEToPCTranslator<RakNetPacket> {
    @Override
    public Message[] translate(PocketSession session, RakNetPacket packet) {
        SequinUtils.processBatch((BatchPacket) packet, session.getRakNetSession());
        return new Message[] {};
    }
}
