package com.github.liamvii.penandpaper.weight;

public final class WeightUnit {

    public static final WeightUnit LB = new WeightUnit("lb");

    private final String name;

    private WeightUnit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
