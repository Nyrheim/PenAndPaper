package net.nyrheim.penandpaper.weight;

public final class WeightUnit {

    public static final WeightUnit LB = new WeightUnit("lb", 1);
    public static final WeightUnit OZ = new WeightUnit("oz", 16);

    private final String name;
    private final double scaleFactor;

    private WeightUnit(String name, int scaleFactor) {
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
