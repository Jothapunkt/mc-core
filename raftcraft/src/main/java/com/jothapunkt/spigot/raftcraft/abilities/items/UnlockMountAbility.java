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

public class UnlockMountAbility extends MoveToStorageAbility {
    public UnlockMountAbility() {
        super("mounts");
    }

    @Override
    public void use(Player player) {
        super.use(player);
        player.sendMessage(ChatColor.GREEN + "Added mount to your /mounts inventory");
    }
}
