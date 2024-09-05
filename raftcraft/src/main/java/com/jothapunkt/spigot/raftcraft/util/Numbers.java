package com.jothapunkt.spigot.raftcraft.util;

public class Numbers {
    public static String getDisplayString(double number) {
        return getDisplayString((long) number);
    }

    public static String getDisplayString(long number) {
        if (number > 1000000000) {
            return Double.toString((double) Math.round(number / 10000000) / 100) + "B";
        }

        if (number > 1000000) {
            return Double.toString((double) Math.round(number / 10000) / 100) + "m";
        }

        if (number > 1000) {
            return Double.toString((double) Math.round(number / 10) / 100) + "k";
        }

        return Long.toString(number);
    }

    public static String getPercentageString(double number) {
        return Double.toString((double) Math.round(number * 10000) / 100) + "%"; 
    }
}
