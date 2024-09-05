package com.jothapunkt.spigot.raftcraft.listeners;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.interfaces.InteractableBlock;
import com.jothapunkt.spigot.raftcraft.interfaces.InteractableEntity;
import com.jothapunkt.spigot.raftcraft.util.CustomClass;
import com.jothapunkt.spigot.raftcraft.util.CustomClassRegistry;


public class CustomClassListener implements Listener {
    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent event) {
        CustomClass clazz = CustomClassRegistry.getInstance().get(event.getRightClicked());
        if (clazz instanceof InteractableEntity) {
            ((InteractableEntity) clazz).onInteract(event);
        }
    }

    @EventHandler
    public void onBlockInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) { return; }

        Bukkit.broadcastMessage("Interact... " + event.getClickedBlock().getState().getClass().getName());
        
        if (event.getClickedBlock().getState() instanceof PersistentDataHolder) {
            PersistentDataHolder holder = (PersistentDataHolder) event.getClickedBlock().getState();
            Bukkit.broadcastMessage("PDH... " + holder.getPersistentDataContainer().get(new NamespacedKey(RaftCraft.getInstance(), "custom_class"), PersistentDataType.STRING));
            CustomClass clazz = CustomClassRegistry.getInstance().get((PersistentDataHolder) event.getClickedBlock().getState());
            if (clazz != null) {
                Bukkit.broadcastMessage(clazz.getKey());
            }
            if (clazz instanceof InteractableBlock) {
                ((InteractableBlock) clazz).onInteract(event);
            }
        }
    }
}