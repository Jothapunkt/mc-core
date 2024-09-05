package com.jothapunkt.spigot.raftcraft.commands;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.World;

import com.jothapunkt.spigot.raftcraft.errors.BaseError;
import com.jothapunkt.spigot.raftcraft.errors.WorldError;
import com.jothapunkt.spigot.raftcraft.util.Worlds;
import com.jothapunkt.spigot.raftcraft.util.GameRules;

import net.md_5.bungee.api.ChatColor;


public class CommandWorlds implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equals("list")) {
            sender.sendMessage("In Memory:");
            for (World world : Bukkit.getWorlds()) {
                sender.sendMessage(world.getName());
            }

            sender.sendMessage("");
            sender.sendMessage("On Disk:");
            for (File worldFile : Bukkit.getWorldContainer().listFiles()) {
                sender.sendMessage(worldFile.getName());
            }

            return true;
        }

        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Currently in " + player.getWorld().getName());
            return true;
        }

        if (args.length >= 1 && args[0].equalsIgnoreCase("applyrules")) {
            GameRules.general(player.getWorld());
            return true;
        }

        if (args.length >= 1 && args[0].equalsIgnoreCase("unload")) {
            Worlds.unloadEmptyWorlds();
            return true;
        }

        if (args.length >= 2 && args[0].equals("tp") || args[0].equals("teleport")) {
            try {
                Worlds.teleport(player, args[1]);
            } catch (WorldError e) {
                e.sendTo(player);
            }

            return true;
        }

        if (args.length >= 2 && args[0].equals("del") || args[0].equals("delete")) {
            World target = Bukkit.getWorld(args[1]);

            if (target == null) {
                player.sendMessage("Could not find world '" + args[1] + "'");
            } else {
                Worlds.delete(target);
            }

            return true;
        }

        if (args.length >= 3 && args[0].equals("cp") || args[0].equals("copy")) {
            String source = args[1];
            String target = args[2];

            try {
                World newWorld = Worlds.clone(source, target);
                Worlds.teleport(player, newWorld);
            } catch(BaseError error) {
                error.sendTo(player);
            }

            return true;
        }

        if (args.length >= 2 && args[0].equals("setspawn")) {
            World target = Bukkit.getWorld(args[1]);

            if (target == null) {
                player.sendMessage("Could not find world '" + args[1] + "'");
            } else {
                target.setSpawnLocation(player.getLocation());
                player.sendMessage(ChatColor.GREEN + "World spawn set to your location.");
            }

            return true;
        }

        if (args.length >= 3 && args[0].equals("create")) {
            String name = args[1];
            String type = args[2].toLowerCase();

            World world = Worlds.createWorld(type, name);

            if (world == null) {
                player.sendMessage(ChatColor.RED + "Unknown world type " + type);
            } else {
                Worlds.teleport(player, world);
            }

            return true;
        }

        return false;
    }
}
