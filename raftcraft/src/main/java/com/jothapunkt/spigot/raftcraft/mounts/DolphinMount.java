package com.jothapunkt.spigot.raftcraft.mounts;

import org.bukkit.entity.Dolphin;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import com.jothapunkt.spigot.raftcraft.mounts.generic.WaterMount;

public class DolphinMount extends WaterMount<Dolphin> {
    protected double speed = 0.5;

    public DolphinMount() {
        name = "Dolphin";
        type = EntityType.DOLPHIN;
    }

    @Override
    public void onRightClick(PlayerInteractEvent event) {
        
    }
}
