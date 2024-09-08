package com.jothapunkt.spigot.raftcraft.commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.jothapunkt.spigot.raftcraft.rafts.Raft;
import com.jothapunkt.spigot.raftcraft.util.Worlds;

import net.md_5.bungee.api.ChatColor;


public class CommandRaft implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 1 && args[0].equals("info")) {
            Raft raft = new Raft(player);
            player.sendMessage("Key: " + raft.getWorldKey().toString());
            player.sendMessage("Key: " + raft.getWorldKey().asString());
            player.sendMessage("Key: " + raft.getWorldKey().asMinimalString());
        }

        if (args.length == 1 && args[0].equals("delete")) {
            if (Worlds.delete(new Raft(player).getWorld(player))) {
                player.sendMessage(ChatColor.RED + "Deleted your raft. Type /raft to create a new one.");
            } else {
                player.sendMessage(ChatColor.RED + "Something went wrong - not able to delete your raft.");
            }
            return true;
        }

        if (args.length == 0) {
            Raft raft = new Raft(player);
            player.teleport(raft.getWorld(player).getSpawnLocation());
            return true;
        }

        return false;
    }
}
