package com.jothapunkt.spigot.raftcraft.mobs.generic;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.abilities.items.generic.ItemAbility;
import com.jothapunkt.spigot.raftcraft.items.blocks.CustomSpawner;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.items.generic.VanillaItem;
import com.jothapunkt.spigot.raftcraft.types.Rarity;
import com.jothapunkt.spigot.raftcraft.types.Skills;
import com.jothapunkt.spigot.raftcraft.types.Stat;
import com.jothapunkt.spigot.raftcraft.util.CustomClass;
import com.jothapunkt.spigot.raftcraft.util.LootTable;
import com.jothapunkt.spigot.raftcraft.util.MobInfo;
import com.jothapunkt.spigot.raftcraft.util.Numbers;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;

import net.md_5.bungee.api.ChatColor;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.spawner.BaseSpawner;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.HashMap;


public class CustomMob extends CustomClass<Mob> {
    protected Rarity rarity = Rarity.COMMON;
    protected String name = "Test Mob";
    protected EntityType type = EntityType.ZOMBIE;
    protected List<String> description = List.of();
    protected HashMap<Stat, Double> stats = new HashMap<>();
    protected HashMap<EquipmentSlot, CustomItem> equipment = new HashMap<>();
    protected List<ItemAbility> abilities = new ArrayList<>();
    protected boolean invisible = false;
    protected int level = 1;

    public CustomMob() {
        stat(Stat.MAX_HEALTH, 20.0);
        stat(Stat.ARMOR, 0.0);
    }

    public void equip(EquipmentSlot slot, CustomItem item) {
        equipment.put(slot, item);
    }

    public void onKill(Mob instance, Player killer) {
        new PlayerInfo(killer).grantSkillXP(Skills.COMBAT, 10 + level);
    }

    public String getInstanceName(Mob instance) {
        Double health = new MobInfo(instance).getStat(Stat.HEALTH);

        if (health == null) {
            Bukkit.broadcastMessage("No health value for " + name + " (" + type + "), removing");
            instance.remove();
            return "[Lv " + level + "] " + name + " " + Stat.HEALTH.getShortName() + " " + "???";
        }

        return "[Lv " + level + "] " + name + " " + Stat.HEALTH.getShortName() + " " + Numbers.getDisplayString(health);
    }

    public void stat(Stat stat, Double value) {
        stats.put(stat, value);
    }

    @Override
    public Mob instantiateRaw(Location location) {
        return spawn(location);
    }

    public Mob spawn(Location location) {
        Mob mob = (Mob) location.getWorld().spawnEntity(location, type, false);
        MobInfo info = new MobInfo(mob);

        // Set stats
        if (stats.keySet().contains(Stat.MAX_HEALTH)) {
            stats.put(Stat.HEALTH, stats.get(Stat.MAX_HEALTH));
        }

        for (Entry<Stat, Double> stat: stats.entrySet()) {
            info.setStat(stat.getKey(), stat.getValue());
        }
        
        mob.setCustomName(getInstanceName(mob));
        mob.setCustomNameVisible(true);
        mob.setInvisible(invisible);

        for (Entry<EquipmentSlot, CustomItem> equip : equipment.entrySet()) {
            mob.getEquipment().setItem(equip.getKey(), equip.getValue().print());
        }
        
        return mob;
    }

    public String getName() {
        return name;
    }

    public ItemStack getSpawner() {
        return new CustomSpawner(this).print();
    }

    public EntityType getType() {
        return type;
    }

    public HashMap<Stat, Double> getStats() {
        return stats;
    }
}
