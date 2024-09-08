package com.jothapunkt.spigot.raftcraft.items.blocks;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Barrel;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.ApiStatus.OverrideOnly;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.blocks.CustomSpawnerBlock;
import com.jothapunkt.spigot.raftcraft.blocks.PreservingJarBlock;
import com.jothapunkt.spigot.raftcraft.blocks.generic.CustomBlock;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomBlockItem;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.mobs.generic.CustomMob;
import com.jothapunkt.spigot.raftcraft.types.Rarity;

public class CustomSpawner extends CustomBlockItem<CreatureSpawner> {
    protected CustomMob spawn;
    public CustomSpawner(CustomMob spawn) {
        name = "Spawner for " + spawn.getName();
        baseItem = new ItemStack(Material.SPAWNER);
        rarity = Rarity.LEGENDARY;
        this.spawn = spawn;
    }

    @Override
    public CustomBlock<CreatureSpawner> getCustomBlock() {
        return new CustomSpawnerBlock();
    }

    @Override
    public ItemStack print(int amount) {
        ItemStack item = super.print(amount);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(
            new NamespacedKey(RaftCraft.getInstance(), "mob_identifier"),
            PersistentDataType.STRING,
            spawn.getKey()
        );
        item.setItemMeta(meta);
        return item;
    }
}
