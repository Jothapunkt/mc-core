package com.jothapunkt.spigot.raftcraft.util;

import net.md_5.bungee.api.ChatColor;
import java.awt.Color;
import java.lang.Math;
import java.util.List;


public class Colors {
    public static String gradientText(String text, Color color1, Color color2) {
        double delta_r = (color2.getRed() - color1.getRed()) / text.length();
        double delta_g = (color2.getGreen() - color1.getGreen()) / text.length();
        double delta_b = (color2.getBlue() - color1.getBlue()) / text.length();

        String gradientString = "";

        for (int i = 0; i < text.length(); i++) {
            int r = (int) Math.floor(color1.getRed() + (delta_r * i));
            int g = (int) Math.floor(color1.getGreen() + (delta_g * i));
            int b = (int) Math.floor(color1.getBlue() + (delta_b * i));

            gradientString += ChatColor.of(new Color(r, g, b)) + "" + text.charAt(i);
        }

        return gradientString;
    }

    public static String colorLoopText(String text, List<Color> colors) {
        String colorString = "";

        for (int i = 0; i < text.length(); i++) {
            Color color = colors.get(i % colors.size());
            colorString += ChatColor.of(color) + "" + text.charAt(i);
        }

        return colorString;
    }
}