package com.jothapunkt.spigot.raftcraft.abilities.items;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import java.util.List;

import com.jothapunkt.spigot.raftcraft.abilities.items.generic.RightClickAbility;
import com.jothapunkt.spigot.raftcraft.util.PersistentData;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;

import net.md_5.bungee.api.ChatColor;

public class MoveToStorageAbility extends RightClickAbility {
    private String storageKey;

    public MoveToStorageAbility(String storageKey) {
        this.storageKey = storageKey;
    }

    @Override
    public void use(Player player) {
        ItemStack itemToMove = player.getInventory().getItemInMainHand();
        player.getInventory().setItemInMainHand(null);
        PersistentData.from(player).addItem(itemToMove, storageKey);
    }
}
