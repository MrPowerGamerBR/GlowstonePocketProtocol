package net.pocketdreams.sequinland.net.protocol.packets;

import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.net.protocol.ProtocolInfo;

public class ChunkRadiusUpdatedPacket extends GamePacket {
    public int radius;
    
    public ChunkRadiusUpdatedPacket(Packet packet) {
        super(packet);
    }
    
    public ChunkRadiusUpdatedPacket() {
        super();
    }
    
    @Override
    public void encode() {
        this.radius = this.readSignedVarInt();
    }
    
    @Override
    public short getPacketId() {
        return ProtocolInfo.CHUNK_RADIUS_UPDATED_PACKET;
    }
}
