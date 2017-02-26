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
        attributes.put(AttributeName.HEALTH, new PocketAttribute(AttributeName.HEALTH.getName(), 0F, 20F, 20F, 20F));
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
        HEALTH("minecraft:health");
        
        String name;
        
        AttributeName(String name) {
            this.name = name;
        }
    }
}
