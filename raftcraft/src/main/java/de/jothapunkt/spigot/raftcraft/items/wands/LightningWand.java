package com.jothapunkt.spigot.raftcraft.items.wands;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.abilities.items.BeamAttack;
import com.jothapunkt.spigot.raftcraft.abilities.items.LightningAbility;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.types.Rarity;


public class LightningWand extends CustomItem {
    public LightningWand() {
        rarity = Rarity.LEGENDARY;
        name = "Lightning Wand";
        baseItem = new ItemStack(Material.LIGHTNING_ROD);
        enchantGlint = true;
        ability(new BeamAttack(Particle.ANGRY_VILLAGER));
        ability(new LightningAbility());
    }
}
