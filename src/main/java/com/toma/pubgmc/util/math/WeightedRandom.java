package com.toma.pubgmc.util.math;

import com.toma.pubgmc.Pubgmc;

import java.util.Collection;

public final class WeightedRandom {

    public static int getTotalWeight(Collection<? extends IWeight> collection) {
        int total = 0;
        for (IWeight weight : collection) {
            total += weight.getWeight();
        }
        return total;
    }

    public static <T extends IWeight> T getRandom(Collection<T> collection) {
        int totalWeight = getTotalWeight(collection);
        if(totalWeight <= 0) {
            throw new IllegalArgumentException("Total weight must be > 0!");
        }
        int weight = Pubgmc.rng().nextInt(totalWeight);
        for(T t : collection) {
            weight -= t.getWeight();
            if(weight < 0) {
                return t;
            }
        }
        return null;
    }
}
