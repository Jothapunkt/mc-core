package com.jothapunkt.spigot.raftcraft.interfaces;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.types.Difficulty;
import com.jothapunkt.spigot.raftcraft.util.LootTable;


public interface LockpickingTarget {
    public LootTable getLootTable();
    public Difficulty getDifficulty();
    public default ItemStack getLockItem() {
        return new ItemStack(Material.IRON_INGOT);
    }
    public default ItemStack getLootItem() {
        return new ItemStack(Material.CHEST);
    }
    public default ItemStack getBackgroundItem() {
        return new ItemStack(Material.IRON_BARS);
    }
    public default ItemStack getCylinderItem() {
        return new ItemStack(Material.CHAIN);
    }
    public default ItemStack getKeyItem() {
        return new ItemStack(Material.TRIAL_KEY);
    }
}
