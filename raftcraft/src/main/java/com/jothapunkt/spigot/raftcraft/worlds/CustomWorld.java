package com.jothapunkt.spigot.raftcraft.worlds;

import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import com.jothapunkt.spigot.raftcraft.util.CustomClass;

import net.kyori.adventure.util.TriState;

public class CustomWorld extends CustomClass<World> {
    protected void applyGameRules(World world) {
        world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        world.setGameRule(GameRule.MOB_GRIEFING, false);
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        world.setGameRule(GameRule.DO_FIRE_TICK, false);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.KEEP_INVENTORY, true);
        world.setGameRule(GameRule.PROJECTILES_CAN_BREAK_BLOCKS, false);
        world.setGameRule(GameRule.DISABLE_RAIDS, false);
        world.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
        world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
        world.setGameRule(GameRule.DO_MOB_LOOT, false);
    }

    protected WorldCreator getWorldCreator(String name) {
        WorldCreator creator = new WorldCreator(name);
        creator.generateStructures(false);
        creator.keepSpawnLoaded(TriState.FALSE);
        creator.type(WorldType.NORMAL);
        return creator;
    }

    protected World createWorld(String name) {
        return getWorldCreator(name).createWorld();
    }

    @Override
    public World instantiateRaw(String name) {
        World world = createWorld(name);
        applyGameRules(world);
        return world;
    }
}
