package com.jothapunkt.spigot.raftcraft.setup;

import com.jothapunkt.spigot.raftcraft.entities.mobs.ice.IceKing;
import com.jothapunkt.spigot.raftcraft.mounts.PhantomMount;
import com.jothapunkt.spigot.raftcraft.mounts.PigMount;
import com.jothapunkt.spigot.raftcraft.util.CustomClassRegistry;

public class RegisterMobs {
    public static void register() {
        CustomClassRegistry registry = CustomClassRegistry.getInstance();

        registry.register(new IceKing());
        registry.register(new PigMount());
        registry.register(new PhantomMount());
    }
}
