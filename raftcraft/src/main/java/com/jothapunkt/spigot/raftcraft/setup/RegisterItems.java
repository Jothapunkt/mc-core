package com.jothapunkt.spigot.raftcraft.setup;

import com.jothapunkt.spigot.raftcraft.entities.mobs.ice.IceKing;
import com.jothapunkt.spigot.raftcraft.items.blocks.CustomSpawner;
import com.jothapunkt.spigot.raftcraft.items.menu.MenuItem;
import com.jothapunkt.spigot.raftcraft.items.menu.MountControlItem;
import com.jothapunkt.spigot.raftcraft.items.swords.Claymore;
import com.jothapunkt.spigot.raftcraft.items.wands.IceStaff;
import com.jothapunkt.spigot.raftcraft.util.CustomClassRegistry;

public class RegisterItems {
    public static void register() {
        CustomClassRegistry registry = CustomClassRegistry.getInstance();

        registry.register(new Claymore());
        registry.register(new IceStaff());
        registry.register(new MenuItem());
        registry.register(new MountControlItem());
        registry.register(new CustomSpawner(new IceKing()));
    }
}
