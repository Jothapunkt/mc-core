package com.jothapunkt.spigot.raftcraft.util;

import java.util.UUID;

import org.apache.commons.lang3.NotImplementedException;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.RaftCraft;

public class CustomClass<T extends PersistentDataHolder> {
    public String getKey() {
        return this.getClass().getName();
    }
    
    public void setKey(T holder) {
        holder.getPersistentDataContainer().set(new NamespacedKey(RaftCraft.getInstance(), "custom_class"), PersistentDataType.STRING, getKey());
    }

    protected T instantiateRaw(Location location) {
        throw new NotImplementedException();
    }

    public T instantiate(Location location) {
        T holder = instantiateRaw(location);
        setKey(holder);
        return holder;
    }

    public void onInterval() {}
}
