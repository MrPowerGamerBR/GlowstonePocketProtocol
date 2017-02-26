package net.pocketdreams.sequinland.network.protocol.packets;

import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.network.protocol.ProtocolInfo;

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
        return ProtocolInfo.AVAILABLE_COMMANDS_PACKET;
    }
}
