package net.pocketdreams.sequinland.net.protocol.packets;

import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.net.protocol.ProtocolInfo;

public class ResourcePacksInfoPacket extends GamePacket {
    public boolean mustAccept;
    
    public ResourcePacksInfoPacket() {
        super();
    }

    public ResourcePacksInfoPacket(Packet packet) {
        super(packet);
    }
    
    @Override
    public void encode() {
        this.writeByte(getPacketId());
        this.writeBoolean(mustAccept);
        this.writeShort(0); // No resource packs yet
        this.writeShort(0);
    }
    
    @Override
    public short getPacketId() {
        return ProtocolInfo.RESOURCE_PACKS_INFO_PACKET;
    }
}
