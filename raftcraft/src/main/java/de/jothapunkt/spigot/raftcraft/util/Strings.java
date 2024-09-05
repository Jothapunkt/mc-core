package com.jothapunkt.spigot.raftcraft.util;

public class Strings {
    public static String snakeToCamelCase(String input) {
        String result = "";

        for (int i = 0; i < input.split("_").length; i++) {
            String word = input.split("_")[i].toLowerCase();

            for (int charIndex = 0; charIndex < word.length(); charIndex++) {
                if (charIndex == 0) {
                    result += (word.charAt(charIndex) + "").toUpperCase();
                } else {
                    result += word.charAt(charIndex);
                }
            }

            if (i < input.split("_").length - 1) {
                result += " ";
            }
        }

        return result;
    }

    public static String pad(String input, int targetLength) {
        String padded = input;

        while (padded.length() < targetLength) {
            padded += " ";
        }

        return padded;
    }

    public static String center(String input, int targetLength) {
        String padded = input;

        for (int i = 0; i < targetLength - input.length(); i++) {
            if (i % 2 == 0) {
                padded += " ";
            } else {
                padded = " " + padded;
            }
        }

        return padded;
    }
}
