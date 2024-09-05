package com.jothapunkt.spigot.raftcraft.rafts;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.logic.FlotsamLogic;
import com.jothapunkt.spigot.raftcraft.util.FileSystem;
import com.jothapunkt.spigot.raftcraft.util.GameRules;
import com.jothapunkt.spigot.raftcraft.util.Worlds;

import net.kyori.adventure.util.TriState;
import net.md_5.bungee.api.ChatColor;

public class Raft {
    private String uuid;

    public Raft(String uuid) {
        this.uuid = uuid;
    }

    public Raft(Player player) {
        this.uuid = player.getUniqueId().toString();
    }

    public Raft(OfflinePlayer player) {
        this.uuid = player.getUniqueId().toString();
    }

    public String getWorldName() {
        return "raft_" + uuid;
    }

    public NamespacedKey getWorldKey() {
        return new NamespacedKey(RaftCraft.getInstance(), getWorldName());
    }

    public World loadOrCreate(Player sender) {
        if (Worlds.existsOnDisk(getWorldName())) {
            return load(sender);
        } else {
            return create(sender);
        }
    }

    private World create(Player sender) {
        sender.sendMessage("Setting up a new raft, this may take a moment...");
        Bukkit.getLogger().info("Creating new raft: " + getWorldName());
        WorldCreator creator = new WorldCreator(getWorldKey());
        creator.generateStructures(false);
        creator.keepSpawnLoaded(TriState.FALSE);
        creator.type(WorldType.FLAT);
        creator.generator(new OceanGenerator());
        World world = creator.createWorld();

        for (int X = -1; X <= 1; X++) {
            for (int Z = -2; Z <= 2; Z++) {
                world.getBlockAt(X, OceanGenerator.SEA_LEVEL, Z).setType(Material.SPRUCE_PLANKS);
            }
        }

        // Set game rules
        GameRules.raft(world);

        // Set up spawn location and world border
        Location worldSpawn = new Location(world, 0, OceanGenerator.SEA_LEVEL + 1, 0);
        world.getWorldBorder().setCenter(worldSpawn);
        world.getWorldBorder().setSize(500);
        world.setSpawnLocation(worldSpawn);
        sender.teleport(worldSpawn);

        // Set up flotsam spawner
        FlotsamLogic.createFlotsamSpawner(worldSpawn);

        return world;
    }

    private World load(Player sender) {
        Bukkit.getLogger().info("Loading raft: " + getWorldName());
        WorldCreator creator = new WorldCreator(getWorldKey());
        creator.generateStructures(false);
        creator.keepSpawnLoaded(TriState.FALSE);
        creator.type(WorldType.FLAT);
        creator.generator(new OceanGenerator());
        World world = creator.createWorld(); // this loads the world if it already exists
        sender.teleport(world.getSpawnLocation());
        return world;
    }

    public World getWorldIfLoaded() {
        return Bukkit.getWorld(getWorldKey());
    }

    public void send(Player player) {
        player.teleport(getWorld(player).getSpawnLocation());
    }

    public World getWorld(Player sender) {
        World world = getWorldIfLoaded();

        if (world == null) {
            world = loadOrCreate(sender);
        }

        return world;
    }
}
