package com.jothapunkt.spigot.raftcraft.commands;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.World;

import com.jothapunkt.spigot.raftcraft.dungeons.generic.DungeonInstance;
import com.jothapunkt.spigot.raftcraft.dungeons.generic.Dungeons;
import net.md_5.bungee.api.ChatColor;


public class CommandDungeons implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (args.length >= 2 && args[0].equals("start")) {
            String name = args[1];

            Dungeons dungeonType = Dungeons.find(name);

            if (dungeonType == null) {
                player.sendMessage(ChatColor.RED + "Unknown dungeon type");
                return true;
            }

            DungeonInstance instance = dungeonType.instanciate();
            instance.send(player);

            return true;
        }

        return false;
    }
}
