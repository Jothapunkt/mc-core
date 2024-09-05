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
import com.jothapunkt.spigot.raftcraft.util.Numbers;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;
import com.jothapunkt.spigot.raftcraft.mounts.generic.Mount;
import com.jothapunkt.spigot.raftcraft.types.Skills;
import com.jothapunkt.spigot.raftcraft.mobs.MobRegistry;

import net.md_5.bungee.api.ChatColor;


public class CommandSkills implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        PlayerInfo info = new PlayerInfo(player);

        if (args.length == 0) {
            for (Skills skill : Skills.values()) {
                double xp = info.getSkillXP(skill);
                player.sendMessage(
                    skill.getLongName() + " Lvl" + skill.getSkill().getLevel(xp)
                    + " " + skill.getSkill().getRemaining(xp) + " / " + skill.getSkill().getNextRequirement(xp)
                    + " (" + Numbers.getPercentageString(skill.getSkill().getProgress(xp)) + ")"
                );
            }
            return true;
        }

        if (args.length >= 1 && args[0].equalsIgnoreCase("reset")) {
            for (Skills skill : Skills.values()) {
                info.setCounter(skill.getSkillKey(), 0.0);
            }
            return true;
        }

        return false;
    }
}
