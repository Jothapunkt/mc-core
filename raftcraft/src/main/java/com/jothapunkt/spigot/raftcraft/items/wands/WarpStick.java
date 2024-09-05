package com.jothapunkt.spigot.raftcraft.items.wands;

import java.awt.Color;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.jothapunkt.spigot.raftcraft.abilities.items.TeleportAbility;
import com.jothapunkt.spigot.raftcraft.abilities.items.BeamAttack;
import com.jothapunkt.spigot.raftcraft.abilities.items.generic.RightClickAbility;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.items.generic.Wand;
import com.jothapunkt.spigot.raftcraft.types.Rarity;
import com.jothapunkt.spigot.raftcraft.util.Colors;

import net.md_5.bungee.api.ChatColor;


public class WarpStick extends Wand {
    public WarpStick() {
        rarity = Rarity.EPIC;
        name = Colors.gradientText("Warp Stick", new Color(255, 51, 204), new Color(153, 0, 204));
        baseItem = new ItemStack(Material.BREEZE_ROD);
        enchantGlint = true;
        ability(new BeamAttack());
        ability(new TeleportAbility());
    }
}
