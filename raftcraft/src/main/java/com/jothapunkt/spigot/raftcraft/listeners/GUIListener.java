package com.jothapunkt.spigot.raftcraft.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import com.jothapunkt.spigot.raftcraft.gui.generic.GUI;
import com.jothapunkt.spigot.raftcraft.gui.generic.GUIElement;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;


public class GUIListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        GUI gui = new PlayerInfo(player).getCurrentGUI();

        // Check if this is a click inside a gui
        if (gui == null || !event.getClickedInventory().equals(gui.getInventory())) {
            return;
        }

        GUIElement clickedElement = gui.getElement(event.getSlot());
        event.setCancelled(!gui.isDefaultUsable(clickedElement));

        if (clickedElement != null) {
            clickedElement.onClick(event);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        new PlayerInfo(player).getMeta().remove("currentGUI");
    } 
}