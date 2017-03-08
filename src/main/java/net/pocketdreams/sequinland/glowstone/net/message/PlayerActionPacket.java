package net.pocketdreams.sequinland.glowstone.net.message;

import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.glowstone.net.protocol.PocketProtocol;
import net.pocketdreams.sequinland.util.nukkit.Vector3f;

public class PlayerActionPacket extends GamePacket {
    public static final byte ACTION_START_BREAK = 0;
    public static final byte ACTION_ABORT_BREAK = 1;
    public static final byte ACTION_STOP_BREAK = 2;

    public static final byte ACTION_RELEASE_ITEM = 5;
    public static final byte ACTION_STOP_SLEEPING = 6;
    public static final byte ACTION_RESPAWN = 7;
    public static final byte ACTION_JUMP = 8;
    public static final byte ACTION_START_SPRINT = 9;
    public static final byte ACTION_STOP_SPRINT = 10;
    public static final byte ACTION_START_SNEAK = 11;
    public static final byte ACTION_STOP_SNEAK = 12;
    public static final byte ACTION_DIMENSION_CHANGE = 13; //TODO: correct these

    public static final byte ACTION_NETHER_UNKNOWN = 14; //todo what's this?

    public static final byte ACTION_START_GLIDE = 15;
    public static final byte ACTION_STOP_GLIDE = 16;
    
    public long entityId;
    public int action;
    public Vector3f position;
    public int face;
    
    public PlayerActionPacket() {
        super();
    }

    public PlayerActionPacket(Packet packet) {
        super(packet);
    }
    
    @Override
    public void decode() {
        this.entityId = this.readUnsignedVarLong();
        this.action = this.readSignedVarInt();
        this.position = this.readBlockCoords();
        this.face = this.readSignedVarInt();
    }
    
    @Override
    public short getPacketId() {
        return PocketProtocol.PLAYER_ACTION_PACKET;
    }
}
