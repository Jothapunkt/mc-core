package com.jothapunkt.spigot.raftcraft.items.generic;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.util.Strings;

public class VanillaItem extends CustomItem {
    public VanillaItem(Material material) {
        baseItem = new ItemStack(material);
        name = Strings.snakeToCamelCase(material.name());
    }

    @Override
    public String getIdentifer() {
        return baseItem.getType().name();
    }
}
