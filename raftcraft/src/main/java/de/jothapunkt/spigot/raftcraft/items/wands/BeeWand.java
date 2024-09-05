package com.jothapunkt.spigot.raftcraft.items.wands;

import java.awt.Color;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import com.jothapunkt.spigot.raftcraft.abilities.items.BeamAttack;
import com.jothapunkt.spigot.raftcraft.abilities.items.BeeShotAbility;
import com.jothapunkt.spigot.raftcraft.abilities.items.generic.RightClickAbility;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.items.generic.Wand;
import com.jothapunkt.spigot.raftcraft.types.Rarity;
import com.jothapunkt.spigot.raftcraft.util.Colors;

import net.md_5.bungee.api.ChatColor;


public class BeeWand extends Wand {
    public BeeWand() {
        rarity = Rarity.RARE;
        name = "Bee Wand";
        baseItem = new ItemStack(Material.BLAZE_ROD);
        enchantGlint = true;
        ability(new BeamAttack());
        ability(new BeeShotAbility());
    }
}
