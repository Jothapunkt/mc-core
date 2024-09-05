package com.jothapunkt.spigot.raftcraft.interfaces;

import org.bukkit.event.player.PlayerInteractEntityEvent;

public interface InteractableEntity {
    public void onInteract(PlayerInteractEntityEvent event);
}
