package com.jothapunkt.spigot.raftcraft.abilities.items;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import java.util.List;

import com.jothapunkt.spigot.raftcraft.abilities.items.generic.RightClickAbility;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;

import net.md_5.bungee.api.ChatColor;

public class InspectAbility extends RightClickAbility {
    private double amount = 9999.0;

    public InspectAbility() {
        description.add("Check Metadata of blocks and entities");
    }

    @Override
    public void use(Player player) {
        player.damage(amount);
    }
}
