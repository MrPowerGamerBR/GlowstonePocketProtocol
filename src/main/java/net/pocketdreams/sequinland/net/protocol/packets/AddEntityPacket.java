package net.pocketdreams.sequinland.net.protocol.packets;

import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.net.protocol.ProtocolInfo;

public class AddEntityPacket extends GamePacket {
    public long entityUniqueId;
    public long entityRuntimeId;
    public int type;
    public float x;
    public float y;
    public float z;
    public float speedX;
    public float speedY;
    public float speedZ;
    public float pitch;
    public float yaw;
    
    public AddEntityPacket(Packet packet) {
        super(packet);
    }
    
    public AddEntityPacket() {
        super();
    }
    
    @Override
    public void encode() {
        this.writeByte(getPacketId());
        this.writeUnsignedVarLong(this.entityUniqueId);
        this.writeUnsignedVarLong(this.entityRuntimeId);
        this.writeUnsignedVarLong(this.type);
        this.writeVector3f(this.x, this.y, this.z);
        this.writeVector3f(this.speedX, this.speedY, this.speedZ);
        this.writeLFloat(this.pitch);
        this.writeLFloat(this.yaw);
        
        this.writeUnsignedVarInt(0); // TODO: Item!
        this.writeUnsignedVarInt(0); // TODO: Metadata!
        this.writeUnsignedVarInt(0); // TODO: Linked Objects!
    }
    
    @Override
    public short getPacketId() {
        return ProtocolInfo.ADD_ENTITY_PACKET;
    }
}
