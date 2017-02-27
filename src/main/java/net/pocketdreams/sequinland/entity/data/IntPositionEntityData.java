package net.pocketdreams.sequinland.entity.data;

import org.bukkit.util.BlockVector;
import net.pocketdreams.sequinland.entity.PocketEntity;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class IntPositionEntityData extends EntityData<BlockVector> {
    public int x;
    public int y;
    public int z;

    public IntPositionEntityData(int id, int x, int y, int z) {
        super(id);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public IntPositionEntityData(int id, BlockVector pos) {
        this(id, (int) pos.getBlockX(), (int) pos.getBlockY(), (int) pos.getBlockZ());
    }

    @Override
    public BlockVector getData() {
        return new BlockVector(x, y, z);
    }

    @Override
    public void setData(BlockVector data) {
        if (data != null) {
            this.x = data.getBlockX();
            this.y = data.getBlockY();
            this.z = data.getBlockZ();
        }
    }

    @Override
    public int getType() {
        return PocketEntity.DATA_TYPE_POS;
    }
}
