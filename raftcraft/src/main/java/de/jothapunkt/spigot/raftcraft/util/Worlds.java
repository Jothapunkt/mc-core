package com.jothapunkt.spigot.raftcraft.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.errors.WorldError;
import com.jothapunkt.spigot.raftcraft.rafts.OceanGenerator;

import net.kyori.adventure.util.TriState;
import net.md_5.bungee.api.ChatColor;

public class Worlds {
    public static void teleport(Player player, String name) throws WorldError {
        player.teleport(get(name).getSpawnLocation());
    }

    public static void teleport(Player player, World world) {
        player.teleport(world.getSpawnLocation());
    }

    public static World get(String name) throws WorldError {
        World world = getIfLoaded(name);

        if (world == null) {
            if (existsOnDisk(name)) {
                return load(name);
            } else {
                throw new WorldError("No world named " + name);
            }
        }

        return world;
    }

    public static World load(String name) {
        WorldCreator creator = new WorldCreator(name);
        creator.generateStructures(false);
        creator.keepSpawnLoaded(TriState.FALSE);
        creator.type(WorldType.FLAT);
        creator.generator(new OceanGenerator());
        return creator.createWorld();
    }

    public static void unload(World world) {
        evacuate(world); // Can't have any players on the island or it can't be unloaded
        Bukkit.getLogger().info("Unloading world: " + world.getName());
        Bukkit.unloadWorld(world, true);
    }

    public static World clone(String name, String newName) throws WorldError {
        World world = get(name);

        Path sourcePath = world.getWorldFolder().toPath();
        Path targetPath = Paths.get(Bukkit.getWorldContainer().toPath() + "\\" + newName);

        unload(world);

        try {
            FileSystem.copyFolder(sourcePath, targetPath);

            // Delete uid.dat file, if these are identical it will be recognized as a duplicate
            targetPath.resolve("uid.dat").toFile().delete();

            Bukkit.getLogger().info("Copied world " + world.getName() + " to " + newName);

            return load(newName);
        } catch(IOException e) {
            Bukkit.getLogger().warning(e.getMessage());
            throw new WorldError("Failed to clone world");
        }
    }

    public static void evacuate(World world) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getWorld() == world) {
                // Evacuate to spawn
                player.sendMessage(ChatColor.RED + "The world you were in is being evacuated, sending you to spawn...");
                player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
            }
        }
    }

    public static boolean delete(World world) {
        unload(world);
        
        for (File worldFile : Bukkit.getWorldContainer().listFiles()) {
            if (worldFile.getName().equals(world.getName())) {
                try {
                    FileSystem.deleteDirectory(worldFile);
                    Bukkit.getLogger().info("Deleted world: " + world.getName());
                    return true;
                } catch(IOException e) {
                    Bukkit.getLogger().warning(e.getMessage());
                    return false;
                }
            }
        }

        return false;
    }

    public static boolean isLoaded(String name) {
        for (World world : Bukkit.getWorlds()) {
            if (world.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }

        return false;
    }

    public static World getIfLoaded(String name) {
        for (World world : Bukkit.getWorlds()) {
            if (world.getName().equalsIgnoreCase(name)) {
                return world;
            }
        }

        return null;
    }

    public static boolean existsOnDisk(String name) {
        for (File worldFile : Bukkit.getWorldContainer().listFiles()) {
            if (worldFile.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public static void unloadEmptyWorlds() {
        for (World world : Bukkit.getWorlds()) {
            boolean hasPlayers = false;

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getLocation().getWorld() == world) {
                    hasPlayers = true;
                }
            }

            if (!hasPlayers) {
                Bukkit.getLogger().info("Unloading world " + world.getName());
                Bukkit.unloadWorld(world, true);
                world = null;
                System.gc();
            }
        }
    }

    public static World createWorld(String type, String name) {
        if (type.equals("normal")) {
            WorldCreator creator = new WorldCreator(name);
            creator.generateStructures(false);
            creator.keepSpawnLoaded(TriState.FALSE);
            creator.type(WorldType.NORMAL);
            World world = creator.createWorld();
            return world;
        }

        if (type.equals("flat")) {
            WorldCreator creator = new WorldCreator(name);
            creator.generateStructures(false);
            creator.keepSpawnLoaded(TriState.FALSE);
            creator.type(WorldType.FLAT);
            World world = creator.createWorld();
            return world;
        }

        if (type.equals("ocean")) {
            WorldCreator creator = new WorldCreator(name);
            creator.generateStructures(false);
            creator.keepSpawnLoaded(TriState.FALSE);
            creator.type(WorldType.FLAT);
            creator.generator(new OceanGenerator());
            World world = creator.createWorld();
            return world;
        }

        return null;
    }
}
