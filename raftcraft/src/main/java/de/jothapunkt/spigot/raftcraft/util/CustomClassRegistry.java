package com.jothapunkt.spigot.raftcraft.util;

import java.util.HashMap;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.bukkit.NamespacedKey;
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

    public CustomClass get(PersistentDataHolder instance) {
        if (instance.getPersistentDataContainer().has(new NamespacedKey(RaftCraft.getInstance(), "custom_class"))) {
            String key = instance.getPersistentDataContainer().get(new NamespacedKey(RaftCraft.getInstance(), "custom_class"), PersistentDataType.STRING);
            return registry.get(key);
        }

        return null;
    }
}
