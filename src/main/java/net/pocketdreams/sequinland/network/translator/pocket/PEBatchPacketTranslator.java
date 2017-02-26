package net.pocketdreams.sequinland.network.translator.pocket;

import com.flowpowered.network.Message;

import net.pocketdreams.sequinland.network.PocketSession;
import net.pocketdreams.sequinland.network.protocol.packets.BatchPacket;
import net.pocketdreams.sequinland.network.protocol.packets.GamePacket;
import net.pocketdreams.sequinland.network.translator.PEToPCTranslator;
import net.pocketdreams.sequinland.util.SequinUtils;

public class PEBatchPacketTranslator implements PEToPCTranslator<GamePacket> {
    @Override
    public Message[] translate(PocketSession session, GamePacket packet) {
        SequinUtils.processBatch((BatchPacket) packet, session.getRakNetSession());
        return new Message[] {};
    }
}
