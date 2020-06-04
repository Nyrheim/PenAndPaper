package net.nyrheim.penandpaper.item.weapon;

import net.nyrheim.penandpaper.damage.DamageType;
import net.nyrheim.penandpaper.dice.Roll;

public final class WeaponDamage {

    private final Roll roll;
    private final DamageType damageType;

    public WeaponDamage(Roll roll, DamageType damageType) {
        this.roll = roll;
        this.damageType = damageType;
    }

    public Roll getRoll() {
        return roll;
    }

    public DamageType getDamageType() {
        return damageType;
    }

}
