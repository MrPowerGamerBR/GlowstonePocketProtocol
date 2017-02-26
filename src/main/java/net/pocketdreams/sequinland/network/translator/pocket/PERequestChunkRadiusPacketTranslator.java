package net.pocketdreams.sequinland.network.translator.pocket;

import com.flowpowered.network.Message;

import net.marfgamer.jraknet.protocol.Reliability;
import net.pocketdreams.sequinland.network.PocketSession;
import net.pocketdreams.sequinland.network.protocol.packets.ChunkRadiusUpdatedPacket;
import net.pocketdreams.sequinland.network.protocol.packets.GamePacket;
import net.pocketdreams.sequinland.network.protocol.packets.RequestChunkRadiusPacket;
import net.pocketdreams.sequinland.network.translator.PEToPCTranslator;

public class PERequestChunkRadiusPacketTranslator implements PEToPCTranslator<GamePacket> {
    @Override
    public Message[] translate(PocketSession session, GamePacket packet) {
        RequestChunkRadiusPacket pePacket = (RequestChunkRadiusPacket) packet;
        ChunkRadiusUpdatedPacket crup = new ChunkRadiusUpdatedPacket();
        pePacket.radius = crup.radius; // Use client's view distance
        session.getRakNetSession().sendMessage(Reliability.UNRELIABLE, pePacket);
        return new Message[] {};
    }
}
