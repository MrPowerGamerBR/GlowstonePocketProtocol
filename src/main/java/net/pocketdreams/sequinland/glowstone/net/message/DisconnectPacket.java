package net.pocketdreams.sequinland.glowstone.net.message;

import lombok.AllArgsConstructor;
import net.pocketdreams.sequinland.glowstone.net.protocol.PocketProtocol;

@AllArgsConstructor
public class DisconnectPacket extends GamePacket {
    public boolean hideDisconnectionScreen;
    public String message;
    
    @Override
    public void encode() {
        this.writeByte(getPacketId());
        this.writeBoolean(hideDisconnectionScreen);
        this.writeVarString(message);
    }
    
    @Override
    public short getPacketId() {
        return PocketProtocol.DISCONNECT_PACKET;
    }
}
