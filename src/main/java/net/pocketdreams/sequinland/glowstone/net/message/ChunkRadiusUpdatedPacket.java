package net.pocketdreams.sequinland.glowstone.net.message;

import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.glowstone.net.protocol.PocketProtocol;

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
        this.writeByte(getPacketId());
        this.radius = this.readSignedVarInt();
    }
    
    @Override
    public short getPacketId() {
        return PocketProtocol.CHUNK_RADIUS_UPDATED_PACKET;
    }
}
