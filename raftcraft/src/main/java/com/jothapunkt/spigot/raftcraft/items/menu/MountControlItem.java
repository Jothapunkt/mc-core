package com.jothapunkt.spigot.raftcraft.items.menu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.abilities.items.MountLeftClickAbility;
import com.jothapunkt.spigot.raftcraft.abilities.items.MountRightClickAbility;
import com.jothapunkt.spigot.raftcraft.items.generic.FixedPositionItem;

import net.md_5.bungee.api.ChatColor;

public class MountControlItem extends FixedPositionItem {
    public MountControlItem() {
        baseItem = new ItemStack(Material.CARROT_ON_A_STICK);
        name = ChatColor.GOLD + "Command Mount";
        ability(new MountLeftClickAbility());
        ability(new MountRightClickAbility());
    }
}
