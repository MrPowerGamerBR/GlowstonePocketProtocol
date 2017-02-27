package net.pocketdreams.sequinland.network.protocol.packets;

import lombok.AllArgsConstructor;
import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.entity.PocketEntity;
import net.pocketdreams.sequinland.entity.data.EntityMetadata;
import net.pocketdreams.sequinland.network.protocol.ProtocolInfo;

@AllArgsConstructor
public class SetEntityDataPacket extends GamePacket {
    public long entityId;
    public EntityMetadata metadata = PocketEntity.getDefaultMetadata();
    
    public SetEntityDataPacket() {
        super();
    }

    public SetEntityDataPacket(Packet packet) {
        super(packet);
    }
    
    @Override
    public void encode() {
        this.writeByte(getPacketId());
        this.writeUnsignedVarLong(entityId);
        this.writeMetadata(metadata);
    }
    
    @Override
    public short getPacketId() {
        return ProtocolInfo.SET_ENTITY_DATA_PACKET;
    }
}
