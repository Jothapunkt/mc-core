package com.jothapunkt.spigot.raftcraft.worlds;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import com.jothapunkt.spigot.raftcraft.util.CustomClass;

public class FlatWorld extends CustomWorld {
    protected WorldCreator getWorldCreator(String name) {
        WorldCreator creator = new WorldCreator(name);
        creator.type(WorldType.FLAT);
        return creator;
    }
}
