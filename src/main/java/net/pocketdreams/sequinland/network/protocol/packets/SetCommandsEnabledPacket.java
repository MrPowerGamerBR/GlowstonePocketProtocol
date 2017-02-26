package net.pocketdreams.sequinland.network.protocol.packets;

import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.network.protocol.ProtocolInfo;

public class SetCommandsEnabledPacket extends GamePacket {
    public boolean enabled;
    
    public SetCommandsEnabledPacket() {
        super();
    }

    public SetCommandsEnabledPacket(Packet packet) {
        super(packet);
    }
    
    @Override
    public void encode() {
        this.writeByte(getPacketId());
        this.writeBoolean(enabled);
    }
    
    @Override
    public short getPacketId() {
        return ProtocolInfo.SET_COMMANDS_ENABLED_PACKET;
    }
}
