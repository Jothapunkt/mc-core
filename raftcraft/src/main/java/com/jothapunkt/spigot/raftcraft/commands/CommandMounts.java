package com.jothapunkt.spigot.raftcraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.joml.Random;

import com.jothapunkt.spigot.raftcraft.mounts.DolphinMount;
import com.jothapunkt.spigot.raftcraft.mounts.PhantomMount;
import com.jothapunkt.spigot.raftcraft.mounts.PigMount;


public class CommandMounts implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Currently in " + player.getWorld().getName());
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("summon")) {
            new PhantomMount().summon(player);
            return true;
        }

        if (args.length > 1 && args[0].equalsIgnoreCase("summon")) {
            if (args[1].equalsIgnoreCase("pig")) {
                new PigMount().summon(player);
                return true;
            }

            if (args[1].equalsIgnoreCase("phantom")) {
                new PhantomMount().summon(player);
                return true;
            }

            if (args[1].equalsIgnoreCase("dolphin")) {
                new DolphinMount().summon(player);
                return true;
            }
        }

        return false;
    }
}
