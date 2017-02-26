package net.pocketdreams.sequinland.util;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import net.glowstone.GlowChunk;
import net.pocketdreams.sequinland.util.nukkit.BinaryStream;

public class PocketChunkUtils {
    public static byte[] getBytesForEmptyChunk() {
        ByteBuffer buffer = ByteBuffer.allocate(10240);
        byte[] blocks = new byte[4096];
        byte[] data = new byte[2048];
        byte[] skyLight = new byte[2048];
        byte[] blockLight = new byte[2048];
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int i = (x << 7) | (z << 3);
                for (int y = 0; y < 16; y += 2) {
                    int id = 0;
                    int id2 = 0;
                    blocks[(i << 1) | y] = (byte) id;
                    blocks[(i << 1) | (y + 1)] = (byte) id2;
                    int b1 = 0;
                    int b2 = 0;
                    data[i | (y >> 1)] = (byte) ((b2 << 4) | b1);
                    b1 = 15;
                    b2 = 15;
                    skyLight[i | (y >> 1)] = (byte) ((b2 << 4) | b1);
                    b1 = 15;
                    b2 = 15;
                    blockLight[i | (y >> 1)] = (byte) ((b2 << 4) | b1);
                }
            }
        }
        return buffer
                .put(blocks)
                .put(data)
                .put(skyLight)
                .put(blockLight)
                .array();
    }
    
    public static byte[] requestEmptyChunk() {
        byte[] blockEntities = new byte[0];

        Map<Integer, Integer> extra = new HashMap<>();
        BinaryStream extraData;
        if (!extra.isEmpty()) {
            extraData = new BinaryStream();
            extraData.putVarInt(extra.size());
            for (Map.Entry<Integer, Integer> entry : extra.entrySet()) {
                extraData.putVarInt(entry.getKey());
                extraData.putLShort(entry.getValue());
            }
        } else {
            extraData = null;
        }

        BinaryStream stream = new BinaryStream();
        int count = 16;
        stream.putByte((byte) count);
        for (int i = 0; i < count; i++) {
            stream.putByte((byte) 0);
            stream.put(getBytesForEmptyChunk());
        }
        for (int height : new byte[256]) {
            stream.putByte((byte) height);
        }
        stream.put(new byte[256]);
        stream.put(new byte[256]);
        stream.putByte((byte) 0);
        if (extraData != null) {
            stream.put(extraData.getBuffer());
        } else {
            stream.putVarInt(0);
        }
        stream.put(blockEntities);

        return stream.getBuffer();
    }

    public static byte[] translateToPocket(GlowChunk chunk) {
        byte[] blockEntities = new byte[0];

        Map<Integer, Integer> extra = new HashMap<>();
        BinaryStream extraData;
        if (!extra.isEmpty()) {
            extraData = new BinaryStream();
            extraData.putVarInt(extra.size());
            for (Map.Entry<Integer, Integer> entry : extra.entrySet()) {
                extraData.putVarInt(entry.getKey());
                extraData.putLShort(entry.getValue());
            }
        } else {
            extraData = null;
        }

        BinaryStream stream = new BinaryStream();
        int count = 16;
        stream.putByte((byte) count);
        int currentChkSection = 0;
        for (int i = 0; i < count; i++) {
            stream.putByte((byte) 0);
            stream.put(convertChunkToSection(chunk, currentChkSection));
            currentChkSection++;
        }
        for (int height : new byte[256]) {
            stream.putByte((byte) height);
        }
        stream.put(new byte[256]);
        stream.put(new byte[256]);
        stream.putByte((byte) 0);
        if (extraData != null) {
            stream.put(extraData.getBuffer());
        } else {
            stream.putVarInt(0);
        }
        stream.put(blockEntities);

        return stream.getBuffer();
    }
    
    public static byte[] convertChunkToSection(GlowChunk chk, int currentSection) {
        ByteBuffer buffer = ByteBuffer.allocate(10240);
        byte[] blocks = new byte[4096];
        byte[] data = new byte[2048];
        byte[] skyLight = new byte[2048];
        byte[] blockLight = new byte[2048];
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int i = (x << 7) | (z << 3);
                for (int y = 0; y < 16; y += 2) {
                    int id = chk.getBlock(x, y + (16 * currentSection), z).getTypeId();
                    int id2 = chk.getBlock(x, (y + 1) + (16 * currentSection), z).getTypeId();
                    blocks[(i << 1) | y] = (byte) id;
                    blocks[(i << 1) | (y + 1)] = (byte) id2;
                    int b1 = 0;
                    int b2 = 0;
                    data[i | (y >> 1)] = (byte) ((b2 << 4) | b1);
                    // Here comes the super duper workaround because 0, 16 is outside of the chunk section(tm)!
                    // (patent pending)
                    // TODO: Fix instead of doing this hacky workaround
                    b1 = 15;
                    b2 = 15;
                    try {
                        b1 = chk.getSkyLight(x, y + (16 * currentSection), z);
                        b2 = chk.getSkyLight(x, (y + 1) + (16 * currentSection), z);
                    } catch (Exception e) {
                        
                    }
                    skyLight[i | (y >> 1)] = (byte) ((b2 << 4) | b1);
                    try {
                        b1 = chk.getBlockLight(x, y + (16 * currentSection), z);
                        b2 = chk.getBlockLight(x, (y + 1) + (16 * currentSection), z);
                    } catch (Exception e) {
                        
                    }
                    blockLight[i | (y >> 1)] = (byte) ((b2 << 4) | b1);
                }
            }
        }
        return buffer
                .put(blocks)
                .put(data)
                .put(skyLight)
                .put(blockLight)
                .array();
    }
}
