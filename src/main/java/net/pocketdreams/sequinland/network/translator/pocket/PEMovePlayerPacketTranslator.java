package net.pocketdreams.sequinland.network.translator.pocket;

import com.flowpowered.network.Message;

import net.glowstone.net.message.play.player.PlayerPositionLookMessage;
import net.pocketdreams.sequinland.network.PocketSession;
import net.pocketdreams.sequinland.network.protocol.packets.GamePacket;
import net.pocketdreams.sequinland.network.protocol.packets.MovePlayerPacket;
import net.pocketdreams.sequinland.network.translator.PEToPCTranslator;

public class PEMovePlayerPacketTranslator implements PEToPCTranslator<GamePacket> {
    @Override
    public Message[] translate(PocketSession session, GamePacket packet) {
        MovePlayerPacket pePacket = (MovePlayerPacket) packet;
        PlayerPositionLookMessage pcPacket = new PlayerPositionLookMessage(pePacket.onGround, pePacket.x, pePacket.y - 1.62, pePacket.z, pePacket.yaw, pePacket.pitch);
        return new Message[] { pcPacket };
    }
}
