package com.jothapunkt.spigot.raftcraft.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.items.generic.VanillaItem;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ItemRegistry {
    public static CustomItem get(CustomItemIdentifier identifier) {
        return identifier.getItem();
    }

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

        if (Arrays.stream(CustomItemIdentifier.values()).map(Enum::name).collect(Collectors.toList()).contains(name)) {
            return get(CustomItemIdentifier.valueOf(name));
        }

        if (Arrays.stream(Material.values()).map(Enum::name).collect(Collectors.toList()).contains(name)) {
            return get(Material.valueOf(name));
        }

        return null;
    }

    public static CustomItem get(ItemStack item) {
        if (item == null) {
            return null;
        }
        
        if (!item.hasItemMeta()) {
            return get(item.getType());
        }

        if (!item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(RaftCraft.getInstance(), "item_identifier"))) {
            return get(item.getType());
        }

        String identifier = item.getItemMeta()
                                .getPersistentDataContainer()
                                .get(
                                    new NamespacedKey(RaftCraft.getInstance(), "item_identifier"),
                                    PersistentDataType.STRING
                                );
        
        return get(identifier);
    }
}
