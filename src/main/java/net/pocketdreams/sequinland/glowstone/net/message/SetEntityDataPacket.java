package net.pocketdreams.sequinland.glowstone.net.message;

import lombok.AllArgsConstructor;
import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.entity.PocketEntity;
import net.pocketdreams.sequinland.entity.data.EntityMetadata;
import net.pocketdreams.sequinland.glowstone.net.protocol.PocketProtocol;

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
        return PocketProtocol.SET_ENTITY_DATA_PACKET;
    }
}
