package net.pocketdreams.sequinland.entity.data;

import net.pocketdreams.sequinland.entity.PocketEntity;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class LongEntityData extends EntityData<Long> {
    public long data;

    public LongEntityData(int id, long data) {
        super(id);
        this.data = data;
    }

    public Long getData() {
        return data;
    }

    public void setData(Long data) {
        this.data = data;
    }

    @Override
    public int getType() {
        return PocketEntity.DATA_TYPE_LONG;
    }
}
