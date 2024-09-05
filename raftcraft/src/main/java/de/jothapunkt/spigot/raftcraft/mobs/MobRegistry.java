package com.jothapunkt.spigot.raftcraft.mobs;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.mobs.generic.CustomMob;
import com.jothapunkt.spigot.raftcraft.mobs.generic.VanillaMob;
import com.jothapunkt.spigot.raftcraft.util.Meta;

import java.util.Arrays;
import java.util.stream.Collectors;


public class MobRegistry {
    public static CustomMob get(CustomMobIdentifier identifier) {
        return identifier.getMob();
    }

    public static CustomMob get(EntityType type) {
        return new VanillaMob(type);
    }

    public static CustomMob get(String string) {
        String name = string.toUpperCase().replace(" ", "_");

        if (Arrays.stream(CustomMobIdentifier.values()).map(Enum::name).collect(Collectors.toList()).contains(name)) {
            return get(CustomMobIdentifier.valueOf(name));
        }

        if (Arrays.stream(EntityType.values()).map(Enum::name).collect(Collectors.toList()).contains(name)) {
            return get(EntityType.valueOf(name));
        }

        return null;
    }

    public static CustomMob get(Entity entity) {
        if (entity == null) {
            return null;
        }

        if (!entity.getPersistentDataContainer().has(new NamespacedKey(RaftCraft.getInstance(), "mob_identifier"))) {
            return get(entity.getType());
        }

        return get(entity.getPersistentDataContainer().get(new NamespacedKey(RaftCraft.getInstance(), "mob_identifier"), PersistentDataType.STRING));
    }
}
