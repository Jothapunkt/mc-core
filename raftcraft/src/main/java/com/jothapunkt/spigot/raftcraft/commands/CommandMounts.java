package com.jothapunkt.spigot.raftcraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.joml.Random;

import com.jothapunkt.spigot.raftcraft.gui.MountsGUI;
import com.jothapunkt.spigot.raftcraft.items.ItemRegistry;
import com.jothapunkt.spigot.raftcraft.items.generic.MountItem;
import com.jothapunkt.spigot.raftcraft.mounts.DolphinMount;
import com.jothapunkt.spigot.raftcraft.mounts.PhantomMount;
import com.jothapunkt.spigot.raftcraft.mounts.PigMount;
import com.jothapunkt.spigot.raftcraft.util.PersistentData;

import net.md_5.bungee.api.ChatColor;


public class CommandMounts implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            new MountsGUI(player).show(player);
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("summon")) {
            if (ItemRegistry.get(PersistentData.from(player).getItem("selectedMount")) instanceof MountItem mountItem) {
                mountItem.getMount().summon(player);
            } else {
                player.sendMessage(ChatColor.RED + "No mount active, select one in the /mounts menu");
            }
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
