package net.pocketdreams.sequinland.util;

import java.lang.reflect.Field;

public class ReflectionUtils {
    public static Field findUnderlying(Class<?> clazz, String fieldName) {
        Class<?> current = clazz;
        do {
           try {
               return current.getDeclaredField(fieldName);
           } catch(Exception e) {}
        } while((current = current.getSuperclass()) != null);
        return null;
    }
}
