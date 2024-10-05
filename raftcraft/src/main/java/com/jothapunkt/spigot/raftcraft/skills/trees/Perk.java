package com.jothapunkt.spigot.raftcraft.skills.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.util.PersistentData;

import net.md_5.bungee.api.ChatColor;

public class Perk {
    protected String name;
    protected String tree;
    protected Function<Integer, List<String>> description;
    protected List<Integer> levelCosts = List.of(
        1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 1
    );
    protected List<Perk> prerequisites = new ArrayList<>();
    protected Material baseMaterial = Material.BOOK;
    protected Material unlockedMaterial = Material.ENCHANTED_BOOK;

    public Perk(String name, Function<Integer, List<String>> description) {
        this.name = name;
        this.description = description;
    }

    public String getKey() {
        return name.toLowerCase().replace(" ", "_");
    }

    public int getPlayerLevel(Player player) {
        Integer level = PersistentData.from(player).get(PersistentDataType.INTEGER, "trees", tree, getKey());
        if (level == null) {
            return 0;
        }

        return level;
    }

    public ItemStack getItem(Player player) {
        int level = getPlayerLevel(player);
        ItemStack item = new ItemStack(level > 0 ? unlockedMaterial : baseMaterial);

        //item.setAmount(Math.min(1, level));

        ItemMeta meta = item.getItemMeta();
        meta.setItemName(ChatColor.GOLD + name);
        item.setItemMeta(meta);
        item.setLore(description.apply(getPlayerLevel(player)));

        return item;
    }

    public String getName() {
        return name;
    }

    public Perk setName(String name) {
        this.name = name;
        return this;
    }

    public Function<Integer, List<String>> getDescription() {
        return description;
    }

    public Perk setDescription(Function<Integer, List<String>> description) {
        this.description = description;
        return this;
    }

    public List<Perk> getPrerequisites() {
        return prerequisites;
    }

    public Perk setPrerequisites(List<Perk> prerequisites) {
        this.prerequisites = prerequisites;
        return this;
    }

    public Material getBaseMaterial() {
        return baseMaterial;
    }

    public Perk setBaseMaterial(Material baseMaterial) {
        this.baseMaterial = baseMaterial;
        return this;
    }

    public Material getUnlockedMaterial() {
        return unlockedMaterial;
    }

    public Perk setUnlockedMaterial(Material unlockedMateria) {
        this.unlockedMaterial = unlockedMateria;
        return this;
    }

    public List<Integer> getLevelCosts() {
        return levelCosts;
    }

    public Perk setLevelCosts(List<Integer> levelCosts) {
        this.levelCosts = levelCosts;
        return this;
    }

    public String getTree() {
        return tree;
    }

    public void setTree(String tree) {
        this.tree = tree;
    }
}
