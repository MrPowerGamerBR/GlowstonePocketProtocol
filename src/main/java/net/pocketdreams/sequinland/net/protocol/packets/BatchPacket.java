package net.pocketdreams.sequinland.net.protocol.packets;

import java.util.ArrayList;
import java.util.List;

import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.net.protocol.ProtocolInfo;

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
        return ProtocolInfo.BATCH_PACKET;
    }
}
