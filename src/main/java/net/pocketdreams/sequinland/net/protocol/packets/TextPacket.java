package net.pocketdreams.sequinland.net.protocol.packets;

import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.net.protocol.ProtocolInfo;

public class TextPacket extends GamePacket {

    public TextPacket() {
        super();
    }

    public TextPacket(Packet packet) {
        super(packet);
    }
    
    public TextPacket(String message, byte type) {
        this.message = message;
        this.type = type;
    }
    
    @Override
    public short getPacketId() {
        return ProtocolInfo.TEXT_PACKET;
    }

    public static final byte TYPE_RAW = 0;
    public static final byte TYPE_CHAT = 1;
    public static final byte TYPE_TRANSLATION = 2;
    public static final byte TYPE_POPUP = 3;
    public static final byte TYPE_TIP = 4;
    public static final byte TYPE_SYSTEM = 5;
    public static final byte TYPE_WHISPER = 6;

    public byte type;
    public String source = "";
    public String message = "";
    public String[] parameters = new String[0];

    @Override
    public void decode() {
        this.type = (byte) readByte();
        switch (type) {
            case TYPE_POPUP:
            case TYPE_CHAT:
                this.source = this.readVarString();
            case TYPE_RAW:
            case TYPE_TIP:
            case TYPE_SYSTEM:
                this.message = this.readVarString();
                break;

            case TYPE_TRANSLATION:
                this.message = this.readVarString();
                int count = (int) this.readUnsignedVarInt();
                this.parameters = new String[count];
                for (int i = 0; i < count; i++) {
                    this.parameters[i] = this.readVarString();
                }
        }
    }

    @Override
    public void encode() {
        this.writeByte(getPacketId());   
        this.writeByte(this.type);
        switch (this.type) {
            case TYPE_POPUP:
            case TYPE_CHAT:
                this.writeVarString(this.source);
            case TYPE_RAW:
            case TYPE_TIP:
            case TYPE_SYSTEM:
                this.writeVarString(this.message);
                break;

            case TYPE_TRANSLATION:
                this.writeVarString(this.message);
                this.writeUnsignedVarInt(this.parameters.length);
                for (String parameter : this.parameters) {
                    this.writeVarString(parameter);
                }
        }
    }

}
