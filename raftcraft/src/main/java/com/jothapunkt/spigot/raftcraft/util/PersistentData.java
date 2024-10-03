package com.jothapunkt.spigot.raftcraft.util;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.units.qual.C;

import com.comphenix.protocol.wrappers.Pair;
import com.jothapunkt.spigot.raftcraft.RaftCraft;

public class PersistentData {
    PersistentDataContainer container;

    public PersistentData(PersistentDataContainer container) {
        this.container = container;
    }

    public static PersistentData from(PersistentDataHolder holder) {
        return new PersistentData(holder.getPersistentDataContainer());
    }

    public static PersistentData from(ItemStack item) {
        return new PersistentData(item.getItemMeta().getPersistentDataContainer());
    }

    public Pair<PersistentDataContainer, NamespacedKey> getLocation(String... key) {
        PersistentDataContainer container = this.container;

        for (int i = 0; i < key.length - 1; i++) {
            container = container.getOrDefault(new NamespacedKey(RaftCraft.getInstance(), key[i]), PersistentDataType.TAG_CONTAINER, container.getAdapterContext().newPersistentDataContainer());
        }

        return new Pair<PersistentDataContainer,NamespacedKey>(container, new NamespacedKey(RaftCraft.getInstance(), key[key.length - 1]));
    }

    public <P, C> void set(C value, PersistentDataType<P, C> type, String... key) {
        Pair<PersistentDataContainer, NamespacedKey> location = getLocation(key);
        location.getFirst().set(location.getSecond(), type, value);
    }

    public <P, C> C get(PersistentDataType<P, C> type, String... key) {
        Pair<PersistentDataContainer, NamespacedKey> location = getLocation(key);
        return location.getFirst().get(location.getSecond(), type);
    }

    public ItemStack getItem(String... key) {
        Pair<PersistentDataContainer, NamespacedKey> location = getLocation(key);
        String data = location.getFirst().get(location.getSecond(), PersistentDataType.STRING);

        if (data == null) {
            return null;
        }

        try {
            return Deserialize.item(data);
        } catch(IOException e) {
            Bukkit.getLogger().warning(e.toString());
            return null;
        } catch(ClassNotFoundException e) {
            Bukkit.getLogger().warning(e.toString());
            return null;
        }
    }

    public List<ItemStack> getItems(String... key) {
        Pair<PersistentDataContainer, NamespacedKey> location = getLocation(key);
        String data = location.getFirst().get(location.getSecond(), PersistentDataType.STRING);

        if (data == null) {
            return new ArrayList<>();
        }

        try {
            return Deserialize.items(data);
        } catch(IOException e) {
            Bukkit.getLogger().warning(e.toString());
            return new ArrayList<>();
        } catch(ClassNotFoundException e) {
            Bukkit.getLogger().warning(e.toString());
            return new ArrayList<>();
        }
    }

    public void setItem(ItemStack item, String... key) {
        try {
            set(Serialize.base64(item), key);
        } catch(IOException e) {
            Bukkit.getLogger().warning(e.toString());
        }
        
    }

    public void setItems(List<ItemStack> items, String... key) {
        try {
            set(Serialize.listToBase64(items), key);
        } catch(IOException e) {
            Bukkit.getLogger().warning(e.toString());
        }
    }

    public void addItem(ItemStack item, String... key) {
        List<ItemStack> items = getItems(key);
        items.add(item);
        setItems(items, key);
    }

    public void set(String value, String... key) {
        set(value, PersistentDataType.STRING, key);
    }

    public void set(Double value, String... key) {
        set(value, PersistentDataType.DOUBLE, key);
    }
}
