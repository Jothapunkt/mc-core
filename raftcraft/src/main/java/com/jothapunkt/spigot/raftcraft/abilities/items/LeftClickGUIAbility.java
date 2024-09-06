package com.jothapunkt.spigot.raftcraft.abilities.items;

import java.util.function.Supplier;

import org.bukkit.entity.Player;

import com.jothapunkt.spigot.raftcraft.abilities.items.generic.LeftClickAbility;
import com.jothapunkt.spigot.raftcraft.gui.generic.GUI;

public class LeftClickGUIAbility extends LeftClickAbility {
    protected Supplier<GUI> guiSupplier;

    public LeftClickGUIAbility(Supplier<GUI> guiSupplier) {
        this.guiSupplier = guiSupplier;
    }

    @Override
    public void use(Player player) {
        guiSupplier.get().show(player);
    }
}
