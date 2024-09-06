package com.jothapunkt.spigot.raftcraft.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.items.generic.VanillaItem;
import com.jothapunkt.spigot.raftcraft.mobs.generic.CustomMob;
import com.jothapunkt.spigot.raftcraft.util.CustomClass;
import com.jothapunkt.spigot.raftcraft.util.CustomClassRegistry;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ItemRegistry {
    public static CustomItem get(Material material) {
        CustomItem customVanillaItem = CustomVanillaItems.find(material);
        
        if (customVanillaItem == null) {
            return new VanillaItem(material);
        } else {
            return customVanillaItem;
        }
    }

    public static CustomItem get(String string) {
        String name = string.toUpperCase().replace(" ", "_");

        if (Arrays.stream(Material.values()).map(Enum::name).collect(Collectors.toList()).contains(name)) {
            return get(Material.valueOf(name));
        }

        name = string.toUpperCase().replace(" ", "");

        for (CustomItem item : CustomClassRegistry.getInstance().getSubClasses(CustomItem.class)) {
            if (name.equalsIgnoreCase(item.getClass().getSimpleName())) {
                return item;
            }
        }

        return null;
    }

    public static CustomItem get(ItemStack item) {
        if (item == null) {
            return null;
        }
        
        CustomClass entry = CustomClassRegistry.getInstance().get(item);

        if (entry instanceof CustomItem customItem) {
            return customItem;
        }

        if (entry == null) {
            return get(item.getType());
        }

        return null;
    }
}
