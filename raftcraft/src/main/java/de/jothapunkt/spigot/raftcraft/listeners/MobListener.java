package com.jothapunkt.spigot.raftcraft.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityTargetEvent;
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
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.mobs.MobRegistry;
import com.jothapunkt.spigot.raftcraft.mobs.generic.CustomMob;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;


public class MobListener implements Listener {
    @EventHandler
    public void onTarget(EntityTargetEvent event) {
        if (event.getEntity().getPassengers().size() > 0 && event.getEntity().getPassengers().get(0) instanceof Player) {
            Entity mount = event.getEntity();
            Player rider = (Player) mount.getPassengers().get(0);

            rider.sendMessage(mount.getType() + " targets " + event.getTarget().getType());
        }
    }
}