package com.jothapunkt.spigot.raftcraft.setup;

import com.jothapunkt.spigot.raftcraft.mobs.ice.IceKing;
import com.jothapunkt.spigot.raftcraft.mounts.PigMount;
import com.jothapunkt.spigot.raftcraft.util.CustomClassRegistry;

public class RegisterMobs {
    public static void register() {
        CustomClassRegistry registry = CustomClassRegistry.getInstance();

        registry.register(new IceKing());
        registry.register(new PigMount());
    }
}
