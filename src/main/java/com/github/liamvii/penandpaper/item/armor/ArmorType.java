package com.github.liamvii.penandpaper.item.armor;

import com.github.liamvii.penandpaper.ability.StrengthRequirement;
import com.github.liamvii.penandpaper.armorclass.ArmorClassCalculation;
import com.github.liamvii.penandpaper.armorclass.ArmorClassCalculation.AbilityModifierArmorClassComponent;
import com.github.liamvii.penandpaper.armorclass.ArmorClassCalculation.BaseArmorClassComponent;
import com.github.liamvii.penandpaper.armorclass.ArmorClassCalculation.CappedAbilityModifierArmorClassComponent;
import com.github.liamvii.penandpaper.money.Money;
import com.github.liamvii.penandpaper.weight.Weight;

import static com.github.liamvii.penandpaper.ability.Ability.DEXTERITY;
import static com.github.liamvii.penandpaper.item.armor.ArmorCategory.*;
import static com.github.liamvii.penandpaper.money.Currency.GP;
import static com.github.liamvii.penandpaper.weight.WeightUnit.LB;

public enum ArmorType {

    PADDED(
            "Padded",
            LIGHT_ARMOR,
            new Money(5, GP),
            new ArmorClassCalculation(
                    new BaseArmorClassComponent(5),
                    new AbilityModifierArmorClassComponent(DEXTERITY)
            ),
            null,
            true,
            new Weight(8, LB),
            false
    ),
    LEATHER(
            "Leather",
            LIGHT_ARMOR,
            new Money(10, GP),
            new ArmorClassCalculation(
                    new BaseArmorClassComponent(11),
                    new AbilityModifierArmorClassComponent(DEXTERITY)
            ),
            null,
            false,
            new Weight(10, LB),
            false
    ),
    STUDDED_LEATHER(
            "Studded leather",
            LIGHT_ARMOR,
            new Money(45, GP),
            new ArmorClassCalculation(
                    new BaseArmorClassComponent(12),
                    new AbilityModifierArmorClassComponent(DEXTERITY)
            ),
            null,
            false,
            new Weight(13, LB),
            false
    ),
    HIDE(
            "Hide",
            MEDIUM_ARMOR,
            new Money(10, GP),
            new ArmorClassCalculation(
                    new BaseArmorClassComponent(12),
                    new CappedAbilityModifierArmorClassComponent(DEXTERITY, 2)
            ),
            null,
            false,
            new Weight(12, LB),
            false
    ),
    CHAIN_SHIRT(
            "Chain shirt",
            MEDIUM_ARMOR,
            new Money(50, GP),
            new ArmorClassCalculation(
                    new BaseArmorClassComponent(13),
                    new CappedAbilityModifierArmorClassComponent(DEXTERITY, 2)
            ),
            null,
            false,
            new Weight(20, LB),
            true
    ),
    SCALE_MAIL(
            "Scale mail",
            MEDIUM_ARMOR,
            new Money(50, GP),
            new ArmorClassCalculation(
                    new BaseArmorClassComponent(14),
                    new CappedAbilityModifierArmorClassComponent(DEXTERITY, 2)
            ),
            null,
            true,
            new Weight(45, LB),
            true
    ),
    BREASTPLATE(
            "Breastplate",
            MEDIUM_ARMOR,
            new Money(400, GP),
            new ArmorClassCalculation(
                    new BaseArmorClassComponent(14),
                    new CappedAbilityModifierArmorClassComponent(DEXTERITY, 2)
            ),
            null,
            false,
            new Weight(20, LB),
            true
    ),
    HALF_PLATE(
            "Half plate",
            MEDIUM_ARMOR,
            new Money(750, GP),
            new ArmorClassCalculation(
                    new BaseArmorClassComponent(15),
                    new CappedAbilityModifierArmorClassComponent(DEXTERITY, 2)
            ),
            null,
            true,
            new Weight(40, LB),
            true
    ),
    RING_MAIL(
            "Ring mail",
            HEAVY_ARMOR,
            new Money(30, GP),
            new ArmorClassCalculation(
                    new BaseArmorClassComponent(14)
            ),
            null,
            true,
            new Weight(40, LB),
            false
    ),
    CHAIN_MAIL(
            "Chain mail",
            HEAVY_ARMOR,
            new Money(75, GP),
            new ArmorClassCalculation(
                    new BaseArmorClassComponent(16)
            ),
            new StrengthRequirement(13),
            true,
            new Weight(55, LB),
            true
    ),
    SPLINT(
            "Splint",
            HEAVY_ARMOR,
            new Money(200, GP),
            new ArmorClassCalculation(
                    new BaseArmorClassComponent(17)
            ),
            new StrengthRequirement(15),
            true,
            new Weight(60, LB),
            true
    ),
    PLATE(
            "Plate",
            HEAVY_ARMOR,
            new Money(1500, GP),
            new ArmorClassCalculation(
                    new BaseArmorClassComponent(18)
            ),
            new StrengthRequirement(15),
            true,
            new Weight(65, LB),
            true
    ),
    SHIELD(
            "Shield",
            ArmorCategory.SHIELD,
            new Money(10, GP),
            new ArmorClassCalculation(
                    new BaseArmorClassComponent(2)
            ),
            null,
            false,
            new Weight(6, LB),
            false
    );

    private final String name;
    private final ArmorCategory category;
    private final Money cost;
    private final ArmorClassCalculation armorClass;
    private final StrengthRequirement strengthRequirement;
    private final boolean disadvantageToStealthChecks;
    private final Weight weight;
    private final boolean isMetal;

    ArmorType(
            String name,
            ArmorCategory category,
            Money cost,
            ArmorClassCalculation armorClass,
            StrengthRequirement strengthRequirement,
            boolean disadvantageToStealthChecks,
            Weight weight,
            boolean isMetal
    ) {
        this.name = name;
        this.category = category;
        this.cost = cost;
        this.armorClass = armorClass;
        this.strengthRequirement = strengthRequirement;
        this.disadvantageToStealthChecks = disadvantageToStealthChecks;
        this.weight = weight;
        this.isMetal = isMetal;
    }

    public String getName() {
        return name;
    }

    public ArmorCategory getCategory() {
        return category;
    }

    public Money getCost() {
        return cost;
    }

    public ArmorClassCalculation getArmorClass() {
        return armorClass;
    }

    public StrengthRequirement getStrengthRequirement() {
        return strengthRequirement;
    }

    public boolean isDisadvantageToStealthChecks() {
        return disadvantageToStealthChecks;
    }

    public Weight getWeight() {
        return weight;
    }

    public boolean isMetal() {
        return isMetal;
    }

}
