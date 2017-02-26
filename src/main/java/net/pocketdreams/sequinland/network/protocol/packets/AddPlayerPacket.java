package net.pocketdreams.sequinland.network.protocol.packets;

import java.util.UUID;

import org.bukkit.inventory.ItemStack;

import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.network.protocol.ProtocolInfo;

public class AddPlayerPacket extends GamePacket {
    public UUID uuid;
    public String username;
    public long entityUniqueId;
    public long entityRuntimeId;
    public float x;
    public float y;
    public float z;
    public float speedX;
    public float speedY;
    public float speedZ;
    public float pitch;
    public float yaw;
    public ItemStack heldItem;
    
    public AddPlayerPacket(Packet packet) {
        super(packet);
    }
    
    public AddPlayerPacket() {
        super();
    }
    
    @Override
    public void encode() {
        this.writeByte(getPacketId());
        this.writeUUID(this.uuid);
        this.writeVarString(this.username);
        this.writeUnsignedVarLong(this.entityUniqueId);
        this.writeUnsignedVarLong(this.entityRuntimeId);
        this.writeVector3f(this.x, this.y, this.z);
        this.writeVector3f(this.speedX, this.speedY, this.speedZ);
        this.writeLFloat(this.pitch);
        this.writeLFloat(this.yaw); //TODO headrot
        this.writeLFloat(this.yaw);
        
        this.writeItemStack(heldItem);
        this.writeUnsignedVarInt(0); // TODO: Metadata!
    }
    
    @Override
    public short getPacketId() {
        return ProtocolInfo.ADD_PLAYER_PACKET;
    }
}
