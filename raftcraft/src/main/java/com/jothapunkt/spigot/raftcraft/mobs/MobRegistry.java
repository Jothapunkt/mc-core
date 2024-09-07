package com.jothapunkt.spigot.raftcraft.mobs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import com.jothapunkt.spigot.raftcraft.mobs.generic.CustomMob;
import com.jothapunkt.spigot.raftcraft.mobs.generic.VanillaMob;
import com.jothapunkt.spigot.raftcraft.util.CustomClass;
import com.jothapunkt.spigot.raftcraft.util.CustomClassRegistry;

import java.util.Arrays;
import java.util.stream.Collectors;


public class MobRegistry {
    public static CustomMob get(EntityType type) {
        return new VanillaMob(type);
    }

    public static CustomMob get(String string) {
        String name = string.toUpperCase().replace(" ", "_");

        if (Arrays.stream(EntityType.values()).map(Enum::name).collect(Collectors.toList()).contains(name)) {
            return get(EntityType.valueOf(name));
        }

        name = string.toUpperCase().replace(" ", "");

        for (CustomMob mob : CustomClassRegistry.getInstance().getSubClasses(CustomMob.class)) {
            if (name.equalsIgnoreCase(mob.getClass().getSimpleName()) || name.equalsIgnoreCase(mob.getClass().getName())) {
                return mob;
            }
        }

        return null;
    }

    public static CustomMob get(Entity entity) {
        if (entity == null) {
            return null;
        }

        CustomClass entry = CustomClassRegistry.getInstance().get(entity);
        
        if (entry instanceof CustomMob customMob) {
            return customMob;
        }

        if (entry == null) {
            return get(entity.getType());
        }

        return null;
    }
}
