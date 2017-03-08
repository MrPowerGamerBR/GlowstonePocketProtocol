package net.pocketdreams.sequinland.glowstone.net.message;

import lombok.AllArgsConstructor;
import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.glowstone.net.protocol.PocketProtocol;

@AllArgsConstructor
public class TransferPacket extends GamePacket {
    public String address;
    public int port;
    
    public TransferPacket() {
        super();
    }

    public TransferPacket(Packet packet) {
        super(packet);
    }
    
    @Override
    public void encode() {
        this.writeByte(getPacketId());
        this.writeVarString(address);
        this.writeShortLE(port);
    }
    
    @Override
    public short getPacketId() {
        return PocketProtocol.TRANSFER_PACKET;
    }
}
