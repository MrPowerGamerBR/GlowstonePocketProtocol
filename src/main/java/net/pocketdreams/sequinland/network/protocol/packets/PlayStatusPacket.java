package net.pocketdreams.sequinland.network.protocol.packets;

import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.network.protocol.ProtocolInfo;

public class PlayStatusPacket extends GamePacket {
    public long status;
    
    public static final long OK = 0;
    public static final long OUTDATED_CLIENT = 1;
    public static final long OUTDATED_SERVER = 2;
    public static final long SPAWNED = 3;
    public static final long INVALID_TENANT = 4;
    public static final long EDITION_MISMATCH = 5;
    
    public PlayStatusPacket() {
        super();
    }

    public PlayStatusPacket(Packet packet) {
        super(packet);
    }
    
    public PlayStatusPacket(long status) {
        this.status = status;
    }
    
    @Override
    public void encode() {
        this.writeByte(getPacketId());
        this.writeUInt(status);
    }
    
    @Override
    public short getPacketId() {
        return ProtocolInfo.PLAY_STATUS_PACKET;
    }
}
