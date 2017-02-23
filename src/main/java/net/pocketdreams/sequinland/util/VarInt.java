package net.pocketdreams.sequinland.util;

import net.marfgamer.jraknet.Packet;

public class VarInt {

    /**
     * Reads an unsigned varint
     * 
     * @return An unsigned varint
     */
    public static int readUnsignedVarInt(Packet packet) {
        int value = 0;
        int i = 0;
        int b;
        while (((b = packet.readByte()) & 0x80) != 0) {
            value |= (b & 0x7F) << i;
            i += 7;
            if (i > 35) {
                throw new IllegalArgumentException("Variable length quantity is too long");
            }
        }
        return value | (b << i);
    }

    /**
     * Reads a signed varint
     * 
     * @return A signed varint
     */
    public static int readSignedVarInt(Packet packet) {
        int raw = readUnsignedVarInt(packet);
        int temp = (((raw << 31) >> 31) ^ raw) >> 1;
        return temp ^ (raw & (1 << 31));
    }

    /**
     * Reads an unsigned varlong
     * 
     * @return An unsigned varlong
     */
    public static long readUnsignedVarLong(Packet packet) {
        long value = 0L;
        int i = 0;
        long b;
        while (((b = packet.readByte()) & 0x80L) != 0) {
            value |= (b & 0x7F) << i;
            i += 7;
            if (i > 63) {
                throw new IllegalArgumentException("Variable length quantity is too long");
            }
        }
        return value | (b << i);
    }

    /**
     * Reads a signed varlong
     * 
     * @return A signed varlong
     */
    public static long readSignedVarLong(Packet packet) {
        long raw = readUnsignedVarLong(packet);
        long temp = (((raw << 63) >> 63) ^ raw) >> 1;
        return temp ^ (raw & (1L << 63));
    }

    /**
     * Writes an unsigned varint
     * 
     * @param varInt
     *            The varint to write
     */
    public static void writeUnsignedVarInt(Packet packet, int varInt) {
        while ((varInt & 0xFFFFFF80) != 0L) {
            packet.writeByte((varInt & 0x7F) | 0x80);
            varInt >>>= 7;
        }
        packet.writeByte(varInt & 0x7F);
    }

    /**
     * Writes a signed varint
     * 
     * @param varInt
     *            The varint to write
     */
    public static void writeSignedVarInt(Packet packet, int varInt) {
        writeUnsignedVarInt(packet, (varInt << 1) ^ (varInt >> 31));
    }

    /**
     * Writes an unsigned varlong
     * 
     * @param varLong
     *            The varlong to write
     * @return The packet
     */
    public static void writeUnsignedVarLong(Packet packet, long varLong) {
        while ((varLong & 0xFFFFFFFFFFFFFF80L) != 0L) {
            packet.writeByte(((int) varLong & 0x7F) | 0x80);
            varLong >>>= 7;
        }
        packet.writeByte((int) varLong & 0x7F);
    }

    /**
     * Writes a signed varlong
     * 
     * @param varLong
     *            The varlong to write
     */
    public static void writeSignedVarLong(Packet packet, long varLong) {
        writeUnsignedVarLong(packet, (varLong << 1) ^ (varLong >> 63));
    }

}