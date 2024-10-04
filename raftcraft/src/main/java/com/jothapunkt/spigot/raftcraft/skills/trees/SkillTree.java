package com.jothapunkt.spigot.raftcraft.skills.trees;

import java.util.ArrayList;
import java.util.List;

import com.comphenix.protocol.wrappers.Pair;

public class SkillTree {
    String name;
    List<Pair<Integer, SkillTreeNode>> nodes = new ArrayList<>();
    
    public void node(Integer slot, SkillTreeNode skillTreeNode) {
        nodes.add(new Pair<Integer, SkillTreeNode>(slot, skillTreeNode));
    }

    public String getName() {
        return name;
    }

    public List<Pair<Integer, SkillTreeNode>> getNodes() {
        return nodes;
    }

    public void setNodes(List<Pair<Integer, SkillTreeNode>> nodes) {
        this.nodes = nodes;
    }
}
