package com.jothapunkt.spigot.raftcraft.skills.trees;

import java.util.List;

import com.jothapunkt.spigot.raftcraft.types.Stat;

import net.md_5.bungee.api.ChatColor;

public class CombatSkillTree extends PerkTree {
    public CombatSkillTree() {
        name = "Combat";

        node(2, new Perk(
                "Thick Skin",
                (Integer level) -> List.of(ChatColor.GRAY + "Grants " + ChatColor.GOLD + ((level + 1) * 10) + " " + Stat.ARMOR.getLongName())
            ).setLevelCosts(List.of(
                1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1
            ))
        );

        node(4, new Perk(
                "Spelunker",
                (Integer level) -> List.of("Grants " + ChatColor.GOLD + ((level + 1) * 5) + " Bonus chance to dig up treasures")
            ).setLevelCosts(List.of(
                1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1
            ))
        );
    }
}
