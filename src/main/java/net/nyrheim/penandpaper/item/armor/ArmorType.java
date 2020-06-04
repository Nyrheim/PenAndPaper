package net.nyrheim.penandpaper.item.armor;

import net.nyrheim.penandpaper.ability.StrengthRequirement;
import net.nyrheim.penandpaper.armorclass.ArmorClassCalculation;
import net.nyrheim.penandpaper.money.Money;
import net.nyrheim.penandpaper.weight.Weight;
import net.nyrheim.penandpaper.ability.Ability;
import net.nyrheim.penandpaper.money.Currency;

import static net.nyrheim.penandpaper.item.armor.ArmorCategory.*;
import static net.nyrheim.penandpaper.weight.WeightUnit.LB;

public enum ArmorType {

    PADDED(
            "Padded",
            LIGHT_ARMOR,
            new Money(5, Currency.GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(5),
                    new ArmorClassCalculation.AbilityModifierArmorClassComponent(Ability.DEXTERITY)
            ),
            null,
            true,
            new Weight(8, LB),
            false
    ),
    LEATHER(
            "Leather",
            LIGHT_ARMOR,
            new Money(10, Currency.GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(11),
                    new ArmorClassCalculation.AbilityModifierArmorClassComponent(Ability.DEXTERITY)
            ),
            null,
            false,
            new Weight(10, LB),
            false
    ),
    STUDDED_LEATHER(
            "Studded leather",
            LIGHT_ARMOR,
            new Money(45, Currency.GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(12),
                    new ArmorClassCalculation.AbilityModifierArmorClassComponent(Ability.DEXTERITY)
            ),
            null,
            false,
            new Weight(13, LB),
            false
    ),
    HIDE(
            "Hide",
            MEDIUM_ARMOR,
            new Money(10, Currency.GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(12),
                    new ArmorClassCalculation.CappedAbilityModifierArmorClassComponent(Ability.DEXTERITY, 2)
            ),
            null,
            false,
            new Weight(12, LB),
            false
    ),
    CHAIN_SHIRT(
            "Chain shirt",
            MEDIUM_ARMOR,
            new Money(50, Currency.GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(13),
                    new ArmorClassCalculation.CappedAbilityModifierArmorClassComponent(Ability.DEXTERITY, 2)
            ),
            null,
            false,
            new Weight(20, LB),
            true
    ),
    SCALE_MAIL(
            "Scale mail",
            MEDIUM_ARMOR,
            new Money(50, Currency.GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(14),
                    new ArmorClassCalculation.CappedAbilityModifierArmorClassComponent(Ability.DEXTERITY, 2)
            ),
            null,
            true,
            new Weight(45, LB),
            true
    ),
    BREASTPLATE(
            "Breastplate",
            MEDIUM_ARMOR,
            new Money(400, Currency.GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(14),
                    new ArmorClassCalculation.CappedAbilityModifierArmorClassComponent(Ability.DEXTERITY, 2)
            ),
            null,
            false,
            new Weight(20, LB),
            true
    ),
    HALF_PLATE(
            "Half plate",
            MEDIUM_ARMOR,
            new Money(750, Currency.GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(15),
                    new ArmorClassCalculation.CappedAbilityModifierArmorClassComponent(Ability.DEXTERITY, 2)
            ),
            null,
            true,
            new Weight(40, LB),
            true
    ),
    RING_MAIL(
            "Ring mail",
            HEAVY_ARMOR,
            new Money(30, Currency.GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(14)
            ),
            null,
            true,
            new Weight(40, LB),
            false
    ),
    CHAIN_MAIL(
            "Chain mail",
            HEAVY_ARMOR,
            new Money(75, Currency.GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(16)
            ),
            new StrengthRequirement(13),
            true,
            new Weight(55, LB),
            true
    ),
    SPLINT(
            "Splint",
            HEAVY_ARMOR,
            new Money(200, Currency.GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(17)
            ),
            new StrengthRequirement(15),
            true,
            new Weight(60, LB),
            true
    ),
    PLATE(
            "Plate",
            HEAVY_ARMOR,
            new Money(1500, Currency.GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(18)
            ),
            new StrengthRequirement(15),
            true,
            new Weight(65, LB),
            true
    ),
    SHIELD(
            "Shield",
            ArmorCategory.SHIELD,
            new Money(10, Currency.GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(2)
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
