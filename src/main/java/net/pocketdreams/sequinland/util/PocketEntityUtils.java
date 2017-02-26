package net.pocketdreams.sequinland.util;

import java.util.HashMap;

import org.bukkit.entity.EntityType;

public class PocketEntityUtils {
    private static final HashMap<EntityType, Integer> typeToPocket = new HashMap<EntityType, Integer>();
    
    static {
        // Misc
        typeToPocket.put(EntityType.DROPPED_ITEM, 64);
        typeToPocket.put(EntityType.EXPERIENCE_ORB, 69);
        typeToPocket.put(EntityType.PRIMED_TNT, 65);
        typeToPocket.put(EntityType.FALLING_BLOCK, 66);
        typeToPocket.put(EntityType.THROWN_EXP_BOTTLE, 68);
        typeToPocket.put(EntityType.ENDER_SIGNAL, 70);
        typeToPocket.put(EntityType.ENDER_CRYSTAL, 71);
        typeToPocket.put(EntityType.SHULKER_BULLET, 76);
        typeToPocket.put(EntityType.FISHING_HOOK, 77);
        typeToPocket.put(EntityType.DRAGON_FIREBALL, 79);
        typeToPocket.put(EntityType.ARROW, 80);
        typeToPocket.put(EntityType.SNOWBALL, 81);
        typeToPocket.put(EntityType.EGG, 82);
        typeToPocket.put(EntityType.PAINTING, 83);
        typeToPocket.put(EntityType.MINECART, 84);
        typeToPocket.put(EntityType.FIREBALL, 85);
        typeToPocket.put(EntityType.SPLASH_POTION, 86);
        typeToPocket.put(EntityType.ENDER_PEARL, 87);
        typeToPocket.put(EntityType.LEASH_HITCH, 88);
        typeToPocket.put(EntityType.WITHER_SKULL, 89);
        typeToPocket.put(EntityType.BOAT, 90);
        typeToPocket.put(EntityType.LIGHTNING, 93);
        typeToPocket.put(EntityType.SMALL_FIREBALL, 94);
        typeToPocket.put(EntityType.AREA_EFFECT_CLOUD, 95);
        typeToPocket.put(EntityType.MINECART_HOPPER, 96);
        typeToPocket.put(EntityType.MINECART_TNT, 97);
        typeToPocket.put(EntityType.MINECART_CHEST, 98);
        typeToPocket.put(EntityType.LINGERING_POTION, 101);
        
        // Hostile
        typeToPocket.put(EntityType.ZOMBIE, 32);
        typeToPocket.put(EntityType.CREEPER, 33);
        typeToPocket.put(EntityType.SKELETON, 34);
        typeToPocket.put(EntityType.SPIDER, 35);
        typeToPocket.put(EntityType.PIG_ZOMBIE, 36);
        typeToPocket.put(EntityType.SLIME, 37);
        typeToPocket.put(EntityType.ENDERMAN, 38);
        typeToPocket.put(EntityType.SILVERFISH, 39);
        typeToPocket.put(EntityType.CAVE_SPIDER, 40);
        typeToPocket.put(EntityType.GHAST, 41);
        typeToPocket.put(EntityType.MAGMA_CUBE, 42);
        typeToPocket.put(EntityType.BLAZE, 43);
        typeToPocket.put(EntityType.ZOMBIE_VILLAGER, 44);
        typeToPocket.put(EntityType.WITCH, 45);
        typeToPocket.put(EntityType.STRAY, 46);
        typeToPocket.put(EntityType.HUSK, 47);
        typeToPocket.put(EntityType.WITHER_SKELETON, 48);
        typeToPocket.put(EntityType.GUARDIAN, 49);
        typeToPocket.put(EntityType.ELDER_GUARDIAN, 50);
        typeToPocket.put(EntityType.WITHER, 52);
        typeToPocket.put(EntityType.ENDER_DRAGON, 53);
        typeToPocket.put(EntityType.SHULKER, 54);
        typeToPocket.put(EntityType.ENDERMITE, 55);
        
        // Passive
        typeToPocket.put(EntityType.CHICKEN, 10);
        typeToPocket.put(EntityType.COW, 11);
        typeToPocket.put(EntityType.PIG, 12);
        typeToPocket.put(EntityType.SHEEP, 13);
        typeToPocket.put(EntityType.WOLF, 14);
        typeToPocket.put(EntityType.VILLAGER, 15);
        typeToPocket.put(EntityType.MUSHROOM_COW, 16);
        typeToPocket.put(EntityType.SQUID, 17);
        typeToPocket.put(EntityType.RABBIT, 18);
        typeToPocket.put(EntityType.BAT, 19);
        typeToPocket.put(EntityType.IRON_GOLEM, 20);
        typeToPocket.put(EntityType.SNOWMAN, 21);
        typeToPocket.put(EntityType.OCELOT, 22);
        typeToPocket.put(EntityType.HORSE, 23);
        typeToPocket.put(EntityType.DONKEY, 24);
        typeToPocket.put(EntityType.MULE, 25);
        typeToPocket.put(EntityType.SKELETON_HORSE, 26);
        typeToPocket.put(EntityType.ZOMBIE_HORSE, 27);
        typeToPocket.put(EntityType.POLAR_BEAR, 28);
    }
    
    public static int convertToPocket(EntityType type) {
        return typeToPocket.getOrDefault(type, 32); // Default: Zombie
    }
}
