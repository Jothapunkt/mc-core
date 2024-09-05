package com.jothapunkt.spigot.raftcraft.gui.generic;

import java.util.function.Consumer;

import org.bukkit.event.inventory.InventoryClickEvent;

import net.kyori.adventure.util.TriState;


public class GUIElement {
    protected Consumer<InventoryClickEvent> clickHandler = null;
    protected TriState defaultUsable = TriState.NOT_SET;

    public GUIElement() {}
    public GUIElement(Consumer<InventoryClickEvent> handler) {
        this(handler, TriState.NOT_SET);
    }
    public GUIElement(Consumer<InventoryClickEvent> handler, TriState defaultUsable) {
        clickHandler = handler;
        this.defaultUsable = defaultUsable;
    }

    public void onClick(InventoryClickEvent event) {
        if (clickHandler != null) {
            clickHandler.accept(event);
        }
    }

    public TriState isDefaultUsable() {
        return defaultUsable;
    }
}
