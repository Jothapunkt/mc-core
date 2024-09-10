package com.jothapunkt.spigot.raftcraft.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.jothapunkt.spigot.raftcraft.abilities.items.generic.RightClickAbility;
import com.jothapunkt.spigot.raftcraft.entities.mobs.MobRegistry;
import com.jothapunkt.spigot.raftcraft.entities.mobs.generic.CustomMob;
import com.jothapunkt.spigot.raftcraft.items.ItemRegistry;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.util.Displays;
import com.jothapunkt.spigot.raftcraft.util.MobInfo;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;


public class CombatListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onDamage(EntityDamageEvent event) {
        // Player damage handled separately
        if (!(event.getEntity() instanceof Mob)) {
            return;
        }

        // Damage types that are handled separately
        if (List.of(
            DamageCause.ENTITY_ATTACK,
            DamageCause.ENTITY_SWEEP_ATTACK
        ).contains(event.getCause())) {
            return;
        }

        // Mobs take no fall damage
        if (event.getCause() == DamageCause.FALL) {
            event.setCancelled(true);
            return;
        }

        Bukkit.broadcastMessage("Damage type: " + event.getCause().name());

        Displays.damageDisplay(event.getEntity().getLocation(), event.getDamage(), com.jothapunkt.spigot.raftcraft.types.DamageType.NORMAL);
        Bukkit.broadcastMessage("Dealt " + event.getDamage() + " damage");
        Bukkit.broadcastMessage("Cancelled: " + event.isCancelled());
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        // Only handle player damage
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        // Damage types that are handled separately
        if (List.of(
            DamageCause.ENTITY_ATTACK,
            DamageCause.ENTITY_SWEEP_ATTACK
        ).contains(event.getCause())) {
            return;
        }

        Player player = (Player) event.getEntity();
        PlayerInfo info = new PlayerInfo(player);

        // Set damage x5 as baseline
        event.setDamage(event.getDamage() * 5);

        info.applyDamage(event);
    }

    @EventHandler
    public void onPlayerDamageByEntity(EntityDamageByEntityEvent event) {
        // Only handle player damage
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();
        PlayerInfo info = new PlayerInfo(player);

        // Set damage x5 as baseline
        event.setDamage(event.getDamage() * 5);
        info.applyDamage(event);
    }

    @EventHandler
    public void onEntityDamageByPlayer(EntityDamageByEntityEvent event) {
        // Only handle player damage to entities
        if (!(event.getDamager() instanceof Player && event.getEntity() instanceof Mob)) {
            return;
        }

        Player player = (Player) event.getDamager();
        PlayerInfo info = new PlayerInfo(player);
        
        MobInfo mob = new MobInfo((Mob) event.getEntity());

        if (info.isCrit()) {
            mob.applyDamage(player, info.getCritMeleeDamage(), com.jothapunkt.spigot.raftcraft.types.DamageType.CRIT);
            mob.applyKnockback(player.getLocation(), 1);
        } else {
            mob.applyDamage(player, info.getMeleeDamage(), com.jothapunkt.spigot.raftcraft.types.DamageType.NORMAL);
            mob.applyKnockback(player.getLocation(), 1);
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        // Only handle mob deaths
        if (!(event.getEntity() instanceof Mob)) {
            return;
        }

        // Disable default drops
        event.getDrops().clear();
        event.setDroppedExp(0);

        MobInfo info = new MobInfo((Mob) event.getEntity());
        MetadataValue uuidValue = info.getMeta().get("LastDamagedBy");

        if (uuidValue != null) {
            Player killer = Bukkit.getPlayer(UUID.fromString(uuidValue.asString()));
            info.getMob().onKill(info.getInstance(), killer);
            Bukkit.broadcastMessage(info.getInstance().getCustomName() + " killed by " + killer.getName());
        } else {
            Bukkit.broadcastMessage(info.getInstance().getCustomName() + " died without killer");
        }
    }
}