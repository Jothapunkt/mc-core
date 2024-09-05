package com.jothapunkt.spigot.raftcraft.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileLaunchEvent;


public class CollectionListener implements Listener {
    @EventHandler
    public void onFishingHookCreation(ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof FishHook) {
            FishHook hook = (FishHook) event.getEntity();
            Player caster = (Player) hook.getShooter();
            Bukkit.broadcastMessage("Fishing Hook GO! By: " + caster.getName());
        }
    }
}