package net.pocketdreams.sequinland.net.translator.pocket;

import com.flowpowered.network.Message;

import net.glowstone.net.message.play.game.IncomingChatMessage;
import net.pocketdreams.sequinland.net.PocketSession;
import net.pocketdreams.sequinland.net.protocol.packets.GamePacket;
import net.pocketdreams.sequinland.net.protocol.packets.TextPacket;
import net.pocketdreams.sequinland.net.translator.PEToPCTranslator;

public class PETextPacketTranslator implements PEToPCTranslator<GamePacket> {
    @Override
    public Message[] translate(PocketSession session, GamePacket packet) {
        TextPacket pePacket = (TextPacket) packet;
        if (pePacket.message.startsWith("!")) {
            // Remember the super workaround from the pre-0.16 days?
            // Well, it is back since I'm dumb and I'm lazy to properly implement the available commands packet!
            pePacket.message = pePacket.message.replaceFirst("!", "/");
        }
        IncomingChatMessage pcPacket = new IncomingChatMessage(pePacket.message);
        session.messageReceived(pcPacket);
        return new Message[] { pcPacket };
    }
}
