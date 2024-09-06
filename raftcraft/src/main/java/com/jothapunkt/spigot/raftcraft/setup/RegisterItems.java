package com.jothapunkt.spigot.raftcraft.setup;

import com.jothapunkt.spigot.raftcraft.items.swords.Claymore;
import com.jothapunkt.spigot.raftcraft.items.wands.IceStaff;
import com.jothapunkt.spigot.raftcraft.util.CustomClassRegistry;

public class RegisterItems {
    public static void register() {
        CustomClassRegistry registry = CustomClassRegistry.getInstance();

        registry.register(new Claymore());
        registry.register(new IceStaff());
    }
}
