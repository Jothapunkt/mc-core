package com.jothapunkt.spigot.raftcraft.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.comphenix.protocol.wrappers.Pair;
import com.jothapunkt.spigot.raftcraft.gui.generic.GUI;
import com.jothapunkt.spigot.raftcraft.gui.generic.GUIElement;
import com.jothapunkt.spigot.raftcraft.gui.generic.ScrollableGUI;
import com.jothapunkt.spigot.raftcraft.gui.generic.ScrollableGUIElement;
import com.jothapunkt.spigot.raftcraft.interfaces.LockpickingTarget;
import com.jothapunkt.spigot.raftcraft.items.ItemRegistry;
import com.jothapunkt.spigot.raftcraft.items.generic.Belt;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.items.generic.MountItem;
import com.jothapunkt.spigot.raftcraft.items.generic.Necklace;
import com.jothapunkt.spigot.raftcraft.items.generic.Ring;
import com.jothapunkt.spigot.raftcraft.types.Direction;
import com.jothapunkt.spigot.raftcraft.util.PersistentData;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;


public class MountsGUI extends ScrollableGUI {
    private Player player;

    public MountsGUI(Player player) {
        this.player = player;

        updateInventory();
    }

    protected void handleMountClick(InventoryClickEvent event) {
        if (event.getClick().equals(ClickType.LEFT)) {
            if (ItemRegistry.get(event.getCurrentItem()) instanceof MountItem mountItem) {
                mountItem.getMount().summon(player);
                PersistentData.from(player).setItem(event.getCurrentItem(), "selectedMount");
                player.closeInventory();
            }
        }

        // Right click to move mount back to own inventory
        if (event.getClick().equals(ClickType.RIGHT)) {
            List<ItemStack> mounts = PersistentData.from(player).getItems("mounts");
            mounts.remove(event.getCurrentItem());
            PersistentData.from(player).setItems(mounts, "mounts");
            new PlayerInfo(player).give(event.getCurrentItem());
            updateInventory();
        }

        event.setCancelled(true);
    }

    @Override
    protected void refresh() {
        super.refresh();
        
        List<ItemStack> mounts = PersistentData.from(player).getItems("mounts");
        for (int i = 0; i < mounts.size(); i++) {
            slot(i, mounts.get(i), new ScrollableGUIElement((InventoryClickEvent event) -> handleMountClick(event)));
        }
    }

    @Override
    protected Inventory setupInventory() {
        Inventory inventory = Bukkit.createInventory(this, 54, "Mounts");
        return inventory;
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClick().equals(ClickType.LEFT)) {
            ItemStack item = event.getCurrentItem();
            if (ItemRegistry.get(item) instanceof MountItem) {
                player.getInventory().setItem(event.getSlot(), null);
                PersistentData.from(player).addItem(item, "mounts");
                updateInventory();
            }
        }

        event.setCancelled(true);
    }
}
