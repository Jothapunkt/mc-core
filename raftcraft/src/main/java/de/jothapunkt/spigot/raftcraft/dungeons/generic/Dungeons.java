package com.jothapunkt.spigot.raftcraft.dungeons.generic;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.Material;

import com.jothapunkt.spigot.raftcraft.dungeons.TreasureIsland;

public enum Dungeons {
    TREASURE_ISLAND(new TreasureIsland());

    private Dungeon dungeon;

    private Dungeons(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public DungeonInstance instanciate() {
        return dungeon.instantiate();
    }

    public static Dungeons find(String input) {
        String name = input.toUpperCase().replace(" ", "_");

        if (Arrays.stream(Dungeons.values()).map(Enum::name).collect(Collectors.toList()).contains(name)) {
            return Dungeons.valueOf(name);
        }

        return null;
    }
}
