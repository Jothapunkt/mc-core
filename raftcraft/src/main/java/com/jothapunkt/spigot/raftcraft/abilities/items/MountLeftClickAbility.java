package com.jothapunkt.spigot.raftcraft.abilities.items;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import java.util.List;

import com.jothapunkt.spigot.raftcraft.abilities.items.generic.LeftClickAbility;
import com.jothapunkt.spigot.raftcraft.abilities.items.generic.RightClickAbility;
import com.jothapunkt.spigot.raftcraft.mounts.generic.Mount;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;

import net.md_5.bungee.api.ChatColor;

public class MountLeftClickAbility extends LeftClickAbility {
    public MountLeftClickAbility() {
        description.add("Use the left click ability of your mount");
    }

    @Override
    public void onLeftClick(PlayerInteractEvent event) {
        Mount mount = new PlayerInfo(event.getPlayer()).getMount();

        if (mount != null) {
            mount.onLeftClick(event);
        }
    }
}
