package com.jothapunkt.spigot.raftcraft.util;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.RaftCraft;

public class Markers {
    public static LivingEntity target(Location location, double duration) {
        LivingEntity marker = (LivingEntity) location.getWorld().spawnEntity(location, EntityType.BAT);

        marker.setAI(false);
        marker.setInvisible(false);
        marker.setInvulnerable(true);
        marker.setGlowing(true);

        // Set expiration time
        marker.getPersistentDataContainer().set(
            new NamespacedKey(RaftCraft.getInstance(), "expirationTime"),
            PersistentDataType.LONG,
            System.currentTimeMillis() + (int) (duration * 1000)
        );

        return marker;
    }
}
