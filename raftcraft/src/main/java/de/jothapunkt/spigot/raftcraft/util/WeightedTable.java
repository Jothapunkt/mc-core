package com.jothapunkt.spigot.raftcraft.util;

import java.util.Random;
import java.util.List;
import com.jothapunkt.spigot.raftcraft.util.WeightedOption;

public class WeightedTable<T> {
    protected List<WeightedOption<T>> options;

    public WeightedTable() {
        options = List.of();
    }

    public void setOptions(List<WeightedOption<T>> newOptions) {
        this.options = newOptions;
    }

    public List<WeightedOption<T>> getOptions() {
        return this.options;
    }

    public T choose() {
        int sumOfWeights = 0;

        for (WeightedOption<T> option : options) {
            sumOfWeights += option.getWeight();
        }

        int pickedWeight = new Random().nextInt(sumOfWeights);

        sumOfWeights = 0;

        for (WeightedOption<T> option : options) {
            sumOfWeights += option.getWeight();
            if (sumOfWeights >= pickedWeight) {
                return option.getOption();
            }
        }

        return options.get(options.size() - 1).getOption();
    }
}