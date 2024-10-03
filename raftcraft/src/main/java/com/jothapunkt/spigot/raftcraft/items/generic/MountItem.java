package com.jothapunkt.spigot.raftcraft.items.generic;

import org.bukkit.entity.Mob;

import com.jothapunkt.spigot.raftcraft.abilities.items.UnlockMountAbility;
import com.jothapunkt.spigot.raftcraft.mounts.generic.Mount;

import net.kyori.adventure.util.TriState;

public class MountItem extends CustomItem {
    protected Mount mount;

    public <T extends Mob> MountItem(Mount<T> mount) {
        type = "Mount";
        this.mount = mount;
        stackable = TriState.FALSE;
        ability(new UnlockMountAbility());
    }

    public Mount getMount() {
        return mount;
    }
}
