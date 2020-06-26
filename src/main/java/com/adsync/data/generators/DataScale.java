package com.adsync.data.generators;

import java.util.HashMap;
import java.util.Map;

public class DataScale {

    private int maxEntityA;
    private int maxEntityB;
    private Map<Integer, Integer> entityDistribution = new HashMap<>();

    public int getMaxEntityA() {
        return maxEntityA;
    }

    public int getMaxEntityB() {
        return maxEntityB;
    }

    public Map<Integer, Integer> getEntityDistribution() {
        return entityDistribution;
    }

    public void setMaxEntityA(int maxEntityA) {
        this.maxEntityA = maxEntityA;
    }

    public void setMaxEntityB(int maxEntityB) {
        this.maxEntityB = maxEntityB;
    }

    public void setEntityDistribution(Map<Integer, Integer> entityDistribution) {
        this.entityDistribution = entityDistribution;
    }

    @Override
    public String toString() {
        return "DataScale{" +
                "maxEntityA=" + maxEntityA +
                ", maxEntityB=" + maxEntityB +
                ", entityDistribution=" + entityDistribution +
                '}';
    }
}
