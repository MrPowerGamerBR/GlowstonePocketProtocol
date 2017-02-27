package net.pocketdreams.sequinland.entity;

import net.pocketdreams.sequinland.entity.data.EntityMetadata;

public class PocketEntity {
    public static final int DATA_TYPE_BYTE = 0;
    public static final int DATA_TYPE_SHORT = 1;
    public static final int DATA_TYPE_INT = 2;
    public static final int DATA_TYPE_FLOAT = 3;
    public static final int DATA_TYPE_STRING = 4;
    public static final int DATA_TYPE_SLOT = 5;
    public static final int DATA_TYPE_POS = 6;
    public static final int DATA_TYPE_LONG = 7;
    public static final int DATA_TYPE_VECTOR3F = 8;

    public static final int DATA_FLAGS = 0;  //long
    //1 (int)
    public static final int DATA_VARIANT = 2; //int
    public static final int DATA_COLOUR = 3; //byte
    public static final int DATA_NAMETAG = 4; //string
    public static final int DATA_OWNER_EID = 5; //long

    public static final int DATA_AIR = 7; //short
    public static final int DATA_POTION_COLOR = 8; //int (ARGB!)
    public static final int DATA_POTION_AMBIENT = 9; //byte
    /* 27 (byte) something to do with beds
     * 28 (int)
     * 29 (block coords) bed position */
    public static final int DATA_LEAD_HOLDER_EID = 38; //long
    public static final int DATA_SCALE = 39; //float
    public static final int DATA_INTERACTIVE_TAG = 40; //string (button text)
    /* 41 (long) */
    public static final int DATA_URL_TAG = 43; //string
    public static final int DATA_MAX_AIR = 44; //short
    public static final int DATA_MARK_VARIANT = 45; //int
    /* 46 (byte)
     * 47 (int)
     * 48 (int)
     * 49 (long)
     * 50 (long)
     * 51 (long)
     * 52 (short)
     * 53 (unknown) */
    public static final int DATA_BOUNDING_BOX_WIDTH = 54; //float
    public static final int DATA_BOUNDING_BOX_HEIGHT = 55; //float
    public static final int DATA_FUSE_LENGTH = 56; //int
    /* 56 (vector3f)
     * 57 (byte)
     * 58 (float)
     * 59 (float) */

    public static final int DATA_FLAG_ONFIRE = 0;
    public static final int DATA_FLAG_SNEAKING = 1;
    public static final int DATA_FLAG_RIDING = 2;
    public static final int DATA_FLAG_SPRINTING = 3;
    public static final int DATA_FLAG_ACTION = 4;
    public static final int DATA_FLAG_INVISIBLE = 5;
    public static final int DATA_FLAG_TEMPTED = 6; //???
    public static final int DATA_FLAG_INLOVE = 7;
    public static final int DATA_FLAG_SADDLED = 8;
    public static final int DATA_FLAG_POWERED = 9;
    public static final int DATA_FLAG_IGNITED = 10; //for creepers?
    public static final int DATA_FLAG_BABY = 11;
    public static final int DATA_FLAG_CONVERTING = 12; //???
    public static final int DATA_FLAG_CRITICAL = 13;
    public static final int DATA_FLAG_CAN_SHOW_NAMETAG = 14;
    public static final int DATA_FLAG_ALWAYS_SHOW_NAMETAG = 15;
    public static final int DATA_FLAG_IMMOBILE = 16, DATA_FLAG_NO_AI = 16;
    public static final int DATA_FLAG_SILENT = 17;
    public static final int DATA_FLAG_WALLCLIMBING = 18;
    public static final int DATA_FLAG_RESTING = 19; //for bats?
    public static final int DATA_FLAG_SITTING = 20;
    public static final int DATA_FLAG_ANGRY = 21;
    public static final int DATA_FLAG_INTERESTED = 22; //for mobs following players with food?
    public static final int DATA_FLAG_CHARGED = 23;
    public static final int DATA_FLAG_TAMED = 24;
    public static final int DATA_FLAG_LEASHED = 25;
    public static final int DATA_FLAG_SHEARED = 26; //for sheep
    public static final int DATA_FLAG_GLIDING = 27, DATA_FLAG_FALL_FLYING = 27;
    public static final int DATA_FLAG_ELDER = 28; //elder guardian
    public static final int DATA_FLAG_MOVING = 29;
    public static final int DATA_FLAG_BREATHING = 30; //hides bubbles if true
    public static final int DATA_FLAG_CHESTED = 31; //for mules?
    public static final int DATA_FLAG_STACKABLE = 32;
    public static final int DATA_FLAG_IDLING = 36;

    public static final int DATA_LEAD_HOLDER = 23;
    public static final int DATA_LEAD = 24;
    
    public static EntityMetadata getDefaultMetadata() {
        return new EntityMetadata()
                .putLong(DATA_FLAGS, 0)
                .putShort(DATA_AIR, 400)
                .putShort(DATA_MAX_AIR, 400)
                .putString(DATA_NAMETAG, "")
                .putLong(DATA_LEAD_HOLDER_EID, -1)
                .putFloat(DATA_SCALE, 1f);
    }
}
