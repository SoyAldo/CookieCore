package me.soyaldo.cookiecore.utils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class EntityUtil {

    public static boolean arePlayers(Entity... entities) {
        return areGeneric(EntityType.PLAYER, entities);
    }

    public static boolean areGeneric(EntityType entityType, Entity... entities) {
        for (Entity entity : entities) {
            if (entity == null) return false;
            if (!entity.getType().equals(entityType)) return false;
        }
        return true;
    }

}