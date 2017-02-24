package net.glowstone.net.message.play.game;

import com.flowpowered.network.Message;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import net.glowstone.GlowChunk;
import net.glowstone.util.nbt.CompoundTag;

@Data
public final class ChunkDataMessage implements Message {

    private final int x, z;
    private final boolean continuous;
    private final int primaryMask;
    private final ByteBuf data;
    private final CompoundTag[] tileEntities;
    private GlowChunk chunk; // SequinLand
    
    public ChunkDataMessage(int x, int z, boolean continuous, int primaryMask, ByteBuf data, CompoundTag[] tileEntities, GlowChunk glowChunk) {
        this.x = x;
        this.z = z;
        this.continuous = continuous;
        this.primaryMask = primaryMask;
        this.data = data;
        this.tileEntities = tileEntities;
        this.chunk = glowChunk;
    }
}
