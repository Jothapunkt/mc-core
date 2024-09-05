package com.jothapunkt.spigot.raftcraft.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.Random;

import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.items.generic.VanillaItem;
import com.jothapunkt.spigot.raftcraft.types.LootType;

public class LootItem {
    private LootType type;
    private int minAmount = 1;
    private int maxAmount = 1;
    private CustomItem item = null;

    public LootItem(LootType type, int minAmount, int maxAmount) {
        this.type = type;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public LootItem(CustomItem item) {
        this.item = item;
        this.type = LootType.ITEM;
    }

    public LootItem(CustomItem item, int minAmount, int maxAmount) {
        this.item = item;
        this.type = LootType.ITEM;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public LootItem(Material material) {
        this.item = new VanillaItem(material);
        this.type = LootType.ITEM;
    }

    public LootItem(Material material, int minAmount, int maxAmount) {
        this.item = new VanillaItem(material);
        this.type = LootType.ITEM;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public LootItemInstance roll(Player player) {
        int amount = minAmount;

        if (minAmount != maxAmount) {
            amount = maxAmount - new Random().nextInt(maxAmount - minAmount);
        }

        if (type == LootType.ITEM) {
            return new LootItemInstance(item, amount);
        } else {
            return new LootItemInstance(type, amount);
        }
    }
}
