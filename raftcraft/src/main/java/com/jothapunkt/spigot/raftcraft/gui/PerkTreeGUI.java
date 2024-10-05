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
import com.jothapunkt.spigot.raftcraft.skills.trees.PerkTree;
import com.jothapunkt.spigot.raftcraft.skills.trees.Perk;
import com.jothapunkt.spigot.raftcraft.types.Direction;
import com.jothapunkt.spigot.raftcraft.util.PersistentData;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;


public class PerkTreeGUI extends ScrollableGUI {
    private Player player;
    private PerkTree tree;

    public PerkTreeGUI(Player player, PerkTree tree) {
        this.player = player;
        this.tree = tree;
        backgroundItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

        inventory = setupInventory();
        updateInventory();
    }

    @Override
    protected void refresh() {
        super.refresh();
        
        for (Pair<Integer, Perk> node : tree.getNodes()) {
            player.sendMessage(node.getSecond().getName());
            player.getInventory().addItem(node.getSecond().getItem(player));
            slot(node.getFirst(), node.getSecond().getItem(player), new ScrollableGUIElement());
        }
    }

    @Override
    protected Inventory setupInventory() {
        String name = tree == null ? "Skill Tree" : tree.getName();
        Inventory inventory = Bukkit.createInventory(this, 54, name);
        return inventory;
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClick().equals(ClickType.LEFT)) {
            
        }

        event.setCancelled(true);
    }
}
