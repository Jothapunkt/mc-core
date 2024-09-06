package com.jothapunkt.spigot.raftcraft.items.menu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.abilities.items.LeftClickGUIAbility;
import com.jothapunkt.spigot.raftcraft.abilities.items.RightClickGUIAbility;
import com.jothapunkt.spigot.raftcraft.gui.MainMenu;
import com.jothapunkt.spigot.raftcraft.items.generic.FixedPositionItem;

import net.md_5.bungee.api.ChatColor;

public class MenuItem extends FixedPositionItem {
    public MenuItem() {
        baseItem = new ItemStack(Material.BOOK);
        name = ChatColor.GOLD + "Menu";
        ability(new RightClickGUIAbility(() -> new MainMenu()));
        ability(new LeftClickGUIAbility(() -> new MainMenu()));
    }
}
