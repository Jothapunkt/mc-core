package com.jothapunkt.spigot.raftcraft.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.gui.generic.GUI;
import com.jothapunkt.spigot.raftcraft.util.PersistentData;

public class EquipmentGUI extends GUI {
    private Player player;

    public EquipmentGUI(Player player) {
        super(new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        this.player = player;

        updateInventory();
    }

    @Override
    protected void updateInventory() {
        super.updateInventory();
        
        PersistentData data = PersistentData.from(player);
        data.get(PersistentDataType.STRING, "mounts");
    }

    @Override
    protected Inventory setupInventory() {
        Inventory inventory = Bukkit.createInventory(this, 54, "Equipment");
        return inventory;
    }
}
