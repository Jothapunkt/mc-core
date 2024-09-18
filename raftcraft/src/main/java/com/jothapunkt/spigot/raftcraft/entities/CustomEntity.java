package com.jothapunkt.spigot.raftcraft.entities;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;

import com.jothapunkt.spigot.raftcraft.util.CustomClass;

public class CustomEntity<T extends Entity> extends CustomClass<T> {
    protected EntityType type = EntityType.ZOMBIE;

    @Override
    public T instantiateRaw(Location location) {
        return spawn(location);
    }
    
    public T spawn(Location location) {
        T entity = (T) location.getWorld().spawnEntity(location, type, false);
        return entity;
    }
}
