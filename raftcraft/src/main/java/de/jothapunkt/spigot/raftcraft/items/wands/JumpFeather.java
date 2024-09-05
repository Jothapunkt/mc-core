package com.jothapunkt.spigot.raftcraft.items.wands;

import java.awt.Color;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.jothapunkt.spigot.raftcraft.abilities.items.JumpAbility;
import com.jothapunkt.spigot.raftcraft.abilities.items.generic.ItemAbility;
import com.jothapunkt.spigot.raftcraft.abilities.items.generic.RightClickAbility;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.types.Rarity;
import com.jothapunkt.spigot.raftcraft.util.Colors;


public class JumpFeather extends CustomItem {
    public JumpFeather() {
        rarity = Rarity.EPIC;
        name = Colors.gradientText("Jump Feather", new Color(255, 255, 255), new Color(130, 130, 130));
        baseItem = new ItemStack(Material.FEATHER);
        enchantGlint = true;
        ability(new JumpAbility());
    }
}
