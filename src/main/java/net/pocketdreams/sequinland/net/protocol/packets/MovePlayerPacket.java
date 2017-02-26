package net.pocketdreams.sequinland.net.protocol.packets;

import lombok.AllArgsConstructor;
import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.net.protocol.ProtocolInfo;
import net.pocketdreams.sequinland.util.nukkit.Vector3f;

@AllArgsConstructor
public class MovePlayerPacket extends GamePacket {
    public static final byte MODE_NORMAL = 0;
    public static final byte MODE_RESET = 1;
    public static final byte MODE_ROTATION = 2;

    public long eid;
    public float x;
    public float y;
    public float z;
    public float yaw;
    public float headYaw;
    public float pitch;
    public byte mode = MODE_NORMAL;
    public boolean onGround;
    
    public MovePlayerPacket() {
        super();
    }

    public MovePlayerPacket(Packet packet) {
        super(packet);
    }
    
    @Override
    public void decode() {
        this.eid = this.readSignedVarLong();
        Vector3f v = this.readVector3f();
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.pitch = this.readLFloat();
        this.headYaw = this.readLFloat();
        this.yaw = this.readLFloat();
        this.mode = (byte) this.readByte();
        this.onGround = this.readBoolean();
    }

    @Override
    public void encode() {
        this.writeByte(getPacketId()); 
        this.writeSignedVarLong(this.eid);
        this.writeVector3f(this.x, this.y, this.z);
        this.writeLFloat(this.pitch);
        this.writeLFloat(this.yaw);
        this.writeLFloat(this.headYaw);
        this.writeByte(this.mode);
        this.writeBoolean(this.onGround);
    }
    
    @Override
    public short getPacketId() {
        return ProtocolInfo.MOVE_PLAYER_PACKET;
    }
}
