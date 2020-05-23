package com.github.liamvii.penandpaper.clazz;

import com.github.liamvii.penandpaper.ability.Ability;
import com.github.liamvii.penandpaper.dice.Roll;
import com.github.liamvii.penandpaper.dice.Roll.Die;
import com.github.liamvii.penandpaper.item.armor.ArmorCategory;
import com.github.liamvii.penandpaper.item.armor.ArmorType;
import com.github.liamvii.penandpaper.item.weapon.WeaponType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.liamvii.penandpaper.ability.Ability.*;
import static com.github.liamvii.penandpaper.item.armor.ArmorCategory.*;
import static com.github.liamvii.penandpaper.item.weapon.WeaponType.*;

public final class DnDClass {

    public static final DnDClass BARBARIAN = new DnDClass(
            "Barbarian",
            "A fierce warrior of primitive background who can enter a battle rage",
            new Roll(new Die(1, 12)),
            STRENGTH,
            Arrays.asList(
                    STRENGTH,
                    CONSTITUTION
            ),
            Arrays.stream(ArmorType.values())
                    .filter(armorType ->
                            armorType.getCategory() == LIGHT_ARMOR
                                || armorType.getCategory() == MEDIUM_ARMOR
                                || armorType.getCategory() == SHIELD
                    )
                    .collect(Collectors.toList()),
            Arrays.asList(WeaponType.values()),
            12,
            7
    );

    public static final DnDClass BARD = new DnDClass(
            "Bard",
            "An inspiring magician whose power echoes the music of creation",
            new Roll(new Die(1, 8)),
            CHARISMA,
            Arrays.asList(
                    DEXTERITY,
                    CHARISMA
            ),
            Arrays.stream(ArmorType.values())
                    .filter(armorType -> armorType.getCategory() == LIGHT_ARMOR)
                    .collect(Collectors.toList()),
            Arrays.stream(WeaponType.values())
                    .filter(weaponType ->
                            weaponType.getCategory().isSimple()
                                || weaponType == HAND_CROSSBOW
                                || weaponType == LONGSWORD
                                || weaponType == RAPIER
                                || weaponType == SHORTSWORD
                    )
                    .collect(Collectors.toList()),
            8,
            5
    );

    public static final DnDClass CLERIC = new DnDClass(
            "Cleric",
            "A priestly champion who wields divine magic in service of a higher power",
            new Roll(new Die(1, 8)),
            WISDOM,
            Arrays.asList(
                    WISDOM,
                    CHARISMA
            ),
            Arrays.stream(ArmorType.values())
                    .filter(armorType ->
                            armorType.getCategory() == LIGHT_ARMOR
                                || armorType.getCategory() == MEDIUM_ARMOR
                                || armorType.getCategory() == ArmorCategory.SHIELD
                    ).collect(Collectors.toList()),
            Arrays.stream(WeaponType.values())
                    .filter(weaponType -> weaponType.getCategory().isSimple())
                    .collect(Collectors.toList()),
            8,
            5
    );

    public static final DnDClass DRUID = new DnDClass(
            "Druid",
            "A priest of the Old Faith, wielding the powers of nature - moonlight and plant growth, fire and lightning - and adopting animal forms",
            new Roll(new Die(1, 8)),
            WISDOM,
            Arrays.asList(
                    INTELLIGENCE,
                    WISDOM
            ),
            Arrays.stream(ArmorType.values())
                    .filter(armorType ->
                            (
                                    armorType.getCategory() == LIGHT_ARMOR
                                            || armorType.getCategory() == MEDIUM_ARMOR
                            ) && !armorType.isMetal()
                    )
                    .collect(Collectors.toList()),
            Arrays.asList(
                    CLUB,
                    DAGGER,
                    DART,
                    JAVELIN,
                    MACE,
                    QUARTERSTAFF,
                    SCIMITAR,
                    SICKLE,
                    SLING,
                    SPEAR
            ),
            8,
            5
    );

    public static final DnDClass FIGHTER = new DnDClass(
            "Fighter",
            "A master of martial combat, skilled with a variety of weapons and armor",
            new Roll(new Die(1, 10)),
            STRENGTH, // or DEXTERITY
            Arrays.asList(
                    STRENGTH,
                    CONSTITUTION
            ),
            Arrays.asList(ArmorType.values()),
            Arrays.asList(WeaponType.values()),
            10,
            6
    );

    public static final DnDClass MONK = new DnDClass(
            "Monk",
            "A master of martial arts, harnessing the power of the body in pursuit of physical and " +
                    "spiritual perfection",
            new Roll(new Die(1, 8)),
            DEXTERITY, // and WISDOM
            Arrays.asList(
                    STRENGTH,
                    DEXTERITY
            ),
            Collections.emptyList(),
            Arrays.stream(WeaponType.values())
                    .filter(weaponType ->
                            weaponType.getCategory().isSimple()
                                || weaponType == SHORTSWORD
                    )
                    .collect(Collectors.toList()),
            8,
            5
    );

    public static final DnDClass PALADIN = new DnDClass(
            "Paladin",
            "A holy warrior bound to a sacred oath",
            new Roll(new Die(1, 10)),
            STRENGTH, // and CHARISMA
            Arrays.asList(
                    WISDOM,
                    CHARISMA
            ),
            Arrays.asList(ArmorType.values()),
            Arrays.asList(WeaponType.values()),
            10,
            6
    );

    public static final DnDClass RANGER = new DnDClass(
            "Ranger",
            "A warrior who uses martial prowess and the nature magic to combat threats on the edges of " +
                    "civilization",
            new Roll(new Die(1, 10)),
            DEXTERITY, // and WISDOM
            Arrays.asList(
                    STRENGTH,
                    DEXTERITY
            ),
            Arrays.stream(ArmorType.values())
                    .filter(armorType ->
                            armorType.getCategory() == LIGHT_ARMOR
                                    || armorType.getCategory() == MEDIUM_ARMOR
                                    || armorType.getCategory() == SHIELD
                    )
                    .collect(Collectors.toList()),
            Arrays.asList(WeaponType.values()),
            10,
            6
    );

    public static final DnDClass ROGUE = new DnDClass(
            "Rogue",
            "A scoundrel who uses stealth and trickery to overcome obstacles and enemies",
            new Roll(new Die(1, 8)),
            DEXTERITY,
            Arrays.asList(
                    DEXTERITY,
                    INTELLIGENCE
            ),
            Arrays.stream(ArmorType.values())
                    .filter(armorType -> armorType.getCategory() == LIGHT_ARMOR)
                    .collect(Collectors.toList()),
            Arrays.stream(WeaponType.values())
                    .filter(weaponType ->
                            weaponType.getCategory().isSimple()
                                || weaponType == HAND_CROSSBOW
                                || weaponType == LONGSWORD
                                || weaponType == RAPIER
                                || weaponType == SHORTSWORD
                    )
                    .collect(Collectors.toList()),
            8,
            5
    );

    public static final DnDClass SORCERER = new DnDClass(
            "Sorcerer",
            "A spellcaster who draws on inherent magic from a gift or bloodline",
            new Roll(new Die(1, 6)),
            CHARISMA,
            Arrays.asList(
                    CONSTITUTION,
                    CHARISMA
            ),
            Collections.emptyList(),
            Arrays.asList(
                    DAGGER,
                    DART,
                    SLING,
                    QUARTERSTAFF,
                    LIGHT_CROSSBOW
            ),
            6,
            4
    );

    public static final DnDClass WARLOCK = new DnDClass(
            "Warlock",
            "A wielder of magic that is derived from a bargain with an extraplanar entity",
            new Roll(new Die(1, 8)),
            CHARISMA,
            Arrays.asList(
                    WISDOM,
                    CHARISMA
            ),
            Arrays.stream(ArmorType.values())
                    .filter(armorType -> armorType.getCategory() == LIGHT_ARMOR)
                    .collect(Collectors.toList()),
            Arrays.stream(WeaponType.values())
                    .filter(weaponType -> weaponType.getCategory().isSimple())
                    .collect(Collectors.toList()),
            8,
            5
    );

    public static final DnDClass WIZARD = new DnDClass(
            "Wizard",
            "A scholarly magic-user capable of manipulating the structures of reality",
            new Roll(new Die(1, 6)),
            INTELLIGENCE,
            Arrays.asList(
                    INTELLIGENCE,
                    WISDOM
            ),
            Collections.emptyList(),
            Arrays.asList(
                    DAGGER,
                    DART,
                    SLING,
                    QUARTERSTAFF,
                    LIGHT_CROSSBOW
            ),
            6,
            4
    );

    public static DnDClass valueOf(String name) {
        switch (name) {
            case "BARBARIAN": return BARBARIAN;
            case "BARD": return BARD;
            case "CLERIC": return CLERIC;
            case "DRUID": return DRUID;
            case "FIGHTER": return FIGHTER;
            case "MONK": return MONK;
            case "PALADIN": return PALADIN;
            case "RANGER": return RANGER;
            case "ROGUE": return ROGUE;
            case "SORCERER": return SORCERER;
            case "WARLOCK": return WARLOCK;
            case "WIZARD": return WIZARD;
            default: return null;
        }
    }

    public static DnDClass getByName(String name) {
        return Arrays.stream(values())
                .filter(clazz -> clazz.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public static DnDClass[] values() {
        return new DnDClass[] {
                BARBARIAN,
                BARD,
                CLERIC,
                DRUID,
                FIGHTER,
                MONK,
                PALADIN,
                RANGER,
                ROGUE,
                SORCERER,
                WARLOCK,
                WIZARD
        };
    }

    private final String name;
    private final String description;
    private final Roll hitDie;
    private final Ability primaryAbility;
    private final List<Ability> savingThrowProficiencies;
    private final List<ArmorType> armorProficiencies;
    private final List<WeaponType> weaponProficiencies;
    private final int baseHP;
    private final int levelHP;

    private DnDClass(
            String name,
            String description,
            Roll hitDie,
            Ability primaryAbility,
            List<Ability> savingThrowProficiencies,
            List<ArmorType> armorProficiencies,
            List<WeaponType> weaponProficiencies,
            int baseHP,
            int levelHP
    ) {
        this.name = name;
        this.description = description;
        this.hitDie = hitDie;
        this.primaryAbility = primaryAbility;
        this.savingThrowProficiencies = savingThrowProficiencies;
        this.armorProficiencies = armorProficiencies;
        this.weaponProficiencies = weaponProficiencies;
        this.baseHP = baseHP;
        this.levelHP = levelHP;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Roll getHitDie() {
        return hitDie;
    }

    public Ability getPrimaryAbility() {
        return primaryAbility;
    }

    public List<Ability> getSavingThrowProficiencies() {
        return savingThrowProficiencies;
    }

    public List<ArmorType> getArmorProficiencies() {
        return armorProficiencies;
    }

    public List<WeaponType> getWeaponProficiencies() {
        return weaponProficiencies;
    }

    public int getBaseHP() {
        return baseHP;
    }

    public int getLevelHP() {
        return levelHP;
    }

    public int calculateHP(int level, int constMod) {
        return baseHP + ((level - (level == 1 ? 0 : 1)) * constMod) + (level * levelHP);
    }

}
