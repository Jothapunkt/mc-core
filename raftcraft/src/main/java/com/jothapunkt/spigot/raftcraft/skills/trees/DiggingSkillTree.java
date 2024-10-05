package com.jothapunkt.spigot.raftcraft.skills.trees;

import java.util.List;

import net.md_5.bungee.api.ChatColor;

public class DiggingSkillTree extends PerkTree {
    public DiggingSkillTree() {
        name = "Digging";

        node(2, new Perk(
                "Speedy Digger",
                (Integer level) -> List.of("Grants " + ChatColor.GOLD + ((level + 1) * 10) + " Digging Speed")
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
