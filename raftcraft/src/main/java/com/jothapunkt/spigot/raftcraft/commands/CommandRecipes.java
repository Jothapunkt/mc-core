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
import com.jothapunkt.spigot.raftcraft.gui.RecipeListGUI;
import com.jothapunkt.spigot.raftcraft.recipes.CustomRecipeRegistry;

import net.md_5.bungee.api.ChatColor;


public class CommandRecipes implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Recipes: " + CustomRecipeRegistry.getInstance().getRecipes().size());
            new RecipeListGUI().show(player);
            return true;
        }

        return false;
    }
}
