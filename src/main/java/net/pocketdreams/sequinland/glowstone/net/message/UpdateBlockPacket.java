package net.pocketdreams.sequinland.glowstone.net.message;

import lombok.AllArgsConstructor;
import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.glowstone.net.protocol.PocketProtocol;

@AllArgsConstructor
public class UpdateBlockPacket extends GamePacket {
    public int x;
    public int y;
    public int z;
    public int blockId;
    public int blockData;
    
    public UpdateBlockPacket() {
        super();
    }

    public UpdateBlockPacket(Packet packet) {
        super(packet);
    }
    
    @Override
    public void encode() {
        this.writeByte(getPacketId());
        this.writeBlockCoords(x, y, z);
        this.writeUnsignedVarInt(blockId);
        this.writeUnsignedVarInt((0xb << 4) | blockData & 0xf);
    }
    
    @Override
    public short getPacketId() {
        return PocketProtocol.UPDATE_BLOCK_PACKET;
    }
}
