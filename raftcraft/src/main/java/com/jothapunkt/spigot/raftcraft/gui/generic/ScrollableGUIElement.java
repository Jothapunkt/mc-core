package com.jothapunkt.spigot.raftcraft.gui.generic;

import java.util.function.Consumer;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.kyori.adventure.util.TriState;


public class ScrollableGUIElement extends GUIElement {
    public ScrollableGUIElement() {}

    public ScrollableGUIElement(Consumer<InventoryClickEvent> handler) {
        super(handler, TriState.NOT_SET);
    }
    public ScrollableGUIElement(Consumer<InventoryClickEvent> handler, TriState defaultUsable) {
        super(handler, defaultUsable);
    }
}
