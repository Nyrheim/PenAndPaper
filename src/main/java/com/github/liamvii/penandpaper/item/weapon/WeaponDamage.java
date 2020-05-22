package com.github.liamvii.penandpaper.item.weapon;

import com.github.liamvii.penandpaper.damage.DamageType;
import com.github.liamvii.penandpaper.dice.Roll;

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
