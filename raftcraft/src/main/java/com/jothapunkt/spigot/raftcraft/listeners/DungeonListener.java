package com.jothapunkt.spigot.raftcraft.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.spawner.SpawnerEntry;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.dungeons.generic.DungeonInstance;
import com.jothapunkt.spigot.raftcraft.mobs.MobRegistry;
import com.jothapunkt.spigot.raftcraft.mobs.generic.CustomMob;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;


public class DungeonListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        DungeonInstance instance = DungeonInstance.getDungeonInstance(event.getBlock().getWorld());
        if (instance != null) {
            instance.getDungeon().onBlockPlace(event);
        } 
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        DungeonInstance instance = DungeonInstance.getDungeonInstance(event.getBlock().getWorld());
        if (instance != null) {
            instance.getDungeon().onBlockBreak(event);
        } 
    }

    @EventHandler
    public void onSpawnerSpawn(SpawnerSpawnEvent event) {
        if (event.getSpawner().getPersistentDataContainer().has(new NamespacedKey(RaftCraft.getInstance(), "mob_identifier"))) {
            CustomMob mob = MobRegistry.get(event.getSpawner().getPersistentDataContainer().get(new NamespacedKey(RaftCraft.getInstance(), "mob_identifier"), PersistentDataType.STRING));
            Bukkit.broadcast(new TextComponent(mob.getIdentifier()));
            mob.spawn(event.getLocation());
            event.setCancelled(true);
        }
    }
}