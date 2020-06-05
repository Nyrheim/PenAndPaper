package net.nyrheim.penandpaper.item.weapon;

public enum WeaponCategory {

    SIMPLE_MELEE("Simple melee", true, false, true, false),
    SIMPLE_RANGED("Simple ranged", true, false, false, true),
    MARTIAL_MELEE("Martial melee", false, true, true, false),
    MARTIAL_RANGED("Martial ranged", false, true, false, true);

    private final String name;
    private final boolean isSimple;
    private final boolean isMartial;
    private final boolean isMelee;
    private final boolean isRanged;

    WeaponCategory(String name, boolean isSimple, boolean isMartial, boolean isMelee, boolean isRanged) {
        this.name = name;
        this.isSimple = isSimple;
        this.isMartial = isMartial;
        this.isMelee = isMelee;
        this.isRanged = isRanged;
    }

    public String getName() {
        return name;
    }

    public boolean isSimple() {
        return isSimple;
    }

    public boolean isMartial() {
        return isMartial;
    }

    public boolean isMelee() {
        return isMelee;
    }

    public boolean isRanged() {
        return isRanged;
    }

}
