package net.pocketdreams.sequinland.network.protocol.packets;

import org.bukkit.inventory.ItemStack;

import lombok.AllArgsConstructor;
import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.network.protocol.ProtocolInfo;

@AllArgsConstructor
public class ContainerSetContentPacket extends GamePacket {
    public static final byte SPECIAL_INVENTORY = 0;
    public static final byte SPECIAL_ARMOR = 0x78;
    public static final byte SPECIAL_CREATIVE = 0x79;
    public static final byte SPECIAL_HOTBAR = 0x7a;

    public int windowid;
    public ItemStack[] slots = new ItemStack[0];
    public int[] hotbar = new int[0];
    
    public ContainerSetContentPacket() {
        super();
    }

    public ContainerSetContentPacket(Packet packet) {
        super(packet);
    }
    
    @Override
    public void encode() {
        this.writeByte(getPacketId());
        this.writeByte((byte) this.windowid);
        this.writeUnsignedVarInt(this.slots.length);
        for (ItemStack item : slots) {
            this.writeItemStack(item);
        }
        if (this.windowid == SPECIAL_INVENTORY && this.hotbar.length > 0) {
            this.writeUnsignedVarInt(this.hotbar.length);
            for (int slot : this.hotbar) {
                this.writeSignedVarInt(slot);
            }
        } else {
            this.writeUnsignedVarInt(0);
        }
    }
    
    @Override
    public short getPacketId() {
        return ProtocolInfo.CONTAINER_SET_CONTENT_PACKET;
    }
}
