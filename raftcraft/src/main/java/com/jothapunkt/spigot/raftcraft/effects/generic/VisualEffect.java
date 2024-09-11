package com.jothapunkt.spigot.raftcraft.effects.generic;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import com.jothapunkt.spigot.raftcraft.RaftCraft;

public class VisualEffect {
    protected int duration = 60; // In Ticks
    protected int age = 0;
    protected UUID id = UUID.randomUUID();
    protected HashMap<String, Entity> parts = new HashMap<>();
    protected Location location;

    // Track last tick time to avoid accidentally triggering 
    protected long lastTick = System.currentTimeMillis();
    
    public VisualEffect(Location location) {
        this.location = location.clone();
    }

    public void addPart(String name, Entity entity) {
        entity.getPersistentDataContainer().set(
            new NamespacedKey(RaftCraft.getInstance(), "expirationTime"),
            PersistentDataType.LONG,
            System.currentTimeMillis() + 500 + duration * 50
        );

        entity.setMetadata("visualEffect", new FixedMetadataValue(RaftCraft.getInstance(), this));

        parts.put(name, entity);
    }

    public Entity getPart(String name) {
        return parts.get(name);
    }

    public Location getLocation() {
        return location;
    }

    public int getAge() {
        return age;
    }

    public void tick() {
        if (System.currentTimeMillis() - lastTick < 30) {
            return;
        }

        this.age++;
        this.lastTick = System.currentTimeMillis();

        if (age > duration) {
            onExpire();
        }
    }

    public void onExpire() {
        for (Entity part : parts.values()) {
            part.remove();
        }
    }

    public void move(Vector offset) {
        location.add(offset);

        for (Entity part : parts.values()) {
            part.teleport(part.getLocation().add(offset));
        }
    }

    public void extend(int ticks) {
        duration = age + ticks;

        for (Entity part : parts.values()) {
            part.getPersistentDataContainer().set(
                new NamespacedKey(RaftCraft.getInstance(), "expirationTime"),
                PersistentDataType.LONG,
                System.currentTimeMillis() + 500 + ticks * 50
            );
        }
    }
}
