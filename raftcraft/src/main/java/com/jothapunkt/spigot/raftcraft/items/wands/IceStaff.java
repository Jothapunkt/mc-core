package com.jothapunkt.spigot.raftcraft.items.wands;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.abilities.items.BeamAttack;
import com.jothapunkt.spigot.raftcraft.abilities.items.IcicleAbility;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.types.Rarity;
import com.jothapunkt.spigot.raftcraft.recipes.CustomRecipeRegistry;
import com.jothapunkt.spigot.raftcraft.recipes.CustomRecipe;


public class IceStaff extends CustomItem {
    public IceStaff() {
        rarity = Rarity.EPIC;
        name = "Ice Staff";
        baseItem = new ItemStack(Material.DIAMOND_HOE);
        ability(new BeamAttack(Particle.SNOWFLAKE));
        ability(new IcicleAbility());
    }
}
