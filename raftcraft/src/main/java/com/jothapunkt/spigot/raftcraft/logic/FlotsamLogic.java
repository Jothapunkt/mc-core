package com.jothapunkt.spigot.raftcraft.logic;

import java.util.List;

import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.Random;
import org.joml.Vector3f;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Marker;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import com.jothapunkt.spigot.raftcraft.types.FlotsamType;
import com.jothapunkt.spigot.raftcraft.types.Stat;
import com.jothapunkt.spigot.raftcraft.types.Wind;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;
import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.tables.BarrelLootTable;
import com.jothapunkt.spigot.raftcraft.tables.FlotsamTypeTable;
import com.jothapunkt.spigot.raftcraft.tables.PotLootTable;
import com.jothapunkt.spigot.raftcraft.items.materials.PalmLeaf;
import com.jothapunkt.spigot.raftcraft.items.materials.Plank;
import com.jothapunkt.spigot.raftcraft.items.materials.Thatch;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.metadata.FixedMetadataValue;

import net.md_5.bungee.api.ChatColor;
import java.awt.Color;


public class FlotsamLogic {
    public static BukkitRunnable getFlotsamCollisionsLoop() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    for (Entity entity : world.getEntities()) {
                        // Spawner logic
                        if (entity instanceof Marker) {    
                            // If there is no metadata: Skip
                            if (!entity.getPersistentDataContainer().has(new NamespacedKey(RaftCraft.getInstance(), "next_flotsam_spawn"))) {
                                continue;
                            }
    
                            flotsamSpawnerTick((Marker) entity);
                        }
                        
                        // Flotsam Logic
                        if (entity instanceof ItemDisplay) {
                            ItemDisplay flotsam = (ItemDisplay) entity;
        
                            // If there is no metadata: Skip
                            if (!entity.getPersistentDataContainer().has(new NamespacedKey(RaftCraft.getInstance(), "flotsam_type"))) {
                                continue;
                            }
    
                            flotsamTick(flotsam);
                        }
                    }
                }
            }
        };
    }

    public static void flotsamTick(ItemDisplay flotsam) {
        // Check if it was caught
        for (Entity otherEntity : flotsam.getWorld().getEntities()) {
            // Check if caught by a fishing hook
            if (otherEntity instanceof FishHook) {
                FishHook hook = (FishHook) otherEntity;

                if (flotsam.getLocation().distance(otherEntity.getLocation()) < 1) {
                    catchFlotsam(flotsam, (Player) hook.getShooter());
                    return;
                }
            }

            // Check if caught by player
            if (otherEntity instanceof Player) {
                Player player = (Player) otherEntity;

                if (flotsam.getLocation().distance(otherEntity.getLocation()) < new PlayerInfo(player).getStat(Stat.PICKUP_RANGE)) {
                    catchFlotsam(flotsam, player);
                    return;
                }
            }
        }

        // Destroy if in air
        if (flotsam.getWorld().getBlockAt(flotsam.getLocation()).getType() != Material.AIR) {
            // TODO Collect if it hit a collection net
            destroyFlotsam(flotsam);
            return;
        }

        // Destroy if not above water / seagrass
        Location blockBelow = flotsam.getLocation().clone();
        blockBelow.setY(blockBelow.getY() - 1);
        if (!List.of(Material.WATER, Material.SEAGRASS).contains(flotsam.getWorld().getBlockAt(blockBelow).getType())) {
            destroyFlotsam(flotsam);
            return;
        } 

        //Perform movement
        flotsam.setInterpolationDuration(40);
        flotsam.setInterpolationDelay(-1);
        flotsam.setTeleportDuration(5);

        Wind wind = RaftCraft.getInstance().getWind();
        
        flotsam.setRotation(wind.getWindYaw(), flotsam.getLocation().getPitch());
        Location location = flotsam.getLocation().clone();
        location.setPitch(0);
        Vector velocity = location.getDirection().normalize().multiply(wind.getWindStrength());
        
        Location newLocation = flotsam.getLocation().clone();
        newLocation.setX(flotsam.getLocation().getX() + velocity.getX());
        newLocation.setZ(flotsam.getLocation().getZ() + velocity.getZ());
        flotsam.teleport(newLocation);
    }

    public static void flotsamSpawnerTick(Marker spawner) {
        int SPAWNER_DISTANCE_LIMIT = 50;
        
        int currentFlotsamCount = 0;

        // Destroy any flotsam that has floated too far from the spawner
        for (Entity entity : spawner.getWorld().getEntities()) {
            // If there is no metadata: Skip
            if (!entity.getPersistentDataContainer().has(new NamespacedKey(RaftCraft.getInstance(), "flotsam_source"))) {
                continue;
            }

            String flotsamSource = entity.getPersistentDataContainer().get(
                new NamespacedKey(RaftCraft.getInstance(), "flotsam_source"),
                PersistentDataType.STRING
            );
            
            if (flotsamSource.equals(spawner.getUniqueId().toString())) {
                if (entity.getLocation().distance(spawner.getLocation()) > SPAWNER_DISTANCE_LIMIT) {
                    destroyFlotsam((ItemDisplay) entity);
                } else {
                    currentFlotsamCount += 1;
                }
            }
        }

        long nextFlotsamSpawn = spawner.getPersistentDataContainer().get(new NamespacedKey(RaftCraft.getInstance(), "next_flotsam_spawn"), PersistentDataType.LONG);

        if (System.currentTimeMillis() > nextFlotsamSpawn) {
            if (currentFlotsamCount < 10) {
                spawner.getPersistentDataContainer().set(
                    new NamespacedKey(RaftCraft.getInstance(), "next_flotsam_spawn"),
                    PersistentDataType.LONG,
                    System.currentTimeMillis() + (500 + new Random().nextInt(1000))
                );
            } else {
                spawner.getPersistentDataContainer().set(
                    new NamespacedKey(RaftCraft.getInstance(), "next_flotsam_spawn"),
                    PersistentDataType.LONG,
                    System.currentTimeMillis() + (800 + new Random().nextInt(3000))
                );
            }
            
            ItemDisplay flotsam = FlotsamLogic.spawnFlotsam(new FlotsamTypeTable().choose(), spawner, true);
            flotsamTick(flotsam);
        }
    }

    public static Location randomFlotsamLocation(Location origin) {
        Location location = origin.clone();

        // Move against wind direction
        location.setPitch(0);
        location.setYaw(RaftCraft.getInstance().getWind().getWindYaw() + 180);
        location.add(location.getDirection().normalize().multiply(30));
        // RaftCraft.getInstance().getWind().getWindYaw();

        // Move perpendicular to wind
        location.setYaw(RaftCraft.getInstance().getWind().getWindYaw() + 90);
        location.add(location.getDirection().normalize().multiply(20 - new Random().nextInt(40)));

        return location;
    }

    public static ItemDisplay spawnFlotsam(FlotsamType type, Entity source, boolean spread) {
        Location location = source.getLocation().clone();
        location.setY(location.getY() + 0.1);

        if (spread) {
            location = randomFlotsamLocation(location);
        }

        ItemDisplay flotsam = (ItemDisplay) source.getWorld().spawnEntity(location, EntityType.ITEM_DISPLAY);

        // Set type
        flotsam.getPersistentDataContainer().set(
            new NamespacedKey(RaftCraft.getInstance(), "flotsam_type"),
            PersistentDataType.STRING,
            type.name()
        );

        // Set source ID
        flotsam.getPersistentDataContainer().set(
            new NamespacedKey(RaftCraft.getInstance(), "flotsam_source"),
            PersistentDataType.STRING,
            source.getUniqueId().toString()
        );

        // Default values
        flotsam.setCustomName("Unknown");
        flotsam.setCustomNameVisible(true);
        flotsam.setRotation(0, -90);

        if (type == FlotsamType.THATCH) {
            flotsam.setItemStack(new Thatch().print());
            flotsam.setCustomName(flotsam.getItemStack().getItemMeta().getDisplayName());
        }

        if (type == FlotsamType.PLANK) {
            flotsam.setItemStack(new Plank().print());
            flotsam.setCustomName(flotsam.getItemStack().getItemMeta().getDisplayName());

            flotsam.setTransformation(
                new Transformation(
                    flotsam.getTransformation().getTranslation(),
                    flotsam.getTransformation().getLeftRotation(),
                    new Vector3f(0.35f, 2f, 0.1f),
                    flotsam.getTransformation().getRightRotation()
                )
            );
        }

        if (type == FlotsamType.PALM_LEAF) {
            flotsam.setItemStack(new PalmLeaf().print());
            flotsam.setCustomName(flotsam.getItemStack().getItemMeta().getDisplayName());
        }

        if (type == FlotsamType.BARREL) {
            flotsam.setItemStack(new ItemStack(Material.BARREL));
            flotsam.setCustomName(ChatColor.of(new Color(128, 43, 0)) + "Barrel");

            flotsam.setTransformation(
                new Transformation(
                    flotsam.getTransformation().getTranslation(),
                    flotsam.getTransformation().getLeftRotation(),
                    new Vector3f(0.5f, 0.5f, 0.5f),
                    flotsam.getTransformation().getRightRotation()
                )
            );
        }

        if (type == FlotsamType.POT) {
            flotsam.setItemStack(new ItemStack(Material.DECORATED_POT));
            flotsam.setCustomName(ChatColor.of(new Color(180, 43, 0)) + "Pot");

            flotsam.setTransformation(
                new Transformation(
                    flotsam.getTransformation().getTranslation(),
                    flotsam.getTransformation().getLeftRotation(),
                    new Vector3f(0.5f, 0.5f, 0.5f),
                    flotsam.getTransformation().getRightRotation()
                )
            );
        }

        return flotsam;
    }

    public static Marker createFlotsamSpawner(Location location) {
        Marker flotsamSpawner = (Marker) location.getWorld().spawnEntity(location, EntityType.MARKER);

        flotsamSpawner.getPersistentDataContainer().set(
            new NamespacedKey(RaftCraft.getInstance(), "next_flotsam_spawn"),
            PersistentDataType.LONG,
            System.currentTimeMillis() + 200
        );

        return flotsamSpawner;
    }

    public static void destroyFlotsam(Entity flotsam) {
        flotsam.remove();
    }

    public static void catchFlotsam(ItemDisplay flotsam, Player player) {
        FlotsamType type = FlotsamType.valueOf(
            flotsam.getPersistentDataContainer().get(
                new NamespacedKey(RaftCraft.getInstance(), "flotsam_type"),
                PersistentDataType.STRING
            )
        );

        if (type == FlotsamType.BARREL) {
            new BarrelLootTable().roll(player, 3).grantTo(player);;
        } else if (type == FlotsamType.POT) {
            new PotLootTable().roll(player, 3).grantTo(player);
        } else {
            player.getInventory().addItem(flotsam.getItemStack());
        }
        
        destroyFlotsam(flotsam);
    }
}
