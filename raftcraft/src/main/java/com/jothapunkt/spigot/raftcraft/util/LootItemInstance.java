package com.jothapunkt.spigot.raftcraft.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.types.LootType;

import net.md_5.bungee.api.ChatColor;

public class LootItemInstance {
    private LootType type;
    private int amount = 1;
    private CustomItem item = null;

    public LootItemInstance(LootType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public LootItemInstance(CustomItem item, int amount) {
        this.item = item;
        this.type = LootType.ITEM;
        this.amount = amount;
    }

    public void grantTo(Player player) {
        grantTo(player, false);
    }

    public void grantTo(Player player, boolean sendMessage) {
        if (this.type == LootType.EXPERIENCE) {
            player.giveExp(this.amount);

            if (sendMessage) {
                player.sendMessage(Messages.addLootMessage(ChatColor.GREEN + "Experience", amount));
            }

            return;
        }

        if (this.type == LootType.ITEM) {
            ItemStack itemStack = item.print();
            itemStack.setAmount(amount);
            player.getInventory().addItem(itemStack);

            if (sendMessage) {
                player.sendMessage(Messages.addLootMessage(itemStack.getItemMeta().getDisplayName(), amount));
            }

            return;
        }
    }
}
