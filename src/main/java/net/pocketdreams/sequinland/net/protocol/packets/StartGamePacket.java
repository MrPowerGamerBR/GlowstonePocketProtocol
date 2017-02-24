package net.pocketdreams.sequinland.net.protocol.packets;

import net.marfgamer.jraknet.Packet;
import net.pocketdreams.sequinland.net.protocol.ProtocolInfo;

public class StartGamePacket extends GamePacket {
    public long entityUniqueId;
    public long entityRuntimeId;
    public float x;
    public float y;
    public float z;
    public int seed;
    public byte dimension;
    public int generator = 1;
    public int gamemode;
    public int difficulty;
    public int spawnX;
    public int spawnY;
    public int spawnZ;
    public boolean hasAchievementsDisabled = true;
    public int dayCycleStopTime = -1; //-1 = not stopped, any positive value = stopped at that time
    public boolean eduMode = false;
    public float rainLevel;
    public float lightningLevel;
    public boolean commandsEnabled;
    public boolean isTexturePacksRequired = false;
    public String levelId = ""; //base64 string, usually the same as world folder name in vanilla
    public String worldName;
    
    public StartGamePacket() {
        super();
    }

    public StartGamePacket(Packet packet) {
        super(packet);
    }
    
    @Override
    public void encode() {
        this.writeByte(getPacketId());
        this.writeUnsignedVarLong(this.entityUniqueId);
        this.writeUnsignedVarLong(this.entityRuntimeId);
        this.writeVector3f(this.x, this.y, this.z);
        this.writeFloat(90); // yaw
        this.writeFloat(90); // pitch
        this.writeSignedVarInt(this.seed);
        this.writeSignedVarInt(this.dimension);
        this.writeSignedVarInt(this.generator);
        this.writeSignedVarInt(this.gamemode);
        this.writeSignedVarInt(this.difficulty);
        this.writeBlockCoords(this.spawnX, this.spawnY, this.spawnZ);
        this.writeBoolean(this.hasAchievementsDisabled);
        this.writeSignedVarInt(this.dayCycleStopTime);
        this.writeBoolean(this.eduMode);
        this.writeFloat(this.rainLevel);
        this.writeFloat(this.lightningLevel);
        this.writeBoolean(this.commandsEnabled);
        this.writeBoolean(this.isTexturePacksRequired);
        this.writeString(this.levelId);
        this.writeString(this.worldName);
    }
    
    @Override
    public short getPacketId() {
        return ProtocolInfo.START_GAME_PACKET;
    }
}
