package com.jothapunkt.spigot.raftcraft.commands;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.World;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.util.Deserialize;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;
import com.jothapunkt.spigot.raftcraft.util.Serialize;

import net.md_5.bungee.api.ChatColor;


public class CommandTinker implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        PlayerInfo info = new PlayerInfo(player);
        Bukkit.broadcastMessage("Tinker");

        if (args.length >= 1 && args[0].equalsIgnoreCase("save")) {
            if (player.getInventory().getItemInMainHand() == null) {
                player.sendMessage(ChatColor.RED + "Not holding anything.");
            } else {
                try {
                    String data = Serialize.base64(player.getInventory().getItemInMainHand());

                    player.sendMessage(data);

                    player.getPersistentDataContainer().set(
                        new NamespacedKey(RaftCraft.getInstance(), "tinkerItem"),
                        PersistentDataType.STRING,
                        data
                    );

                    player.sendMessage("Stored: " + player.getInventory().getItemInMainHand());
                } catch(IOException e) {
                    Bukkit.getLogger().warning(e.toString());
                    Bukkit.broadcastMessage(e.toString());
                }
                
            }
            return true;
        }

        if (args.length >= 1 && args[0].equalsIgnoreCase("restore")) {
            try {
                String data = player.getPersistentDataContainer().get(
                    new NamespacedKey(RaftCraft.getInstance(), "tinkerItem"),
                    PersistentDataType.STRING
                );

                player.sendMessage(data);

                ItemStack storedItem = Deserialize.item(data);
                player.sendMessage("Restored: " + storedItem);

                player.getInventory().addItem(storedItem);
            } catch(IOException e) {
                Bukkit.getLogger().warning(e.toString());
                Bukkit.broadcastMessage(e.toString());
            } catch(ClassNotFoundException e) {
                Bukkit.getLogger().warning(e.toString());
                Bukkit.broadcastMessage(e.toString());
            }

            return true;
        }

        return false;
    }
}
