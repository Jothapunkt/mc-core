package com.jothapunkt.spigot.raftcraft.util;

import java.util.UUID;

import org.apache.commons.lang3.NotImplementedException;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.Metadatable;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.RaftCraft;

public class CustomEntity<T extends Entity> extends CustomClass<T> {
    protected boolean persistent = false;

    @Override
    public void setKey(T entity) {
        super.setKey(entity);
        entity.getPersistentDataContainer().set(new NamespacedKey(RaftCraft.getInstance(), "persistent"), PersistentDataType.BOOLEAN, persistent);
    }

    @Override
    protected T instantiateRaw(Location location) {
        throw new NotImplementedException();
    }

    @Override
    public T instantiate(Location location) {
        T holder = instantiateRaw(location);
        setKey(holder);
        return holder;
    }

    public void onInterval() {}
}
