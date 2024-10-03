package com.jothapunkt.spigot.raftcraft.items.mounts;

import com.jothapunkt.spigot.raftcraft.items.generic.MountItem;
import com.jothapunkt.spigot.raftcraft.mounts.PhantomMount;
import com.jothapunkt.spigot.raftcraft.util.Items;

public class PhantomItem extends MountItem {
    public PhantomItem() {
        super(new PhantomMount());
        name = "Phantom";
        baseItem = Items.head("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjRhZDYzYjY5N2E0YzQ3OTBkMDBjNDM1NDYwYmFmNDkxOTE2NTdlNjFiZWU2MTFmNzU4OGRiY2RhNzE5OGJiZCJ9fX0=");
    }
}
