package com.jothapunkt.spigot.raftcraft.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.gui.generic.GUI;
import com.jothapunkt.spigot.raftcraft.gui.generic.GUIElement;
import com.jothapunkt.spigot.raftcraft.interfaces.LockpickingTarget;
import com.jothapunkt.spigot.raftcraft.types.Direction;

import net.minecraft.world.item.Item;

public class LockpickingGUI extends GUI {
    private LockpickingTarget target;
    private List<Direction> solution = new ArrayList<>();
    private int solved = 0;

    public LockpickingGUI(LockpickingTarget target) {
        super(new ItemStack(target.getBackgroundItem()));
        this.target = target;

        // Generate solution
        Random random = new Random();
        int numLocks = 2 + target.getDifficulty().getValue(); // 2-7 locks
        for (int i = 0; i < numLocks; i++) {
            solution.add(random.nextBoolean() ? Direction.UP : Direction.DOWN);
        }

        updateInventory();
    }

    @Override
    protected void refresh() {
        slots.clear();

        slot(9, target.getKeyItem(), new GUIElement());
        slot(17, target.getLootItem(), new GUIElement((InventoryClickEvent event) -> attemptOpen(event)));

        int numLocks = 2 + target.getDifficulty().getValue(); // 2-7 locks

        for (int lock = 1; lock <= numLocks; lock++) {
            // Upper slot
            if (solved >= lock && solution.get(lock - 1) == Direction.UP) {
                slot(lock, target.getLockItem(), new GUIElement());
            } else if (solved == lock - 1) {
                slot(lock, target.getCylinderItem(), new GUIElement((InventoryClickEvent event) -> attemptSolve(Direction.UP)));
            } else {
                slot(lock, target.getCylinderItem(), new GUIElement());
            }

            // Lower slot
            if (solved >= lock && solution.get(lock - 1) == Direction.DOWN) {
                slot(lock + 18, target.getLockItem(), new GUIElement());
            } else if (solved == lock - 1) {
                slot(lock + 18, target.getCylinderItem(), new GUIElement((InventoryClickEvent event) -> attemptSolve(Direction.DOWN)));
            } else {
                slot(lock + 18, target.getCylinderItem(), new GUIElement());
            }

            // Middle slot
            if (solved >= lock) {
                inventory.clear(lock + 9);
            } else {
                slot(lock + 9, target.getLockItem(), new GUIElement());
            }
        }

        // Middle slots after the locks should be empty
        for (int empty = numLocks + 10; empty < 17; empty++) {
            inventory.clear(empty);
        } 
    }

    public void attemptSolve(Direction direction) {
        if (direction == solution.get(solved)) {
            solved++;
        } else {
            solved = 0;
        }
        updateInventory();
    }

    public void attemptOpen(InventoryClickEvent event) {
        if (solved == solution.size()) {
            if (event.getWhoClicked() instanceof Player player) {
                target.getLootTable().roll(player, 3).grantTo(player);
                player.closeInventory();
            }
        }
    }

    @Override
    protected Inventory setupInventory() {
        Inventory inventory = Bukkit.createInventory(this, InventoryType.CHEST, "Lockpicking");
        return inventory;
    }
}
