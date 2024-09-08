package com.jothapunkt.spigot.raftcraft.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.RaftCraft;


public class CustomClassRegistry {
    private static CustomClassRegistry instance = null;
    private HashMap<String, CustomClass> registry = new HashMap<>();

    public static CustomClassRegistry getInstance() {
        if (instance == null) {
            instance = new CustomClassRegistry();
        }

        return instance;
    }

    private CustomClassRegistry() {}

    public void register(CustomClass instance) {
        String key = instance.getClass().getName();

        if (registry.keySet().contains(key)) {
            throw new KeyAlreadyExistsException(key);
        }

        registry.put(key, instance);
    }

    public CustomClass get(ItemStack item) {
        if (item == null) {
            return null;
        }

        return get(item.getItemMeta());
    }

    public CustomClass get(Block block) {
        if (block == null) {
            return null;
        }

        if (block.getState() instanceof PersistentDataHolder holder) {
            return get(holder);
        }

        return null;
    }

    public CustomClass get(PersistentDataHolder instance) {
        if (instance == null) {
            return null;
        }
        
        if (instance.getPersistentDataContainer().has(new NamespacedKey(RaftCraft.getInstance(), "custom_class"))) {
            String key = instance.getPersistentDataContainer().get(new NamespacedKey(RaftCraft.getInstance(), "custom_class"), PersistentDataType.STRING);
            return registry.get(key);
        }

        return null;
    }

    public <T extends CustomClass> List<T> getSubClasses(Class<T> parentClass) {
        List<T> children = new ArrayList<>();
        Bukkit.broadcastMessage("Looking for subclasses of " + parentClass.getName());
        for (CustomClass entry : registry.values()) {
            Bukkit.broadcastMessage(entry.getKey());
            if (parentClass.isInstance(entry)) {
                children.add((T) entry);
            }
        }

        return children;
    }
}
