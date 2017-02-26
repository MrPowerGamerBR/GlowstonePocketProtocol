package net.pocketdreams.sequinland.net.protocol.packets;

import lombok.AllArgsConstructor;
import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.net.protocol.ProtocolInfo;
import net.pocketdreams.sequinland.util.attributes.PocketAttribute;

@AllArgsConstructor
public class UpdateAttributesPacket extends GamePacket {
    public int entityId;
    public PocketAttribute attributes[];
    
    public UpdateAttributesPacket() {
        super();
    }

    public UpdateAttributesPacket(Packet packet) {
        super(packet);
    }
    
    @Override
    public void encode() {
        this.writeByte(getPacketId());
        this.writeUnsignedVarInt(entityId);
        this.writeUnsignedVarInt(attributes.length);
        for (PocketAttribute attribute : attributes) {
            this.writeAttribute(attribute);
        }
    }
    
    @Override
    public short getPacketId() {
        return ProtocolInfo.UPDATE_ATTRIBUTES_PACKET;
    }
}
