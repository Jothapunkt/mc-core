package com.jothapunkt.spigot.raftcraft.worlds;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import com.jothapunkt.spigot.raftcraft.rafts.OceanGenerator;

public class OceanWorld extends FlatWorld {
    protected WorldCreator getWorldCreator(String name) {
        WorldCreator creator = new WorldCreator(name);
        creator.generator(new OceanGenerator());
        return creator;
    }
}
