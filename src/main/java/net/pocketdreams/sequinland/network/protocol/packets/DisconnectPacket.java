package net.pocketdreams.sequinland.network.protocol.packets;

import lombok.AllArgsConstructor;
import net.pocketdreams.sequinland.network.protocol.ProtocolInfo;

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
        return ProtocolInfo.DISCONNECT_PACKET;
    }
}
