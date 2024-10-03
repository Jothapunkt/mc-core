package com.jothapunkt.spigot.raftcraft.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.gui.generic.GUI;
import com.jothapunkt.spigot.raftcraft.items.ItemRegistry;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.modifiers.effects.CustomEffect;
import com.jothapunkt.spigot.raftcraft.mounts.generic.Mount;
import com.jothapunkt.spigot.raftcraft.types.DamageType;
import com.jothapunkt.spigot.raftcraft.types.Skills;
import com.jothapunkt.spigot.raftcraft.types.Stat;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import com.comphenix.protocol.wrappers.Pair;

public class PlayerInfo {
    private Player player;
    private Meta m;

    public PlayerInfo(Player player) {
        this.player = player;
        this.m = new Meta(player);
    }

    public List<ItemStack> getEquippedItems() {
        List<ItemStack> items = new ArrayList<>();

        for (ItemStack item : Arrays.asList(player.getInventory().getArmorContents())) {
            items.add(item);
        }
        
        items.add(player.getInventory().getItemInMainHand());
        items.add(player.getInventory().getItemInOffHand());
        items.add(getNecklace());
        items.add(getBelt());
        Pair<ItemStack, ItemStack> rings = getRings();
        items.add(rings.getFirst());
        items.add(rings.getSecond());

        return items;
    }

    public List<CustomItem> getEquippedCustomItems() {
        List<CustomItem> items = new ArrayList<>();

        for (ItemStack item : getEquippedItems()) {
            CustomItem customItem = ItemRegistry.get(item);
            if (customItem != null) {
                items.add(customItem);
            }
        }

        return items;
    }

    public void setActionBarMessage(String message) {
        m.set("message", message);
        m.set("messageTime", System.currentTimeMillis());
        updateActionBar();
    }

    public void updateActionBar() {
        String message = Strings.pad(Stat.HEALTH.getShortName() + " " + (m.getStatRounded(Stat.HEALTH) + m.getStatRounded(Stat.SHIELD)) + "/" + m.getStatRounded(Stat.MAX_HEALTH), 10);

        if (m.getString("message") == null || System.currentTimeMillis() - m.getLong("messageTime") > 1000) {
            message += Strings.center(Stat.ARMOR.getShortName() + " " + m.getStatRounded(Stat.ARMOR), 30);
        } else {
            message += Strings.center(m.getString("message"), 30);
        }
        
        message += Strings.pad(Stat.MANA.getShortName() + m.getStatRounded(Stat.MANA) + "/" + m.getStatRounded(Stat.MAX_MANA), 15);

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
    }

    public HashMap<Stat, Double> getStats() {
        HashMap<Stat, Double> stats = new HashMap<>();

        for (Stat stat : Stat.values()) {
            Double value = m.getStat(stat);
            if (value != null) {
                stats.put(stat, value);
            }
        }

        return stats;
    }

    // To be called on player join, respawn etc. when stats should be reset. MUST be called on join or stats are not defined
    public void initializeStats() {
        calculateStats();
        m.setStat(Stat.HEALTH, m.getStat(Stat.MAX_HEALTH)); // Start out with full HP
        m.setStat(Stat.MANA, m.getStat(Stat.MAX_MANA) * 0.5); // Start out with 50% mana
        m.setStat(Stat.SHIELD, 0.0); // Start out with no shield
        
        applyStats();
    }

    public void calculateStats() {
        HashMap<Stat, Double> stats = new HashMap<>();

        // Start with default stats
        for (Stat stat : Stat.values()) {
            stats.put(stat, stat.getDefaultValue());
        }

        // Add Item Stats
        for (CustomItem item : getEquippedCustomItems()) {
            for (Entry<Stat, Double> stat: item.getStats().entrySet()) {
                stats.put(
                    stat.getKey(),
                    stats.get(stat.getKey()) + stat.getValue()
                );
            }
        }
        
        for (Entry<Stat, Double> stat: stats.entrySet()) {
            if (stat.getKey().getStatic()) { // Only static stats are recalculated, health, mana etc. is not
                m.setStat(stat.getKey(), stat.getValue());
            }
        }
    }

    public Mount getMount() {
        if (CustomClassRegistry.getInstance().get(player.getVehicle()) instanceof Mount mount) {
            return mount;
        }

        return null;
    }

    public double getMeleeDamage() {
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        int damage = 1;

        if (heldItem != null) {
            damage = ItemRegistry.get(heldItem).getDamage();
            Bukkit.broadcastMessage("Damage: " + damage);
        }

        return damage * (1 + getStat(Stat.ATTACK) / 100);
    }

    public double getCritMeleeDamage() {
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        int damage = 1;

        if (heldItem != null) {
            damage = ItemRegistry.get(heldItem).getDamage();
        }

         return damage * (1 + getStat(Stat.CRIT_DAMAGE) / 100) * (1 + getStat(Stat.ATTACK) / 100);
    }

    public boolean isCrit() {
        return new Random().nextDouble(100) <= getStat(Stat.CRIT_CHANCE);
    }

    public void applyStats() {
        HashMap<Stat, Double> stats = getStats();

        // Set speed
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(stats.get(Stat.SPEED) / 100 * 0.2);
        player.setFlySpeed(Double.valueOf(stats.get(Stat.SPEED) / 100 * 0.1).floatValue());

        // Apply regen
        m.setStat(Stat.HEALTH, m.getStat(Stat.HEALTH) + m.getStat(Stat.HEALTH_REGEN));
        m.setStat(Stat.MANA, m.getStat(Stat.MANA) + m.getStat(Stat.MANA_REGEN));

        // Clamp values
        m.clampStat(Stat.HEALTH, null, m.getStat(Stat.MAX_HEALTH));
        m.clampStat(Stat.MANA, 0.0, m.getStat(Stat.MAX_MANA));
        m.clampStat(Stat.SHIELD, 0.0, m.getStat(Stat.MAX_HEALTH));

        // Set health
        player.setHealth(Math.max(0.0, stats.get(Stat.HEALTH) / stats.get(Stat.MAX_HEALTH) * 20));
        player.setAbsorptionAmount(stats.get(Stat.SHIELD) / stats.get(Stat.MAX_HEALTH));
        Bukkit.getScoreboardManager().getMainScoreboard().getObjective("PlayerHealth").getScoreFor(player).setScore(m.getStatRounded(Stat.HEALTH));

        // Display Action Bar message
        updateActionBar();
    }

    public void applyDamage(EntityDamageEvent event) {
        applyDamage(event, DamageType.CRIT);
    }

    public GUI getCurrentGUI() {
        MetadataValue value = m.get("currentGUI");

        if (value == null) {
            return null;
        } else {
            return (GUI) value.value();
        }
    }

    public void applyDamage(EntityDamageEvent event, DamageType type) {
        player.sendMessage(ChatColor.RED + "Received " + event.getDamage() + " damage");
        m.setStat(Stat.HEALTH, m.getStat(Stat.HEALTH) - event.getDamage());

        Location textLocation = player.getLocation();
        textLocation.add(0, 1.5, 0).add(player.getLocation().getDirection().normalize());
        Displays.damageDisplay(textLocation, event.getDamage(), type);

        // Set back to regular amount for proper display
        event.setDamage(.1);
    }

    public void printStats() {
        HashMap<Stat, Double> stats = getStats();

        player.sendMessage(Stat.HEALTH.getLongName() + " " + ChatColor.WHITE + stats.get(Stat.HEALTH) + "/" + stats.get(Stat.MAX_HEALTH) + " (" + Stat.HEALTH_REGEN.getShortName() + " " + ChatColor.WHITE + stats.get(Stat.HEALTH_REGEN) + ")");
        player.sendMessage(Stat.ARMOR.getLongName() + " " + ChatColor.WHITE + stats.get(Stat.ARMOR));
        player.sendMessage("");

        player.sendMessage(Stat.ATTACK.getLongName() + " " + ChatColor.WHITE + stats.get(Stat.ATTACK));
        player.sendMessage(Stat.CRIT_CHANCE.getLongName() + " " + ChatColor.WHITE + stats.get(Stat.CRIT_CHANCE));
        player.sendMessage(Stat.CRIT_DAMAGE.getLongName() + " " + ChatColor.WHITE + stats.get(Stat.CRIT_DAMAGE));
        player.sendMessage(Stat.MANA.getLongName() + " " + ChatColor.WHITE + stats.get(Stat.MANA) + "/" + stats.get(Stat.MAX_MANA) + " (" + Stat.MANA_REGEN.getShortName() + " " + ChatColor.WHITE + stats.get(Stat.MANA_REGEN) + ")");
        player.sendMessage("");

        player.sendMessage(Stat.SPEED.getLongName() + " " + ChatColor.WHITE + stats.get(Stat.SPEED));
        player.sendMessage(Stat.SWIM_SPEED.getLongName() + " " + ChatColor.WHITE + stats.get(Stat.SWIM_SPEED));
        player.sendMessage(Stat.BOAT_SPEED.getLongName() + " " + ChatColor.WHITE + stats.get(Stat.BOAT_SPEED));

        player.sendMessage(Stat.PICKUP_RANGE.getLongName() + " " + ChatColor.WHITE + stats.get(Stat.PICKUP_RANGE));
    }

    public void setStat(Stat stat, Double value) {
        m.setStat(stat, value);
    }

    public Double getStat(Stat stat) {
        return m.getStat(stat);
    }

    public Meta getMeta() {
        return m;
    }

    public Double getCounter(String key) {
        NamespacedKey nk = new NamespacedKey(RaftCraft.getInstance(), key);
        if (player.getPersistentDataContainer().has(nk)) {
            return player.getPersistentDataContainer().get(nk, PersistentDataType.DOUBLE);
        }
        return 0.0;
    }

    public void setCounter(String key, Double newValue) {
        NamespacedKey nk = new NamespacedKey(RaftCraft.getInstance(), key);
        player.getPersistentDataContainer().set(nk, PersistentDataType.DOUBLE, newValue);
    }

    public void addCounter(String key, Double add) {
        setCounter(key, getCounter(key) + add);
    }

    public double getSkillXP(Skills skill) {
        return getCounter(skill.getSkillKey());
    }

    public void grantSkillXP(Skills skill, double amount) {
        // Update XP
        double oldXP = getSkillXP(skill);
        double newXP = oldXP + amount;
        setCounter(skill.getSkillKey(), newXP);

        // Send action bar message
        setActionBarMessage(skill.getIcon() + "+" + amount + " (" + Numbers.getPercentageString(skill.getSkill().getProgress(newXP)) + ")");
        
        // If there was a level up, send corresponding notification
        for (int level = skill.getSkill().getLevel(oldXP) + 1; level <= skill.getSkill().getLevel(newXP); level++) {
            player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "SKILL LEVEL UP! " + ChatColor.AQUA + skill.getLongName() + " " + (level-1) + " ➡ " + level);
        }
    }

    public void applyEffect(CustomEffect effect) {
        getEffects().add(effect);
        effect.onApply(player);
    }

    public void updateEffects() {   
        List<CustomEffect> effects = getEffects();

        for (CustomEffect effect : effects) {
            if (System.currentTimeMillis() > effect.getEndTime()) {
                effect.onRemove(player);
                effects.remove(effect);
            } else {
                effect.onTick(player);
            }
        }

    }

    public List<CustomEffect> getEffects() {
        MetadataValue effectsValue = m.get("effects");

        if (effectsValue == null) {
            effectsValue = new FixedMetadataValue(RaftCraft.getInstance(), new ArrayList<CustomEffect>());
        }

        List<CustomEffect> effects = (List<CustomEffect>) effectsValue.value();

        return effects;
    }

    public void give(ItemStack... items) {
        HashMap<Integer, ItemStack> overflow = player.getInventory().addItem(items);

        for (ItemStack extra : overflow.values()) {
            Items.drop(player.getLocation(), extra);
        }
    }

    public ItemStack getNecklace() {
        return PersistentData.from(player).getItem("necklace");
    }

    public ItemStack getBelt() {
        return PersistentData.from(player).getItem("belt");
    }

    public Pair<ItemStack, ItemStack> getRings() {
        return new Pair<ItemStack, ItemStack>(
            PersistentData.from(player).getItem("ring1"),
            PersistentData.from(player).getItem("ring2")
        );
    }

    public void setNecklace(ItemStack necklace) {
        PersistentData.from(player).setItem(necklace, "necklace");
    }

    public void setBelt(ItemStack belt) {
        PersistentData.from(player).setItem(belt, "belt");
    }

    public void setFirstRing(ItemStack ring) {
        PersistentData.from(player).setItem(ring, "ring1");
    }

    public void setSecondRing(ItemStack ring) {
        PersistentData.from(player).setItem(ring, "ring2");
    }

    public List<CustomItem> getMounts() {
        ArrayList<CustomItem> mounts = new ArrayList<>();

        for (ItemStack item : PersistentData.from(player).getItems("mounts")) {
            mounts.add(ItemRegistry.get(item));
        }

        return mounts;
    }
}
