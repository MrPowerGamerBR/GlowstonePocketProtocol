package net.pocketdreams.sequinland.util.attributes;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PocketAttribute {
    private String name;
    private float min;
    private float max;
    private float def;
    private float val;
    
    private static final HashMap<AttributeName, PocketAttribute> attributes = new HashMap<>();
    
    static {
        attributes.put(AttributeName.HEALTH, new PocketAttribute(AttributeName.HEALTH.getName(), 0F, 0F, 20F, 20F));
        attributes.put(AttributeName.ABSORPTION, new PocketAttribute(AttributeName.ABSORPTION.getName(), 0F, 4F, 0F, 0F));
        attributes.put(AttributeName.HUNGER, new PocketAttribute(AttributeName.HUNGER.getName(), 0F, 20F, 20F, 20F));
        attributes.put(AttributeName.SATURATION, new PocketAttribute(AttributeName.SATURATION.getName(), 0F, 20F, 5F, 5F));
        attributes.put(AttributeName.EXPERIENCE, new PocketAttribute(AttributeName.EXPERIENCE.getName(), 0F, 1F, 0F, 0F));
        attributes.put(AttributeName.LEVEL, new PocketAttribute(AttributeName.LEVEL.getName(), 0F, 24791F, 0F, 0F));
        attributes.put(AttributeName.SPEED, new PocketAttribute(AttributeName.SPEED.getName(), 0F, 24791F, 0.1F, 0.1F));
        attributes.put(AttributeName.KNOCKBACK_RESISTENCE, new PocketAttribute(AttributeName.KNOCKBACK_RESISTENCE.getName(), 0F, 1F, 0F, 0F));
    }
    
    public static PocketAttribute getAttributeByName(AttributeName name) {
        return attributes.get(name);
    }
    
    @Override
    public PocketAttribute clone() {
        return new PocketAttribute(name, min, max, def, val);
    }
    
    public PocketAttribute withMin(float min) {
        this.min = min;
        return this;
    }
    
    public PocketAttribute withMax(float max) {
        this.max = max;
        return this;
    }
    
    public PocketAttribute withDef(float def) {
        this.def = def;
        return this;
    }
    
    public PocketAttribute withVal(float val) {
        this.val = val;
        return this;
    }
    
    @Getter
    public static enum AttributeName {
        HEALTH("minecraft:health"),
        ABSORPTION("minecraft:generic.absorption"),
        HUNGER("minecraft:player.hunger"),
        SATURATION("minecraft:player.saturation"),
        EXPERIENCE("minecraft:player.experience"),
        LEVEL("minecraft:player.level"),
        SPEED("minecraft:movement"),
        KNOCKBACK_RESISTENCE("minecraft:generic.knockback_resistance");
        
        String name;
        
        AttributeName(String name) {
            this.name = name;
        }
    }
}
