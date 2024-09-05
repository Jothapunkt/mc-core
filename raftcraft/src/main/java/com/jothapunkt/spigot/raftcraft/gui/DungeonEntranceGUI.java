package com.jothapunkt.spigot.raftcraft.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.dungeons.generic.Dungeon;
import com.jothapunkt.spigot.raftcraft.gui.generic.GUI;
import com.jothapunkt.spigot.raftcraft.gui.generic.GUIElement;

public class DungeonEntranceGUI extends GUI {
    protected Dungeon dungeon;

    public DungeonEntranceGUI(Dungeon dungeon) {
        super(new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        this.dungeon = dungeon;
        slot(4, dungeon.getEntranceItem(), new GUIElement((InventoryClickEvent e) -> e.getWhoClicked().sendMessage("Entering!")));
    }
    
    @Override
    protected Inventory setupInventory() {
        Inventory inventory = Bukkit.createInventory(this, InventoryType.DISPENSER, "Entrance");
        return inventory;
    }
}
