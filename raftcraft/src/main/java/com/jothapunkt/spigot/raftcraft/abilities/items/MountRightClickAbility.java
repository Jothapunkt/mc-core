package com.jothapunkt.spigot.raftcraft.abilities.items;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import java.util.List;

import com.jothapunkt.spigot.raftcraft.abilities.items.generic.RightClickAbility;
import com.jothapunkt.spigot.raftcraft.mounts.generic.Mount;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;

import net.md_5.bungee.api.ChatColor;

public class MountRightClickAbility extends RightClickAbility {
    public MountRightClickAbility() {
        description.add("Use the right click ability of your mount");
    }

    @Override
    public void onRightClick(PlayerInteractEvent event) {
        Mount mount = new PlayerInfo(event.getPlayer()).getMount();

        if (mount != null) {
            mount.onRightClick(event);
        }
    }
}
