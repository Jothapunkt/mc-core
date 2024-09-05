package com.jothapunkt.spigot.raftcraft.abilities.items.generic;

import org.bukkit.event.player.PlayerInteractEvent;


public class LeftClickAbility extends ItemAbility {
    public LeftClickAbility() {
        type = "Left Click";
    }
    
    public void onLeftClick(PlayerInteractEvent event) {
        attemptUse(event.getPlayer());
    }
}
