package net.pocketdreams.sequinland.net.translator.pocket;

import com.flowpowered.network.Message;

import net.glowstone.net.message.play.player.PlayerPositionLookMessage;
import net.marfgamer.jraknet.RakNetPacket;
import net.pocketdreams.sequinland.net.PocketSession;
import net.pocketdreams.sequinland.net.protocol.packets.GamePacket;
import net.pocketdreams.sequinland.net.protocol.packets.MovePlayerPacket;
import net.pocketdreams.sequinland.net.translator.PEToPCTranslator;

public class PEMovePlayerPacketTranslator implements PEToPCTranslator<GamePacket> {
    @Override
    public Message[] translate(PocketSession session, GamePacket packet) {
        MovePlayerPacket pePacket = (MovePlayerPacket) packet;
        PlayerPositionLookMessage pcPacket = new PlayerPositionLookMessage(pePacket.onGround, pePacket.x, pePacket.y, pePacket.z, pePacket.yaw, pePacket.pitch);
        return new Message[] { pcPacket };
    }
}
