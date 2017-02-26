package net.pocketdreams.sequinland.network.protocol.packets;

import lombok.AllArgsConstructor;
import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.network.protocol.ProtocolInfo;

@AllArgsConstructor
public class FullChunkDataPacket extends GamePacket {
    public int chunkX;
    public int chunkZ;
    public byte[] payload;
    
    public FullChunkDataPacket() {
        super();
    }

    public FullChunkDataPacket(Packet packet) {
        super(packet);
    }
    
    @Override
    public void encode() {
        this.writeByte(getPacketId());
        this.writeSignedVarInt(chunkX);
        this.writeSignedVarInt(chunkZ);
        this.writeByteArray(payload);
    }
    
    @Override
    public short getPacketId() {
        return ProtocolInfo.FULL_CHUNK_DATA_PACKET;
    }
}
