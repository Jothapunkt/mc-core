package com.jothapunkt.spigot.raftcraft.gui.generic;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;

import net.kyori.adventure.util.TriState;

public class GUI implements InventoryHolder {
    protected Inventory inventory;
    protected HashMap<Integer, GUIElement> slots = new HashMap<>();
    protected boolean defaultUsable = false;

    public GUI() {
        inventory = setupInventory();
    }

    public GUI(ItemStack backgroundItem) {
        this();

        if (backgroundItem != null) {
            ItemMeta meta = backgroundItem.getItemMeta();
            meta.setDisplayName(" ");
            backgroundItem.setItemMeta(meta);

            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, backgroundItem.clone());
            }
        }
    }

    protected void slot(int slot, ItemStack item, GUIElement element) {
        inventory.setItem(slot, item);
        slots.put(slot, element);
    }

    public boolean isDefaultUsable() {
        return defaultUsable;
    }

    public boolean isDefaultUsable(GUIElement element) {
        if (element == null) { return defaultUsable; }
        if (element.defaultUsable == TriState.TRUE) {return true;}
        if (element.defaultUsable == TriState.FALSE) {return false;}

        return defaultUsable;
    }

    protected Inventory setupInventory() {
        Inventory inventory = Bukkit.createInventory(this, 27);
        return inventory;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public void show(Player player) {
        player.openInventory(inventory);
        new PlayerInfo(player).getMeta().set("currentGUI", this);
    }

    public GUIElement getElement(Integer slot) {
        return slots.get(slot);
    }
}
