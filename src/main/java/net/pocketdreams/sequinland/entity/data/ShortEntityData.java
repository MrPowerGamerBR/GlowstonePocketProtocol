package net.pocketdreams.sequinland.entity.data;

import net.pocketdreams.sequinland.entity.PocketEntity;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class ShortEntityData extends EntityData<Integer> {
    public int data;

    public ShortEntityData(int id, int data) {
        super(id);
        this.data = data;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        if (data == null) {
            this.data = 0;
        } else {
            this.data = data;
        }
    }

    @Override
    public int getType() {
        return PocketEntity.DATA_TYPE_SHORT;
    }
}
