package net.pocketdreams.sequinland.net.protocol.packets;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import net.marfgamer.jraknet.Packet;
import net.marfgamer.jraknet.RakNetPacket;
import net.marfgamer.jraknet.protocol.Reliability;
import net.pocketdreams.sequinland.util.VarInt;
import net.pocketdreams.sequinland.util.nukkit.Binary;
import net.pocketdreams.sequinland.util.nukkit.Vector2;
import net.pocketdreams.sequinland.util.nukkit.Vector2f;
import net.pocketdreams.sequinland.util.nukkit.Vector3;
import net.pocketdreams.sequinland.util.nukkit.Vector3f;

/**
 * A packet meant for Minecraft: Pocket Edition
 *
 * @author MarfGamer
 */
public class GamePacket extends RakNetPacket {
    public Reliability reliability = Reliability.RELIABLE;

    public GamePacket() {
        super(0xFE);
    }

    public GamePacket(int id) {
        super(id);
    }

    public GamePacket(byte[] data) {
        super(data);
    }

    public GamePacket(Packet packet) {
        super(packet);
    }

    /**
     * Reads an unsigned VarInt
     * 
     * @return An unsigned VarInt
     */
    public int readUnsignedVarInt() {
        return VarInt.readUnsignedVarInt(this);
    }

    /**
     * Reads a signed VarInt
     * 
     * @return A signed VarInt
     */
    public int readSignedVarInt() {
        return VarInt.readSignedVarInt(this);
    }

    public void writeLFloat(float v) {
        this.write(net.pocketdreams.sequinland.util.nukkit.Binary.writeLFloat(v));
    }

    public float readLFloat() {
        return net.pocketdreams.sequinland.util.nukkit.Binary.readLFloat(this.read(4), -1);
    }

    /**
     * Reads an unsigned VarLong
     * 
     * @return An unsigned VarLong
     */
    public long readUnsignedVarLong() {
        return VarInt.readUnsignedVarLong(this);
    }

    /**
     * Reads a signed VarLong
     * 
     * @return A signed VarLong
     */
    public long readSignedVarLong() {
        return VarInt.readSignedVarLong(this);
    }

    /**
     * Reads a vector of 2 floats
     * 
     * @return A vector of 2 floats
     */
    public Vector2f readVector2f() {
        float x = this.readFloat();
        float y = this.readFloat();
        return new Vector2f(x, y);
    }

    /**
     * Reads a vector of 2 integers
     * 
     * @return A vector of 2 integers
     */
    public Vector2 readVector2i() {
        int x = this.readInt();
        int y = this.readInt();
        return new Vector2(x, y);
    }


    /**
     * Reads a vector of 3 floats
     * 
     * @return A vector of 3 floats
     */
    public Vector3f readVector3f() {
        float x = this.readLFloat();
        float y = this.readLFloat();
        float z = this.readLFloat();
        return new Vector3f(x, y, z);
    }

    /**
     * Reads a vector of 3 integers
     * 
     * @return A vector of 3 integers
     */

    public Vector3 readVector3i() {
        int x = this.readInt();
        int y = this.readInt();
        int z = this.readInt();
        return new Vector3(x, y, z);
    }


    /**
     * Reads a block location
     * 
     * @return A block location
     */
    /* public Vector3i readBlockLocation() {
        int x = this.readSignedVarInt();
        int y = this.readSignedVarInt();
        int z = this.readSignedVarInt();
        return new Vector3i(x, y, z);
    } */

    /**
     * Reads a byte array from the packet
     * 
     * @return A byte array
     */
    public byte[] readByteArray() {
        int length = this.readUnsignedVarInt();
        return this.read(length);
    }

    /**
     * Reads a UTF-8 String with it's length prefixed by an unsigned VarInt
     * 
     * @return A String
     */
    public String readVarString() {
        return new String(this.readByteArray());
    }

    /**
     * Reads a UTF-8 String with it's length prefixed by a signed little endian
     * integer
     * 
     * @return A String
     */
    public String readStringILE() {
        int length = this.readIntLE();
        return new String(this.read(length));
    }

    /**
     * Writes an unsigned VarInt
     * 
     * @param varInt
     *            The VarInt to write
     * @return The packet
     */
    public GamePacket writeUnsignedVarInt(int varInt) {
        VarInt.writeUnsignedVarInt(this, varInt);
        return this;
    }

    /**
     * Writes a signed VarInt
     * 
     * @param varInt
     *            The VarInt to write
     * @return The packet
     */
    public GamePacket writeSignedVarInt(int varInt) {
        VarInt.writeSignedVarInt(this, varInt);
        return this;
    }

    /**
     * Writes an unsigned VarLong
     * 
     * @param varLong
     *            The VarLong to write
     * @return The packet
     */
    public GamePacket writeUnsignedVarLong(long varLong) {
        VarInt.writeUnsignedVarLong(this, varLong);
        return this;
    }

    /**
     * Writes a signed VarLong
     * 
     * @param varLong
     *            The VarLong to write
     * @return The packet
     */
    public GamePacket writeSignedVarLong(long varLong) {
        VarInt.writeSignedVarLong(this, varLong);
        return this;
    }

    /**
     * Writes a vector of 2 floats
     * 
     * @param vector
     *            The vector to write
     * @return The packet
     */
    /* public GamePacket writeVector2f(Vector2f vector) {
        this.writeFloat(vector.x);
        this.writeFloat(vector.y);
        return this;

    } */

    /**
     * Writes a vector of 2 integers
     * 
     * @param vector
     *            The vector to write
     * @return 
     * @return The packet
     */
    /*
     * public GamePacket writeVector2i(Vector2i vector) {
     * this.writeInt(vector.x); this.writeInt(vector.y); return this; }
     */

    public GamePacket writeVector3f(double x, double y, double z) {
        this.writeLFloat((float) x);
        this.writeLFloat((float) y);
        this.writeLFloat((float) z);
        return this;
    }

    public void writeBlockCoords(int x, int y, int z) {
        this.writeSignedVarInt(x);
        this.writeUnsignedVarInt(y);
        this.writeSignedVarInt(z);
    }

    /**
     * Writes a vector of 3 integers
     * 
     * @param vector
     *            The vector to write
     * @return The packet
     */
    /*
     * public GamePacket writeVector3i(Vector3i vector) {
     * this.writeInt(vector.x); this.writeInt(vector.y);
     * this.writeInt(vector.z); return this; }
     */

    /**
     * Writes a block location
     * 
     * @param vector
     *            The block location
     * @return The packet
     */
    /* public GamePacket writeBlockLocation(Vector3i vector) {
        this.writeSignedVarInt(vector.x);
        this.writeSignedVarInt(vector.y);
        this.writeSignedVarInt(vector.z);
        return this;
    } */

    /**
     * Writes a byte array to the packet
     * 
     * @param data
     *            The data to write
     * @return The packet
     */
    public GamePacket writeByteArray(byte[] data) {
        this.writeUnsignedVarInt(data.length);
        this.write(data);
        return this;
    }

    /**
     * Writes a UTF-8 String prefixed by a signed VarInt to the packet
     * 
     * @param s
     *            The String
     * @return The packet
     */
    public GamePacket writeVarString(String s) {
        byte[] b = s.getBytes(StandardCharsets.UTF_8);
        this.writeByteArray(b);
        return this;
    }

    /**
     * Writes a UTF-8 String prefixed by an signed little endian integer to the
     * packet
     * 
     * @param s
     *            The String
     * @return The packet
     */
    public GamePacket writeStringILE(String s) {
        this.writeIntLE(s.length());
        this.write(s.getBytes());
        return this;
    }

    public GamePacket writeUUID(UUID uuid) {
        this.write(Binary.writeUUID(uuid));
        return this;
    }
    
    /**
     * If a class that extends <code>GamePacket</code> overrides this and makes
     * it return <code>true</code>, whenever the <code>Player</code> object
     * sends it to a player it will always be compressed no matter the size
     * 
     * @return Whether or not the packet should always be compressed
     */
    public boolean forceCompression() {
        return false;
    }

    public void encode() {
        throw new UnsupportedOperationException();
    }

    public GamePacket andEncode() {
        this.encode();
        return this;
    }
    
    public void decode() {
        throw new UnsupportedOperationException();
    }

    public GamePacket andDecode() {
        this.decode();
        return this;
    }
    
    public short getPacketId() {
        throw new UnsupportedOperationException();
    }
}
