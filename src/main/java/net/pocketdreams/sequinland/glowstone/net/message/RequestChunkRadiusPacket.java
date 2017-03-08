package net.pocketdreams.sequinland.glowstone.net.message;

import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.glowstone.net.protocol.PocketProtocol;

public class RequestChunkRadiusPacket extends GamePacket {
    public int radius;
    
    public RequestChunkRadiusPacket(Packet packet) {
        super(packet);
    }
    
    public RequestChunkRadiusPacket() {
        super();
    }
    
    @Override
    public void decode() {
        this.radius = this.readSignedVarInt();
    }
    
    @Override
    public short getPacketId() {
        return PocketProtocol.REQUEST_CHUNK_RADIUS_PACKET;
    }
}
