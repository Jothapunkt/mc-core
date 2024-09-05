package com.jothapunkt.spigot.raftcraft.items.wands;

import java.awt.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.jothapunkt.spigot.raftcraft.abilities.items.ShieldAbility;
import com.jothapunkt.spigot.raftcraft.abilities.items.BeamAttack;
import com.jothapunkt.spigot.raftcraft.abilities.items.generic.RightClickAbility;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.types.Rarity;
import com.jothapunkt.spigot.raftcraft.util.Colors;

public class ShieldWand extends CustomItem {
    public ShieldWand() {
        rarity = Rarity.EPIC;
        name = Colors.gradientText("Shield Wand", new Color(255, 51, 204), new Color(153, 0, 204));
        baseItem = new ItemStack(Material.BREEZE_ROD);
        enchantGlint = true;
        ability(new BeamAttack());
        ability(new ShieldAbility());
    }
}
