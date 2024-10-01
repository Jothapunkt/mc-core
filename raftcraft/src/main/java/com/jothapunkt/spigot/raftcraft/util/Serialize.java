package com.jothapunkt.spigot.raftcraft.util;

import java.io.IOException;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.spongepowered.configurate.yaml.internal.snakeyaml.external.biz.base64Coder.Base64Coder;

public class Serialize {
    public static String objectToBase64(Object input) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

        dataOutput.writeObject(input);

        dataOutput.close();

        return Base64Coder.encodeLines(outputStream.toByteArray());
    }

    public static String base64(ItemStack item) throws IOException {
        return objectToBase64(item);
    }
}
