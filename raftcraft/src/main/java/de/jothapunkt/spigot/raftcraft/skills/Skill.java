package com.jothapunkt.spigot.raftcraft.skills;

import java.util.HashMap;
import java.util.List;

import com.jothapunkt.spigot.raftcraft.types.Skills;
import com.jothapunkt.spigot.raftcraft.types.Stat;

public class Skill {
    protected Skills skill;

    protected List<Integer> xpThresholds = List.of(
        50,
        100,
        150,
        250,
        350,
        500,
        750,
        1000,
        1500,
        2000,
        2750,
        3500,
        5000,
        6500,
        8000,
        10000,
        12000,
        15000,
        18000,
        21000,
        25000,
        30000,
        35000,
        40000,
        50000,
        60000,
        75000
    );

    public Skill(Skills skill) {
        this.skill = skill;
    }

    public int getLevel(double totalXP) {
        int level = 0;
        double remaining = totalXP;

        for (Integer requirement : xpThresholds) {
            if (remaining < requirement) {
                break;
            }

            remaining -= requirement;
            level++;
        }

        return level;
    }

    public double getRemaining(double totalXP) {
        double remaining = totalXP;

        for (Integer requirement : xpThresholds) {
            if (remaining < requirement) {
                break;
            }

            remaining -= requirement;
        }

        return remaining;
    }

    public int getNextRequirement(double totalXP) {
        int level = getLevel(totalXP);
        if (level >= xpThresholds.size() - 1) {
            return 0;
        }
        return xpThresholds.get(level);
    }

    public double getProgress(double totalXP) {
        int requirement = getNextRequirement(totalXP);
        if (requirement == 0) {
            return 100.0;
        }
        double remaining = getRemaining(totalXP);
        return remaining / requirement;
    }

    public HashMap<Stat, Double> getStatBoni() {
        return new HashMap<>();
    }
}
