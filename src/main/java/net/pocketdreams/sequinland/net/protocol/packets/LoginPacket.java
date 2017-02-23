package net.pocketdreams.sequinland.net.protocol.packets;

import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.net.protocol.ProtocolInfo;

public class LoginPacket extends GamePacket {
    public long protocolVersion;
    public short gameEdition;
    public byte[] payload;
    
    public LoginPacket(Packet packet) {
        super(packet);
    }
    
    @Override
    public void decode() {
        this.protocolVersion = this.readUInt();
        this.gameEdition = this.readUByte();
        this.payload = this.readByteArray();
    }
    
    @Override
    public short getPacketId() {
        return ProtocolInfo.LOGIN_PACKET;
    }
}
