package com.github.liamvii.penandpaper.distance;

public final class DistanceUnit {

    public static final DistanceUnit FT = new DistanceUnit("ft");

    private final String name;

    private DistanceUnit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
