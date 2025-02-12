package net.nyrheim.penandpaper.item.armor;

import net.nyrheim.penandpaper.ability.Ability;
import net.nyrheim.penandpaper.ability.StrengthRequirement;
import net.nyrheim.penandpaper.armorclass.ArmorClassCalculation;
import net.nyrheim.penandpaper.item.ItemStackInitializer;
import net.nyrheim.penandpaper.item.ItemType;
import net.nyrheim.penandpaper.money.Money;
import net.nyrheim.penandpaper.weight.Weight;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.nyrheim.penandpaper.item.armor.ArmorCategory.*;
import static net.nyrheim.penandpaper.money.Currency.GP;
import static net.nyrheim.penandpaper.weight.WeightUnit.LB;
import static org.bukkit.ChatColor.GRAY;
import static org.bukkit.Material.*;

public enum ArmorType implements ItemType {

    PADDED_ARMOR(
            "Padded Armor",
            LIGHT_ARMOR,
            new Money(5, GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(5),
                    new ArmorClassCalculation.AbilityModifierArmorClassComponent(Ability.DEXTERITY)
            ),
            null,
            true,
            new Weight(8, LB),
            false,
            LEATHER_CHESTPLATE
    ),
    LEATHER_ARMOR(
            "Leather Armor",
            LIGHT_ARMOR,
            new Money(10, GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(11),
                    new ArmorClassCalculation.AbilityModifierArmorClassComponent(Ability.DEXTERITY)
            ),
            null,
            false,
            new Weight(10, LB),
            false,
            LEATHER_CHESTPLATE
    ),
    STUDDED_LEATHER_ARMOR(
            "Studded Leather Armor",
            LIGHT_ARMOR,
            new Money(45, GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(12),
                    new ArmorClassCalculation.AbilityModifierArmorClassComponent(Ability.DEXTERITY)
            ),
            null,
            false,
            new Weight(13, LB),
            false,
            LEATHER_CHESTPLATE
    ),
    HIDE_ARMOR(
            "Hide Armor",
            MEDIUM_ARMOR,
            new Money(10, GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(12),
                    new ArmorClassCalculation.CappedAbilityModifierArmorClassComponent(Ability.DEXTERITY, 2)
            ),
            null,
            false,
            new Weight(12, LB),
            false,
            LEATHER_CHESTPLATE
    ),
    CHAIN_SHIRT(
            "Chain Shirt",
            MEDIUM_ARMOR,
            new Money(50, GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(13),
                    new ArmorClassCalculation.CappedAbilityModifierArmorClassComponent(Ability.DEXTERITY, 2)
            ),
            null,
            false,
            new Weight(20, LB),
            true,
            CHAINMAIL_CHESTPLATE
    ),
    SCALE_MAIL(
            "Scale Mail",
            MEDIUM_ARMOR,
            new Money(50, GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(14),
                    new ArmorClassCalculation.CappedAbilityModifierArmorClassComponent(Ability.DEXTERITY, 2)
            ),
            null,
            true,
            new Weight(45, LB),
            true,
            IRON_CHESTPLATE
    ),
    BREASTPLATE(
            "Breastplate",
            MEDIUM_ARMOR,
            new Money(400, GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(14),
                    new ArmorClassCalculation.CappedAbilityModifierArmorClassComponent(Ability.DEXTERITY, 2)
            ),
            null,
            false,
            new Weight(20, LB),
            true,
            IRON_CHESTPLATE
    ),
    HALF_PLATE(
            "Half Plate",
            MEDIUM_ARMOR,
            new Money(750, GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(15),
                    new ArmorClassCalculation.CappedAbilityModifierArmorClassComponent(Ability.DEXTERITY, 2)
            ),
            null,
            true,
            new Weight(40, LB),
            true,
            IRON_CHESTPLATE
    ),
    RING_MAIL(
            "Ring Mail",
            HEAVY_ARMOR,
            new Money(30, GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(14)
            ),
            null,
            true,
            new Weight(40, LB),
            false,
            CHAINMAIL_CHESTPLATE
    ),
    CHAIN_MAIL(
            "Chain Mail",
            HEAVY_ARMOR,
            new Money(75, GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(16)
            ),
            new StrengthRequirement(13),
            true,
            new Weight(55, LB),
            true,
            CHAINMAIL_CHESTPLATE
    ),
    SPLINT_MAIL(
            "Splint Mail",
            HEAVY_ARMOR,
            new Money(200, GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(17)
            ),
            new StrengthRequirement(15),
            true,
            new Weight(60, LB),
            true,
            CHAINMAIL_CHESTPLATE
    ),
    PLATE_MAIL(
            "Plate Mail",
            HEAVY_ARMOR,
            new Money(1500, GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(18)
            ),
            new StrengthRequirement(15),
            true,
            new Weight(65, LB),
            true,
            IRON_CHESTPLATE
    ),
    SHIELD(
            "Shield",
            ArmorCategory.SHIELD,
            new Money(10, GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(1)
            ),
            null,
            false,
            new Weight(6, LB),
            false,
            Material.SHIELD
    ),
    REINFORCED_SHIELD(
            "Reinforced shield",
            ArmorCategory.SHIELD,
            new Money(10, GP),
            new ArmorClassCalculation(
                    new ArmorClassCalculation.BaseArmorClassComponent(2)
            ),
            null,
            false,
            new Weight(6, LB),
            false,
            Material.SHIELD
    );

    private final String name;
    private final ArmorCategory category;
    private final Money cost;
    private final ArmorClassCalculation armorClass;
    private final StrengthRequirement strengthRequirement;
    private final boolean disadvantageToStealthChecks;
    private final Weight weight;
    private final boolean isMetal;
    private final ItemStackInitializer itemStackInitializer;

    ArmorType(
            String name,
            ArmorCategory category,
            Money cost,
            ArmorClassCalculation armorClass,
            StrengthRequirement strengthRequirement,
            boolean disadvantageToStealthChecks,
            Weight weight,
            boolean isMetal,
            ItemStackInitializer itemStackInitializer
    ) {
        this.name = name;
        this.category = category;
        this.cost = cost;
        this.armorClass = armorClass;
        this.strengthRequirement = strengthRequirement;
        this.disadvantageToStealthChecks = disadvantageToStealthChecks;
        this.weight = weight;
        this.isMetal = isMetal;
        this.itemStackInitializer = itemStackInitializer;
    }

    ArmorType(String name,
              ArmorCategory category,
              Money cost,
              ArmorClassCalculation armorClass,
              StrengthRequirement strengthRequirement,
              boolean disadvantageToStealthChecks,
              Weight weight,
              boolean isMetal,
              Material minecraftType) {
        this(
                name,
                category,
                cost,
                armorClass,
                strengthRequirement,
                disadvantageToStealthChecks,
                weight,
                isMetal,
                (amount) -> new ItemStack(minecraftType, amount)
        );
    }

    @Override
    public String getName() {
        return name;
    }

    public ArmorCategory getCategory() {
        return category;
    }

    @Override
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

    @Override
    public Weight getWeight() {
        return weight;
    }

    public boolean isMetal() {
        return isMetal;
    }

    @Override
    public ItemStack createItemStack(int amount) {
        return itemStackInitializer.invoke(amount);
    }

    @Override
    public List<String> createLore() {
        List<String> lore = new ArrayList<>();
        lore.add(GRAY + "Category: " + getCategory().getName() + ", " + "Metallic: " + (isMetal() ? "Yes" : "No"));
        lore.add(GRAY + "Weight: " + getWeight().toString());
        if (getStrengthRequirement() != null) {
            lore.add(GRAY + "Strength requirement: " + getStrengthRequirement().toString());
        }
        return lore;
    }

    public static ArmorType getByName(String name) {
        return Arrays.stream(ArmorType.values())
                .filter(type -> type.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}
