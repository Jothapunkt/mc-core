package com.jothapunkt.spigot.raftcraft.items.generic;

import org.apache.commons.lang3.NotImplementedException;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.blocks.generic.CustomBlock;

public class CustomBlockItem<T extends PersistentDataHolder> extends CustomItem {
    public CustomBlock<T> getCustomBlock() {
        throw new NotImplementedException();
    }

    @Override
    public ItemStack print(int amount) {
        ItemStack item = super.print(amount);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(
            new NamespacedKey(RaftCraft.getInstance(), "custom_block"),
            PersistentDataType.STRING,
            getCustomBlock().getKey()
        );
        item.setItemMeta(meta);
        return item;
    }
}
