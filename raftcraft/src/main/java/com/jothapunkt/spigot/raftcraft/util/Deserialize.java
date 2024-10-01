package com.jothapunkt.spigot.raftcraft.util;

import java.io.IOException;

import java.io.ByteArrayInputStream;
import org.apache.commons.io.IOExceptionList;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.spongepowered.configurate.yaml.internal.snakeyaml.external.biz.base64Coder.Base64Coder;

public class Deserialize {
    public static ItemStack item(String data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
        BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

        return (ItemStack) dataInput.readObject();
    }
}
