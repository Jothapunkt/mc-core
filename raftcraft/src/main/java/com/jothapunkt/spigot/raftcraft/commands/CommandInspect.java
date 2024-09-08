package com.jothapunkt.spigot.raftcraft.commands;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.rafts.Raft;
import com.jothapunkt.spigot.raftcraft.util.Worlds;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;


public class CommandInspect implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0 || (args.length == 1 && args[0].equals("item"))) {
            ItemStack item = player.getInventory().getItemInMainHand();

            if (item == null) {
                player.sendMessage(ChatColor.RED + "You're not holding anything!");
                return true;
            }

            player.sendMessage(
                Component.text("Data for " + item.toString())
                .hoverEvent(item.asHoverEvent())
            );

            for (NamespacedKey key : item.getItemMeta().getPersistentDataContainer().getKeys()) {
                String value = item.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING);
                player.sendMessage("- " + key.asMinimalString() + ": " + value);
            }

            return true;
        }

        if (args.length == 1 && args[0].equals("block")) {
            Block target = player.getTargetBlock(100);

            if (target == null) {
                player.sendMessage(ChatColor.RED + "You're not looking at any block.");
                return true;
            }

            player.sendMessage(
                Component.text("Data for " + target.getType())
            );

            if (target.getState() instanceof PersistentDataHolder holder) {
                for (NamespacedKey key : holder.getPersistentDataContainer().getKeys()) {
                    String value = holder.getPersistentDataContainer().get(key, PersistentDataType.STRING);
                    player.sendMessage("- " + key.asMinimalString() + ": " + value);
                }
            }

            return true;
        }

        return false;
    }
}
