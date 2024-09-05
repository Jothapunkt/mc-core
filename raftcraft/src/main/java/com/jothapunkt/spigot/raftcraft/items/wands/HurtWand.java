package com.jothapunkt.spigot.raftcraft.items.wands;

import java.awt.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.jothapunkt.spigot.raftcraft.abilities.items.HurtAbility;
import com.jothapunkt.spigot.raftcraft.abilities.items.BeamAttack;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.types.Rarity;
import com.jothapunkt.spigot.raftcraft.util.Colors;

public class HurtWand extends CustomItem {
    public HurtWand() {
        rarity = Rarity.EPIC;
        name = Colors.gradientText("Hurt Wand", new Color(255, 0, 0), new Color(153, 0, 0));
        baseItem = new ItemStack(Material.BLAZE_ROD);
        enchantGlint = true;
        ability(new BeamAttack());
        ability(new HurtAbility());
    }
}
