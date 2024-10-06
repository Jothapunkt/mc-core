package com.jothapunkt.spigot.raftcraft.skills.trees;

import java.util.List;

import com.jothapunkt.spigot.raftcraft.types.Stat;
import com.jothapunkt.spigot.raftcraft.util.Series;

import net.md_5.bungee.api.ChatColor;

public class CombatSkillTree extends PerkTree {
    public CombatSkillTree() {
        name = "Combat";

        node(2, new Perk(
                "Thick Skin",
                (Integer level) -> List.of(ChatColor.GRAY + "Grants " + ChatColor.GOLD + ((level + 1) * 10) + " " + Stat.ARMOR.getLongName())
            ).setLevelCosts(Series.flat(1, 20))
        );

        node(4, new Perk(
                "Ferocious",
                (Integer level) -> List.of(ChatColor.GRAY + "Grants " + ChatColor.GOLD + ((level + 1) * 5) + " " + Stat.ATTACK.getLongName())
            ).setLevelCosts(Series.flat(1, 20))
        );
    }
}
