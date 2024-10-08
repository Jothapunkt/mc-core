package com.jothapunkt.spigot.raftcraft.setup;

import com.jothapunkt.spigot.raftcraft.blocks.LockpickBarrel;
import com.jothapunkt.spigot.raftcraft.entities.mobs.ice.IceKing;
import com.jothapunkt.spigot.raftcraft.items.blocks.CustomSpawner;
import com.jothapunkt.spigot.raftcraft.items.blocks.LockpickBarrelItem;
import com.jothapunkt.spigot.raftcraft.items.equipment.belt.ClothBelt;
import com.jothapunkt.spigot.raftcraft.items.equipment.necklace.RubyAmulet;
import com.jothapunkt.spigot.raftcraft.items.equipment.ring.SilverRing;
import com.jothapunkt.spigot.raftcraft.items.menu.MenuItem;
import com.jothapunkt.spigot.raftcraft.items.menu.MountControlItem;
import com.jothapunkt.spigot.raftcraft.items.mounts.DolphinItem;
import com.jothapunkt.spigot.raftcraft.items.mounts.PhantomItem;
import com.jothapunkt.spigot.raftcraft.items.swords.Claymore;
import com.jothapunkt.spigot.raftcraft.items.wands.IceStaff;
import com.jothapunkt.spigot.raftcraft.util.CustomClassRegistry;

public class RegisterItems {
    public static void register() {
        CustomClassRegistry registry = CustomClassRegistry.getInstance();

        registry.register(new Claymore());
        registry.register(new ClothBelt());
        registry.register(new CustomSpawner(new IceKing()));
        registry.register(new DolphinItem());
        registry.register(new IceStaff());
        registry.register(new LockpickBarrel());
        registry.register(new LockpickBarrelItem());
        registry.register(new MenuItem());
        registry.register(new MountControlItem());
        registry.register(new PhantomItem());
        registry.register(new RubyAmulet());
        registry.register(new SilverRing());
    }
}
