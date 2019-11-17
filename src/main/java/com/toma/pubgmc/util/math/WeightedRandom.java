package com.toma.pubgmc.util.math;

import com.toma.pubgmc.Pubgmc;

import java.util.Collection;
import java.util.List;

public final class WeightedRandom {

    public static int getTotalWeight(Collection<? extends IWeight> collection) {
        int total = 0;
        for (IWeight weight : collection) {
            total += weight.getWeight();
        }
        return total;
    }

    public static <T extends IWeight> T getRandom(Collection<T> collection) {
        return getRandom(collection, getTotalWeight(collection));
    }

    public static <T extends IWeight> T getRandom(Collection<T> collection, int totalWeight) {
        if(totalWeight <= 0) {
            throw new IllegalArgumentException("Total weight must be > 0!");
        }
        int weight = Pubgmc.rng().nextInt(totalWeight);
        for(T t : collection) {
            weight -= t.getWeight();
            if(weight < 0.0D) {
                return t;
            }
        }
        return null;
    }

    public static <T extends IWeight> T getRandom(List<T> collection, int totalWeight, double modifier) {
        if(totalWeight <= 0) {
            throw new IllegalArgumentException("Total weight must be > 0!");
        }
        double weight = Pubgmc.rng().nextInt(totalWeight);
        for(int i = collection.size() - 1; i >= 0; i--) {
            T t = collection.get(i);
            weight -= t.getWeight() * modifier;
            if(weight < 0.0D) {
                return t;
            }
        }
        return null;
    }
}
