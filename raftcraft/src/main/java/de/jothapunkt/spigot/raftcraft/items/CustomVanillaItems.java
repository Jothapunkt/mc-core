package com.jothapunkt.spigot.raftcraft.items;

import org.bukkit.Material;

import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.items.vanilla.LeatherBoots;
import com.jothapunkt.spigot.raftcraft.items.vanilla.LeatherChestplate;
import com.jothapunkt.spigot.raftcraft.items.vanilla.LeatherHelmet;
import com.jothapunkt.spigot.raftcraft.items.vanilla.LeatherLeggings;
import com.jothapunkt.spigot.raftcraft.items.vanilla.IronSword;

// Mapping of custom items to replace vanilla items
public enum CustomVanillaItems {
    IRON_SWORD(new IronSword()),
    LEATHER_HELMET(new LeatherHelmet()),
    LEATHER_CHESTPLATE(new LeatherChestplate()),
    LEATHER_LEGGINGS(new LeatherLeggings()),
    LEATHER_BOOTS(new LeatherBoots());

    private CustomItem item;

    private CustomVanillaItems(CustomItem item) {
        this.item = item;
    }

    public static CustomItem find(Material material) {
        try {
            return valueOf(material.name()).getItem();
        } catch(IllegalArgumentException e) {
            return null;
        }
    }

    public CustomItem getItem() {
        return item;
    }
}
