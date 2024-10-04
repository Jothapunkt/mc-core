package com.jothapunkt.spigot.raftcraft.gui;

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
import com.jothapunkt.spigot.raftcraft.items.ItemRegistry;
import com.jothapunkt.spigot.raftcraft.items.generic.Belt;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.items.generic.Necklace;
import com.jothapunkt.spigot.raftcraft.items.generic.Ring;
import com.jothapunkt.spigot.raftcraft.util.PersistentData;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;

public class EquipmentGUI extends GUI {
    private Player player;

    public EquipmentGUI(Player player) {
        super(new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        this.player = player;

        updateInventory();
    }

    @Override
    protected void refresh() {
        slots.clear();        
        PlayerInfo info = new PlayerInfo(player);

        // Necklace
        slot(10, info.getNecklace(), new GUIElement((InventoryClickEvent event) -> {
            ItemStack necklace = info.getNecklace();
            info.setNecklace(null);
            info.give(necklace);
            updateInventory();
        }));

        // Rings
        Pair<ItemStack, ItemStack> rings = info.getRings();
        slot(19, rings.getFirst(), new GUIElement((InventoryClickEvent event) -> {
            Pair<ItemStack, ItemStack> currentRings = info.getRings();
            info.setFirstRing(null);
            info.give(currentRings.getFirst());
            updateInventory();
        }));
        slot(28, rings.getSecond(), new GUIElement((InventoryClickEvent event) -> {
            Pair<ItemStack, ItemStack> currentRings = info.getRings();
            info.setSecondRing(null);
            info.give(currentRings.getSecond());
            updateInventory();
        }));

        // Belt
        slot(37, info.getBelt(), new GUIElement((InventoryClickEvent event) -> {
            ItemStack belt = info.getBelt();
            info.setBelt(null);
            info.give(belt);
            updateInventory();
        }));

        slot(12, player.getInventory().getHelmet(), new GUIElement());
        slot(21, player.getInventory().getChestplate(), new GUIElement());
        slot(30, player.getInventory().getLeggings(), new GUIElement());
        slot(39, player.getInventory().getBoots(), new GUIElement());
    }

    @Override
    protected Inventory setupInventory() {
        Inventory inventory = Bukkit.createInventory(this, 54, "Equipment");
        return inventory;
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClick().equals(ClickType.LEFT)) {
            PlayerInfo info = new PlayerInfo(player);
            ItemStack clickedItemStack = event.getCurrentItem();
            CustomItem clickedItem = ItemRegistry.get(clickedItemStack);
            player.sendMessage(clickedItem.getName());

            if (clickedItem instanceof Necklace && info.getNecklace() == null) {
                event.getClickedInventory().remove(clickedItemStack);
                info.setNecklace(clickedItemStack);
                updateInventory();
            }

            if (clickedItem instanceof Belt && info.getBelt() == null) {
                event.getClickedInventory().remove(clickedItemStack);
                info.setBelt(clickedItemStack);
                updateInventory();
            }

            if (clickedItem instanceof Ring) {
                Pair<ItemStack, ItemStack> rings = info.getRings();

                if (rings.getFirst() == null) {
                    event.getClickedInventory().remove(clickedItemStack);
                    info.setFirstRing(clickedItemStack);
                    updateInventory();
                } else if (rings.getSecond() == null) {
                    event.getClickedInventory().remove(clickedItemStack);
                    info.setSecondRing(clickedItemStack);
                    updateInventory();
                }
            }
        }

        event.setCancelled(true);
    }
}
