package net.glowstone.entity;

import com.google.common.collect.ImmutableBiMap;
import net.glowstone.entity.monster.*;
import net.glowstone.entity.objects.GlowArmorStand;
import net.glowstone.entity.objects.GlowFallingBlock;
import net.glowstone.entity.objects.GlowItem;
import net.glowstone.entity.objects.GlowItemFrame;
import net.glowstone.entity.passive.*;
import net.glowstone.entity.passive.horse.*;
import org.bukkit.entity.EntityType;

import static org.bukkit.entity.EntityType.*;

public class EntityRegistry {

    public static final ImmutableBiMap<EntityType, Class<? extends GlowEntity>> ENTITIES =
            ImmutableBiMap.<EntityType, Class<? extends GlowEntity>>builder()
                    .put(ARMOR_STAND, GlowArmorStand.class)
                    //TODO: Arrow
                    .put(BAT, GlowBat.class)
                    .put(BLAZE, GlowBlaze.class)
                    //TODO: Boat
                    .put(CAVE_SPIDER, GlowCaveSpider.class)
                    .put(CHICKEN, GlowChicken.class)
                    .put(COW, GlowCow.class)
                    .put(CREEPER, GlowCreeper.class)
                    .put(DROPPED_ITEM, GlowItem.class)
                    //TODO: Egg
                    //TODO: Ender Crystal
                    //TODO: Ender Dragon
                    //TODO: Ender PEarl
                    //TODO: Ender Signal
                    .put(ENDERMAN, GlowEnderman.class)
                    .put(ENDERMITE, GlowEndermite.class)
                    //TODO: Experience orb
                    .put(FALLING_BLOCK, GlowFallingBlock.class)
                    //TODO: Fireball
                    //TODO: Firework
                    //TODO: Fishing hook
                    .put(GHAST, GlowGhast.class)
                    .put(GIANT, GlowGiant.class)
                    .put(GUARDIAN, GlowGuardian.class)
                    .put(ELDER_GUARDIAN, GlowElderGuardian.class)
                    .put(HORSE, GlowHorse.class)
                    .put(ZOMBIE_HORSE, GlowZombieHorse.class)
                    .put(SKELETON_HORSE, GlowSkeletonHorse.class)
                    .put(DONKEY, GlowDonkey.class)
                    .put(MULE, GlowMule.class)
                    .put(IRON_GOLEM, GlowIronGolem.class)
                    .put(ITEM_FRAME, GlowItemFrame.class)
                    //TODO: Leash hitch
                    //TODO: Lightning
                    .put(MAGMA_CUBE, GlowMagmaCube.class)
                    //TODO: Minecarts
                    .put(MUSHROOM_COW, GlowMooshroom.class)
                    .put(OCELOT, GlowOcelot.class)
                    //TODO: Painting
                    .put(PIG, GlowPig.class)
                    .put(PIG_ZOMBIE, GlowPigZombie.class)
                    .put(PLAYER, GlowPlayer.class)
                    .put(POLAR_BEAR, GlowPolarBear.class)
                    .put(PRIMED_TNT, GlowTNTPrimed.class)
                    .put(RABBIT, GlowRabbit.class)
                    .put(SHEEP, GlowSheep.class)
                    .put(SILVERFISH, GlowSilverfish.class)
                    .put(SKELETON, GlowSkeleton.class)
                    .put(WITHER_SKELETON, GlowWitherSkeleton.class)
                    .put(STRAY, GlowStray.class)
                    .put(SLIME, GlowSlime.class)
                    //TODO: Fireball
                    //TODO: Snowball
                    .put(SNOWMAN, GlowSnowman.class)
                    .put(SPIDER, GlowSpider.class)
                    //TODO: Splash potion
                    .put(SQUID, GlowSquid.class)
                    //TODO: Experience bottle
                    .put(VILLAGER, GlowVillager.class)
                    .put(WEATHER, GlowWeather.class)
                    .put(WITCH, GlowWitch.class)
                    //TODO: Wither
                    //TODO: Wither Skull
                    .put(WOLF, GlowWolf.class)
                    .put(ZOMBIE, GlowZombie.class)
                    .put(HUSK, GlowHusk.class)
                    .put(ZOMBIE_VILLAGER, GlowZombieVillager.class)
                    .build();

    public static Class<? extends GlowEntity> getEntity(short id) {
        return ENTITIES.get(fromId(id));
    }

}
