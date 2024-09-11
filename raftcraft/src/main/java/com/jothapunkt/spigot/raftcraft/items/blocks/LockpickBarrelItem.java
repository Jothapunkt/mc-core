package com.jothapunkt.spigot.raftcraft.items.blocks;

import org.bukkit.Material;
import org.bukkit.block.Barrel;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.blocks.LockpickBarrel;
import com.jothapunkt.spigot.raftcraft.blocks.PreservingJarBlock;
import com.jothapunkt.spigot.raftcraft.blocks.generic.CustomBlock;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomBlockItem;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.types.Rarity;

public class LockpickBarrelItem extends CustomBlockItem<Barrel> {
    public LockpickBarrelItem() {
        name = "Lockpick Barrel";
        baseItem = new ItemStack(Material.BARREL);
        rarity = Rarity.UNCOMMON;
    }

    @Override
    public CustomBlock<Barrel> getCustomBlock() {
        return new LockpickBarrel();
    }

}
