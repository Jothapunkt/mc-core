package com.jothapunkt.spigot.raftcraft.abilities.items.generic;

import org.bukkit.event.player.PlayerInteractEvent;


public class RightClickAbility extends ItemAbility {
    public RightClickAbility() {
        type = "Right Click";
    }
    
    public void onRightClick(PlayerInteractEvent event) {
        attemptUse(event.getPlayer());
    }
}
