package net.pocketdreams.sequinland.network.protocol.packets;

import org.bukkit.inventory.ItemStack;

import lombok.AllArgsConstructor;
import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.network.protocol.ProtocolInfo;

@AllArgsConstructor
public class ContainerSetSlotPacket extends GamePacket {
    public int windowid;
    public int slot;
    public int hotbarSlot;
    public ItemStack item;
    public byte unknown;
    
    public ContainerSetSlotPacket() {
        super();
    }

    public ContainerSetSlotPacket(Packet packet) {
        super(packet);
    }
    
    @Override
    public void encode() {
        this.writeByte(getPacketId());
        this.writeByte((byte) this.windowid);
        this.writeSignedVarInt(this.slot);
        this.writeSignedVarInt(this.hotbarSlot);
        this.writeItemStack(this.item);
        this.writeByte(this.unknown);
    }
    
    @Override
    public short getPacketId() {
        return ProtocolInfo.CONTAINER_SET_SLOT_PACKET;
    }
}
