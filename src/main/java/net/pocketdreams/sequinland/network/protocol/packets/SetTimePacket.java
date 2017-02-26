package net.pocketdreams.sequinland.network.protocol.packets;

import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.network.protocol.ProtocolInfo;

public class SetTimePacket extends GamePacket {
    public int time;
    public boolean dayLightCycle = true;
    
    public SetTimePacket() {
        super();
    }

    public SetTimePacket(Packet packet) {
        super(packet);
    }
    
    public SetTimePacket(int time, boolean dayLightCycle) {
        this.time = time;
        this.dayLightCycle = dayLightCycle;
    }
    
    @Override
    public void encode() {
        this.writeByte(getPacketId());
        this.writeSignedVarInt(time);
        this.writeBoolean(dayLightCycle);
    }
    
    @Override
    public short getPacketId() {
        return ProtocolInfo.SET_TIME_PACKET;
    }
    
}
