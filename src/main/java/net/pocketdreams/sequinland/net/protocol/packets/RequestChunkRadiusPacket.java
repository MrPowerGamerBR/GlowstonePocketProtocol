package net.pocketdreams.sequinland.net.protocol.packets;

import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.net.protocol.ProtocolInfo;

public class RequestChunkRadiusPacket extends GamePacket {
    public int radius;
    
    public RequestChunkRadiusPacket(Packet packet) {
        super(packet);
    }
    
    @Override
    public void decode() {
        this.radius = this.readSignedVarInt();
    }
    
    @Override
    public short getPacketId() {
        return ProtocolInfo.REQUEST_CHUNK_RADIUS_PACKET;
    }
}
