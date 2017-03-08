package net.pocketdreams.sequinland.glowstone.net.message;

import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.glowstone.net.protocol.PocketProtocol;

public class AvailableCommandsPacket extends GamePacket {
    public String commands; // JSON-encoded command data
    public String unknown = "";
    
    public AvailableCommandsPacket() {
        super();
    }

    public AvailableCommandsPacket(Packet packet) {
        super(packet);
    }
    
    @Override
    public void encode() {
        this.writeByte(getPacketId());
        this.writeVarString(commands);
        this.writeVarString(unknown);
    }
    
    @Override
    public short getPacketId() {
        return PocketProtocol.AVAILABLE_COMMANDS_PACKET;
    }
}
