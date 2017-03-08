package net.pocketdreams.sequinland.glowstone.net.message;

import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.glowstone.net.protocol.PocketProtocol;

public class BatchPacket extends GamePacket {
    public byte[] payload;
    
    public BatchPacket() {
        super();
    }

    public BatchPacket(Packet packet) {
        super(packet);
    }
    
    @Override
    public void decode() {
        this.payload = this.readByteArray();
    }
    
    @Override
    public void encode() {
        this.writeByte(getPacketId());
        this.writeByteArray(this.payload);
    }
    
    @Override
    public short getPacketId() {
        return PocketProtocol.BATCH_PACKET;
    }
}
