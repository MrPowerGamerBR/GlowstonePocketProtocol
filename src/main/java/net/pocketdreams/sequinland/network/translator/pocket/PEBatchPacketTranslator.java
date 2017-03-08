package net.pocketdreams.sequinland.network.translator.pocket;

import com.flowpowered.network.Message;

import net.pocketdreams.sequinland.glowstone.net.GlowPocketSession;
import net.pocketdreams.sequinland.glowstone.net.message.BatchPacket;
import net.pocketdreams.sequinland.glowstone.net.message.GamePacket;
import net.pocketdreams.sequinland.network.translator.PEToPCTranslator;
import net.pocketdreams.sequinland.util.SequinUtils;

public class PEBatchPacketTranslator implements PEToPCTranslator<GamePacket> {
    @Override
    public Message[] translate(GlowPocketSession session, GamePacket packet) {
        SequinUtils.processBatch((BatchPacket) packet, session.getRakNetSession());
        return new Message[] {};
    }
}
