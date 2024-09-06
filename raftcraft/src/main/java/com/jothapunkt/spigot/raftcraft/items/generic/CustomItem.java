package com.jothapunkt.spigot.raftcraft.items.generic;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.abilities.items.generic.ItemAbility;
import com.jothapunkt.spigot.raftcraft.items.CustomItemIdentifier;
import com.jothapunkt.spigot.raftcraft.types.Rarity;
import com.jothapunkt.spigot.raftcraft.types.Stat;

import net.md_5.bungee.api.ChatColor;

public class CustomItem {
    protected Rarity rarity = Rarity.COMMON;
    protected ItemStack baseItem = new ItemStack(Material.IRON_INGOT);
    protected String name = "Test Item";
    protected String type = "Item";
    protected int damage = 1;
    protected List<String> description = List.of();
    protected boolean soulbound = false;
    protected boolean enchantGlint = false;
    protected HashMap<Stat, Double> stats = new HashMap<>();
    protected List<ItemAbility> abilities = new ArrayList<>();

    public HashMap<Stat, Double> getStats() {
        return stats;
    }

    public void stat(Stat stat, Double value) {
        stats.put(stat, value);
    }

    public void ability(ItemAbility ability) {
        abilities.add(ability);
    }

    public <T extends ItemAbility> T getAbility(Class<T> abilityType) {
        for (ItemAbility ability : abilities) {
            if (abilityType.isInstance(ability)) {
                return (T) ability;
            }
        }

        return null;
    }

    public String getIdentifer() {
        return CustomItemIdentifier.getIdentifier(this) != null ? CustomItemIdentifier.getIdentifier(this).name() : "UNMAPPED";
    }

    public ItemStack reprint(ItemStack item) {
        ItemStack newItem = print();

        item.getPersistentDataContainer().copyTo(newItem.getItemMeta().getPersistentDataContainer(), false);

        return newItem;
    }

    public int getDamage() {
        return damage;
    }

    public ItemStack print() {
        return print(1);
    }

    public ItemStack print(int amount) {
        ItemStack result = baseItem.clone();
        result.setAmount(amount);
        ItemMeta meta = result.getItemMeta();

        String identifier = getIdentifer();
        // Set item id identifier
        meta.getPersistentDataContainer()
            .set(
                new NamespacedKey(RaftCraft.getInstance(), "item_identifier"),
                PersistentDataType.STRING,
                identifier
            );

        ArrayList<String> lore = new ArrayList<>();

        // Stats
        for (Entry<Stat, Double> stat : stats.entrySet()) {
            lore.add(stat.getKey().getLongName() + " " + ChatColor.WHITE + stat.getValue());
        }
        
        if (damage > 1) {
            lore.add(ChatColor.BLUE + "Damage: +" + damage);
        }

        lore.add("");

        // Abilities
        for (ItemAbility ability : abilities) {
            for (String line : ability.getDescription()) {
                lore.add(line);
            }
            lore.add("");
        }

        // Description
        for (String line : description) {
            lore.add(line);
        }

        lore.add("");
        lore.add(rarity.getRarityColor() + "" + ChatColor.BOLD + rarity.name() + " " + type.toUpperCase());

        if (name != null) {
            meta.setDisplayName(rarity.getRarityColor() + name); 
        }

        meta.setEnchantmentGlintOverride(enchantGlint);
         
        meta.setLore(lore);
        result.setItemMeta(meta);

        return result;
    }
}
