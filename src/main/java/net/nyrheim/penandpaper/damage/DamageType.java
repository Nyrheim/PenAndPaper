package net.nyrheim.penandpaper.damage;

public enum DamageType {

    ACID("Acid"),
    BLUDGEONING("Bludgeoning"),
    COLD("Cold"),
    FIRE("Fire"),
    FORCE("Force"),
    LIGHTNING("Lightning"),
    NECROTIC("Necrotic"),
    PIERCING("Piercing"),
    POISON("Poison"),
    PSYCHIC("Psychic"),
    RADIANT("Radiant"),
    SLASHING("Slashing"),
    THUNDER("Thunder");

    private final String name;

    DamageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
