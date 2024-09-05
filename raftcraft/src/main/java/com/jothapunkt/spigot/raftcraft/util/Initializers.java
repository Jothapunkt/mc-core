package com.jothapunkt.spigot.raftcraft.util;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;

import com.jothapunkt.spigot.raftcraft.types.Stat;

public class Initializers {
    public static void scoreboard() {
        if (Bukkit.getScoreboardManager().getMainScoreboard().getObjective("PlayerHealth") == null) {
            Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective("PlayerHealth", Criteria.DUMMY, Stat.HEALTH.getShortName());
        }
        Bukkit.getScoreboardManager().getMainScoreboard().getObjective("PlayerHealth").setAutoUpdateDisplay(true);
        Bukkit.getScoreboardManager().getMainScoreboard().getObjective("PlayerHealth").setDisplaySlot(DisplaySlot.BELOW_NAME);

        if (Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Health") == null) {
            Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective("Health", Criteria.HEALTH, Stat.HEALTH.getShortName());
        }
        Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Health").setAutoUpdateDisplay(true);
        Bukkit.getScoreboardManager().getMainScoreboard().getObjective("Health").setDisplaySlot(DisplaySlot.BELOW_NAME);
    }
}
