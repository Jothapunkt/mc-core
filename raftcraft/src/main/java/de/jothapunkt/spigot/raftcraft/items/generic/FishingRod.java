package com.jothapunkt.spigot.raftcraft.items.generic;

import org.bukkit.Material;
import org.bukkit.entity.FishHook;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.types.Rarity;

public class FishingRod extends EquipmentItem {
    public FishingRod() {
        type = "Fishing Rod";
        rarity = Rarity.RARE;
        baseItem = new ItemStack(Material.FISHING_ROD);
    }

    public void onWaterHit(FishHook hook) {}
    public void onUpdate(FishHook hook) {}
}
