package com.jothapunkt.spigot.raftcraft.gui.generic;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.util.Items;

public class ScrollableGUI extends GUI {
    protected int scrollAreaStart = 0;
    protected int scrollAreaEnd = 44;
    protected int offset = 0;
    protected HashMap<Integer, ScrollableGUIElement> scrollableSlots = new HashMap<>();

    public ScrollableGUI() {
        slot(47, Items.head("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmM4MDEwNzEyZWM2YTk3MmNkODRlOTdjMmNiNmE3NTVjZWM0NGZjOTcxN2I3Mjc1ZTYzZjM1MmQyZTU5NmIifX19"), new GUIElement((InventoryClickEvent e) -> scrollUp()));
        slot(48, Items.head("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWE2NzhlZWU4MTNlZDU2MDgyOTZiYjI1YTg5YzY3MGMwODRhNGE4MzgxZjI2YTMxYzViNWM2ZTQ5OTE0MSJ9fX0="), new GUIElement((InventoryClickEvent e) -> scrollDown()));
    }

    protected void slot(int slot, ItemStack item, GUIElement element) {
        if (element instanceof ScrollableGUIElement scrollable) {
            element.setItem(item);
            scrollableSlots.put(slot, scrollable);
        } else {
            super.slot(slot, item, element);
        }
    }

    public void scrollUp() {
        offset -= 9;
        updateInventory();
    }

    public void scrollDown() {
        offset += 9;
        updateInventory();
    }

    @Override
    protected void foreground() {      
        super.foreground();

        for (Entry<Integer, ScrollableGUIElement> entry : scrollableSlots.entrySet()) {
            int effectiveSlot = entry.getKey() - offset;
            if (effectiveSlot >= scrollAreaStart && effectiveSlot <= scrollAreaEnd) {
                inventory.setItem(effectiveSlot, entry.getValue().getItem());
            }
        }
    }

    @Override
    public GUIElement getElement(Integer slot) {
        if (slots.containsKey(slot)) {
            return slots.get(slot);
        }
        
        for (Entry<Integer, ScrollableGUIElement> entry : scrollableSlots.entrySet()) {
            int effectiveSlot = entry.getKey() - offset;

            if (effectiveSlot == entry.getKey()) {
                return entry.getValue();
            }
        }

        return null;
    }
}
