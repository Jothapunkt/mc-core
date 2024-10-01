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
import com.jothapunkt.spigot.raftcraft.gui.EquipmentGUI;
import com.jothapunkt.spigot.raftcraft.util.Worlds;
import com.jothapunkt.spigot.raftcraft.util.GameRules;

import net.md_5.bungee.api.ChatColor;


public class CommandEquipment implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            new EquipmentGUI(player).show(player);
            return true;
        }

        return false;
    }
}
