package com.jothapunkt.spigot.raftcraft.skills.trees;

import java.util.ArrayList;
import java.util.List;

import com.comphenix.protocol.wrappers.Pair;

public class SkillTree {
    List<Pair<Integer, SkillTreeNode>> nodes = new ArrayList<>();
    
    public void node(Integer slot, SkillTreeNode skillTreeNode) {
        nodes.add(new Pair<Integer, SkillTreeNode>(slot, skillTreeNode));
    }
}
