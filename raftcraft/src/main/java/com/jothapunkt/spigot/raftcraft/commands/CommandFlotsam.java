package com.jothapunkt.spigot.raftcraft.commands;

import java.util.HashMap;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Marker;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.items.ItemRegistry;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.logic.FlotsamLogic;
import com.jothapunkt.spigot.raftcraft.mobs.MobRegistry;
import com.jothapunkt.spigot.raftcraft.mobs.generic.CustomMob;
import com.jothapunkt.spigot.raftcraft.tables.FlotsamTypeTable;
import com.jothapunkt.spigot.raftcraft.util.Displays;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;
import com.jothapunkt.spigot.raftcraft.util.Time;


public class CommandFlotsam implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            FlotsamLogic.spawnFlotsam(new FlotsamTypeTable().choose(), player, false);
            return true;
        }

        if (args.length == 1 && args[0].equals("spawner")) {
            sender.sendMessage("Creating Flotsam Spawner");
            FlotsamLogic.createFlotsamSpawner(player.getLocation());
            return true;
        }

        if (args.length >= 2 && args[0].equals("show")) {
            if (args[1].equals("spawners")) {
                for (Entity entity : player.getWorld().getEntities()) {
                    if (entity instanceof Marker) {    
                        // If there is no metadata: Skip
                        if (!entity.getPersistentDataContainer().has(new NamespacedKey(RaftCraft.getInstance(), "next_flotsam_spawn"))) {
                            continue;
                        }

                        Displays.addTemporaryText(entity.getLocation(), "Flotsam Spawner", 30);
                    }
                }

                return true;
            }
        }

        if (args.length >= 1 && args[0].equals("stats")) {            
            new PlayerInfo(player).printStats();

            return true;
        }

        if (args.length >= 2 && args[0].equals("get")) {
            String search = "";

            for (int i = 1; i < args.length; i++) {
                search += args[i];
                if (i < args.length - 1) {
                    search += " ";
                }
            }
            CustomItem item = ItemRegistry.get(search);

            if (item != null) {
                player.getInventory().addItem(item.print());
            } else {
                player.sendMessage("Unknown Item: " + search);
            }

            return true;
        }

        if (args.length >= 2 && args[0].equals("spawner")) {
            String search = "";

            for (int i = 1; i < args.length; i++) {
                search += args[i];
                if (i < args.length - 1) {
                    search += " ";
                }
            }

            CustomMob mob = MobRegistry.get(search);

            if (mob != null) {
                player.getInventory().addItem(mob.getSpawner());
            } else {
                player.sendMessage("Unknown Mob: " + search);
            }

            return true;
        }

        if (args.length >= 1 && args[0].equals("meta")) {
            ItemStack item = player.getInventory().getItemInMainHand();
            
            for (NamespacedKey key : item.getPersistentDataContainer().getKeys()) {
                player.sendMessage(key + ": " + item.getPersistentDataContainer().get(key, PersistentDataType.STRING));
            }

            return true;
        }

        if (args.length >= 1 && args[0].equals("inspect")) {
            Block block = player.getTargetBlockExact(100);

            if (block != null) {
                player.sendMessage(block.getType().name());
                player.sendMessage(block.getClass().getName());
                player.sendMessage(block.getState().getClass().getName());
            }

            if (block.getState() instanceof PersistentDataHolder) {
                player.sendMessage("This block can hold persistent metadata.");
                PersistentDataHolder holder = (PersistentDataHolder) block.getState();
                for (NamespacedKey key : holder.getPersistentDataContainer().getKeys()) {
                    player.sendMessage(key + ": " + holder.getPersistentDataContainer().get(key, PersistentDataType.STRING));
                }
            } else {

            }

            return true;
        }

        if (args.length >= 1 && args[0].equals("date")) {            
            Time d = Time.now();
            player.sendMessage(d.getYear() + "/" + d.getMonth() + "/" + d.getDate() + " " + d.getHour() + ":" + d.getMinute());

            return true;
        }

        return false;
    }
}
