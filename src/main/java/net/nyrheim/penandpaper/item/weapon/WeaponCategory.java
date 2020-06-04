package net.nyrheim.penandpaper.item.weapon;

public enum WeaponCategory {

    SIMPLE_MELEE(true, false, true, false),
    SIMPLE_RANGED(true, false, false, true),
    MARTIAL_MELEE(false, true, true, false),
    MARTIAL_RANGED(false, true, false, true);

    private final boolean isSimple;
    private final boolean isMartial;
    private final boolean isMelee;
    private final boolean isRanged;

    WeaponCategory(boolean isSimple, boolean isMartial, boolean isMelee, boolean isRanged) {
        this.isSimple = isSimple;
        this.isMartial = isMartial;
        this.isMelee = isMelee;
        this.isRanged = isRanged;
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
