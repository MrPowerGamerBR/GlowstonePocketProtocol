package net.pocketdreams.sequinland.glowstone.net.message;

import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.glowstone.net.protocol.PocketProtocol;

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
        return PocketProtocol.SET_COMMANDS_ENABLED_PACKET;
    }
}
