package com.github.liamvii.penandpaper.distance;

public final class DistanceUnit {

    public static final DistanceUnit FEET = new DistanceUnit("ft", 50);
    public static final DistanceUnit INCHES = new DistanceUnit("in", 600);

    public static final DistanceUnit METRES = new DistanceUnit("m", 15);
    public static final DistanceUnit CENTIMETRES = new DistanceUnit("cm", 1500);

    private final String name;
    private final double scaleFactor;

    private DistanceUnit(String name, int scaleFactor) {
        this.name = name;
        this.scaleFactor = scaleFactor;
    }

    public String getName() {
        return name;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }
}
