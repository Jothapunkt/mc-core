package com.jothapunkt.spigot.raftcraft.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.modifiers.effects.CustomEffect;
import com.jothapunkt.spigot.raftcraft.rafts.Raft;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;


public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        new PlayerInfo(event.getPlayer()).initializeStats();

        // If the player logged out on their raft last time, log them in on their raft
        if (
            event.getPlayer().getPersistentDataContainer().has(new NamespacedKey(RaftCraft.getInstance(), "loggedOutOnRaft"))
            && event.getPlayer().getPersistentDataContainer().get(new NamespacedKey(RaftCraft.getInstance(), "loggedOutOnRaft"), PersistentDataType.BOOLEAN)
        ) {
            new Raft(event.getPlayer()).send(event.getPlayer());
        }

        // Restore custom effects
        List<CustomEffect> effects = new ArrayList<>();
        new PlayerInfo(event.getPlayer()).getMeta().set("effects", effects);
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        event.setFoodLevel(20);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        // Note: Make sure to enable doInstantRespawn gamerule
        
        Player player = event.getPlayer();
        PlayerInfo info = new PlayerInfo(player);

        // Reset health and other stats
        info.initializeStats();

        // Set respawn location to current world spawn point
        player.teleport(player.getWorld().getSpawnLocation());

        // Apply brief blindness
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 1));
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        // If a player logs out on their raft, set a flag so they are spawned back there
        // Necessary because the raft may have been unloaded in the meantime
        Raft raft = new Raft(event.getPlayer());

        event.getPlayer().getPersistentDataContainer().set(
            new NamespacedKey(RaftCraft.getInstance(), "loggedOutOnRaft"),
            PersistentDataType.BOOLEAN,
            raft.getWorldName().equals(event.getPlayer().getWorld().getName())
        );
    }
}