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
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.items.generic.Necklace;
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
    protected void updateInventory() {
        super.updateInventory();
        
        PlayerInfo info = new PlayerInfo(player);
        slot(10, info.getNecklace(), new GUIElement((InventoryClickEvent event) -> {
            ItemStack necklace = info.getNecklace();
            info.setNecklace(null);
            info.give(necklace);
            updateInventory();
        }));
        Pair<ItemStack, ItemStack> rings = info.getRings();
        slot(19, rings.getFirst(), new GUIElement());
        slot(28, rings.getFirst(), new GUIElement());
        slot(37, info.getBelt(), new GUIElement());
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
        }

        event.setCancelled(true);
    }
}
