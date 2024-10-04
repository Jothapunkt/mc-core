package com.jothapunkt.spigot.raftcraft.skills.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.bukkit.Material;

public class SkillTreeNode {
    protected String name;
    protected Function<Integer, List<String>> description;
    protected List<Integer> levelCosts = List.of(
        1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 1
    );
    protected List<SkillTreeNode> prerequisites = new ArrayList<>();
    protected Material baseMaterial = Material.BOOK;
    protected Material unlockedMaterial = Material.ENCHANTED_BOOK;

    public SkillTreeNode(String name, Function<Integer, List<String>> description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public SkillTreeNode setName(String name) {
        this.name = name;
        return this;
    }

    public Function<Integer, List<String>> getDescription() {
        return description;
    }

    public SkillTreeNode setDescription(Function<Integer, List<String>> description) {
        this.description = description;
        return this;
    }

    public List<SkillTreeNode> getPrerequisites() {
        return prerequisites;
    }

    public SkillTreeNode setPrerequisites(List<SkillTreeNode> prerequisites) {
        this.prerequisites = prerequisites;
        return this;
    }

    public Material getBaseMaterial() {
        return baseMaterial;
    }

    public SkillTreeNode setBaseMaterial(Material baseMaterial) {
        this.baseMaterial = baseMaterial;
        return this;
    }

    public Material getUnlockedMaterial() {
        return unlockedMaterial;
    }

    public SkillTreeNode setUnlockedMaterial(Material unlockedMateria) {
        this.unlockedMaterial = unlockedMateria;
        return this;
    }

    public List<Integer> getLevelCosts() {
        return levelCosts;
    }

    public SkillTreeNode setLevelCosts(List<Integer> levelCosts) {
        this.levelCosts = levelCosts;
        return this;
    }
}
