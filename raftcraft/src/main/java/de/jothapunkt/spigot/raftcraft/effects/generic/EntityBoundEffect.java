package com.jothapunkt.spigot.raftcraft.effects.generic;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.util.CustomClass;

public class EntityBoundEffect extends VisualEffect {
    protected UUID entityUuid;
    protected Location lastEntityLocation;

    public EntityBoundEffect(Entity entity) {
        super(entity.getLocation());
        entityUuid = entity.getUniqueId();
        lastEntityLocation = entity.getLocation().clone();
    }

    public void tick() {
        super.tick();

        Entity entity = Bukkit.getEntity(entityUuid);

        if (entity == null) {
            Bukkit.broadcastMessage("expiring because no bound entity found");
            onExpire();
        } else {
            Vector offset = entity.getLocation().subtract(lastEntityLocation).toVector();
            move(offset);
            lastEntityLocation = entity.getLocation();
        }
    }
}
