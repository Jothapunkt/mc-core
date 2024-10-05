package com.jothapunkt.spigot.raftcraft.skills.trees;

import java.util.ArrayList;
import java.util.List;

import com.comphenix.protocol.wrappers.Pair;

public class PerkTree {
    String name;
    List<Pair<Integer, Perk>> nodes = new ArrayList<>();
    
    public void node(Integer slot, Perk skillTreeNode) {
        skillTreeNode.setTree(name);
        nodes.add(new Pair<Integer, Perk>(slot, skillTreeNode));
    }

    public String getName() {
        return name;
    }

    public List<Pair<Integer, Perk>> getNodes() {
        return nodes;
    }

    public void setNodes(List<Pair<Integer, Perk>> nodes) {
        this.nodes = nodes;
    }
}
