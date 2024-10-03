package com.jothapunkt.spigot.raftcraft.items.mounts;

import com.jothapunkt.spigot.raftcraft.items.generic.MountItem;
import com.jothapunkt.spigot.raftcraft.mounts.DolphinMount;
import com.jothapunkt.spigot.raftcraft.mounts.PhantomMount;
import com.jothapunkt.spigot.raftcraft.util.Items;

public class DolphinItem extends MountItem {
    public DolphinItem() {
        super(new DolphinMount());
        name = "Dolphin";
        baseItem = Items.head("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGU5Njg4Yjk1MGQ4ODBiNTViN2FhMmNmY2Q3NmU1YTBmYTk0YWFjNmQxNmY3OGU4MzNmNzQ0M2VhMjlmZWQzIn19fQ==");
    }
}
