package com.jothapunkt.spigot.raftcraft.skills.trees;

import java.util.List;

import net.md_5.bungee.api.ChatColor;

public class CombatSkillTree extends SkillTree {
    public CombatSkillTree() {
        name = "Combat";

        node(2, new SkillTreeNode(
                "Combat",
                "Speedy Digger",
                (Integer level) -> List.of("Grants " + ChatColor.GOLD + ((level + 1) * 10) + " Digging Speed")
            ).setLevelCosts(List.of(
                1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1
            ))
        );

        node(4, new SkillTreeNode(
                "Combat",
                "Spelunker",
                (Integer level) -> List.of("Grants " + ChatColor.GOLD + ((level + 1) * 5) + " Bonus chance to dig up treasures")
            ).setLevelCosts(List.of(
                1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1
            ))
        );
    }
}
