package com.jothapunkt.spigot.raftcraft.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.gui.generic.GUI;
import com.jothapunkt.spigot.raftcraft.gui.generic.GUIElement;
import com.jothapunkt.spigot.raftcraft.interfaces.LockpickingTarget;
import com.jothapunkt.spigot.raftcraft.types.Direction;
import com.jothapunkt.spigot.raftcraft.util.PersistentData;

import net.minecraft.world.item.Item;

public class MountsGUI extends GUI {
    private Player player;

    public MountsGUI(Player player) {
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
        Inventory inventory = Bukkit.createInventory(this, InventoryType.CHEST, "Mounts");
        return inventory;
    }
}
