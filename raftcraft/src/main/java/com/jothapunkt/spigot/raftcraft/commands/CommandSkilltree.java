package com.jothapunkt.spigot.raftcraft.commands;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.jothapunkt.spigot.raftcraft.gui.SkillTreeGUI;
import com.jothapunkt.spigot.raftcraft.skills.trees.CombatSkillTree;
import com.jothapunkt.spigot.raftcraft.skills.trees.SkillTree;


public class CommandSkilltree implements CommandExecutor {
    protected List<SkillTree> skilltrees = List.of(
        new CombatSkillTree()
    );

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            return false;
        }

        if (args.length >= 1) {
            for (SkillTree tree : skilltrees) {
                if (tree.getName().equalsIgnoreCase(args[0])) {
                    new SkillTreeGUI(player, tree).show(player);
                    return true;
                }
            }
        }

        return false;
    }
}
