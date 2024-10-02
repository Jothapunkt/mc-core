package com.jothapunkt.spigot.raftcraft.items.generic;

import net.kyori.adventure.util.TriState;

public class EquipmentItem extends CustomItem {
    protected int level;

    public EquipmentItem() {
        super();
        stackable = TriState.FALSE;
    }
}
