package net.pocketdreams.sequinland.glowstone.net.message;

import lombok.AllArgsConstructor;
import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.glowstone.net.protocol.PocketProtocol;

@AllArgsConstructor
public class MoveEntityPacket extends GamePacket {
    public long eid;
    public double x;
    public double y;
    public double z;
    public double yaw;
    public double headYaw;
    public double pitch;
    
    public MoveEntityPacket() {
        super();
    }

    public MoveEntityPacket(Packet packet) {
        super(packet);
    }

    @Override
    public void encode() {
        this.writeByte(this.getPacketId());
        this.writeUnsignedVarLong(this.eid);
        this.writeVector3f((float) this.x, (float) this.y, (float) this.z);
        this.writeByte((byte) (this.pitch / (360d / 256d)));
        this.writeByte((byte) (this.headYaw / (360d / 256d)));
        this.writeByte((byte) (this.yaw / (360d / 256d)));
    }
    
    @Override
    public short getPacketId() {
        return PocketProtocol.MOVE_ENTITY_PACKET;
    }
}
