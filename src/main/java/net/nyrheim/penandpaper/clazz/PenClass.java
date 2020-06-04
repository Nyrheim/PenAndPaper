package net.nyrheim.penandpaper.clazz;

import net.nyrheim.penandpaper.ability.Ability;
import net.nyrheim.penandpaper.dice.Roll;
import net.nyrheim.penandpaper.dice.Roll.Die;
import net.nyrheim.penandpaper.item.armor.ArmorCategory;
import net.nyrheim.penandpaper.item.armor.ArmorType;
import net.nyrheim.penandpaper.item.weapon.WeaponType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class PenClass {

    public static final PenClass BARBARIAN = new PenClass(
            "Barbarian",
            "A fierce warrior of primitive background who can enter a battle rage",
            new Roll(new Die(1, 12)),
            Ability.STRENGTH,
            Arrays.asList(
                    Ability.STRENGTH,
                    Ability.CONSTITUTION
            ),
            Arrays.stream(ArmorType.values())
                    .filter(armorType ->
                            armorType.getCategory() == ArmorCategory.LIGHT_ARMOR
                                || armorType.getCategory() == ArmorCategory.MEDIUM_ARMOR
                                || armorType.getCategory() == ArmorCategory.SHIELD
                    )
                    .collect(Collectors.toList()),
            Arrays.asList(WeaponType.values()),
            12,
            7,
            new MulticlassingRequirement.AbilityRequirement(Ability.STRENGTH, 13)
    );

    public static final PenClass BARD = new PenClass(
            "Bard",
            "An inspiring magician whose power echoes the music of creation",
            new Roll(new Die(1, 8)),
            Ability.CHARISMA,
            Arrays.asList(
                    Ability.DEXTERITY,
                    Ability.CHARISMA
            ),
            Arrays.stream(ArmorType.values())
                    .filter(armorType -> armorType.getCategory() == ArmorCategory.LIGHT_ARMOR)
                    .collect(Collectors.toList()),
            Arrays.stream(WeaponType.values())
                    .filter(weaponType ->
                            weaponType.getCategory().isSimple()
                                || weaponType == WeaponType.HAND_CROSSBOW
                                || weaponType == WeaponType.LONGSWORD
                                || weaponType == WeaponType.RAPIER
                                || weaponType == WeaponType.SHORTSWORD
                    )
                    .collect(Collectors.toList()),
            8,
            5,
            new MulticlassingRequirement.AbilityRequirement(Ability.CHARISMA, 13)
    );

    public static final PenClass CLERIC = new PenClass(
            "Cleric",
            "A priestly champion who wields divine magic in service of a higher power",
            new Roll(new Die(1, 8)),
            Ability.WISDOM,
            Arrays.asList(
                    Ability.WISDOM,
                    Ability.CHARISMA
            ),
            Arrays.stream(ArmorType.values())
                    .filter(armorType ->
                            armorType.getCategory() == ArmorCategory.LIGHT_ARMOR
                                || armorType.getCategory() == ArmorCategory.MEDIUM_ARMOR
                                || armorType.getCategory() == ArmorCategory.SHIELD
                    ).collect(Collectors.toList()),
            Arrays.stream(WeaponType.values())
                    .filter(weaponType -> weaponType.getCategory().isSimple())
                    .collect(Collectors.toList()),
            8,
            5,
            new MulticlassingRequirement.AbilityRequirement(Ability.WISDOM, 13)
    );

    public static final PenClass DRUID = new PenClass(
            "Druid",
            "A priest of the Old Faith, wielding the powers of nature - moonlight and plant growth, fire and lightning - and adopting animal forms",
            new Roll(new Die(1, 8)),
            Ability.WISDOM,
            Arrays.asList(
                    Ability.INTELLIGENCE,
                    Ability.WISDOM
            ),
            Arrays.stream(ArmorType.values())
                    .filter(armorType ->
                            (
                                    armorType.getCategory() == ArmorCategory.LIGHT_ARMOR
                                            || armorType.getCategory() == ArmorCategory.MEDIUM_ARMOR
                            ) && !armorType.isMetal()
                    )
                    .collect(Collectors.toList()),
            Arrays.asList(
                    WeaponType.CLUB,
                    WeaponType.DAGGER,
                    WeaponType.DART,
                    WeaponType.JAVELIN,
                    WeaponType.MACE,
                    WeaponType.QUARTERSTAFF,
                    WeaponType.SCIMITAR,
                    WeaponType.SICKLE,
                    WeaponType.SLING,
                    WeaponType.SPEAR
            ),
            8,
            5,
            new MulticlassingRequirement.AbilityRequirement(Ability.WISDOM, 13)
    );

    public static final PenClass FIGHTER = new PenClass(
            "Fighter",
            "A master of martial combat, skilled with a variety of weapons and armor",
            new Roll(new Die(1, 10)),
            Ability.STRENGTH, // or DEXTERITY
            Arrays.asList(
                    Ability.STRENGTH,
                    Ability.CONSTITUTION
            ),
            Arrays.asList(ArmorType.values()),
            Arrays.asList(WeaponType.values()),
            10,
            6,
            new MulticlassingRequirement.AnyRequirement(
                    new MulticlassingRequirement.AbilityRequirement(Ability.STRENGTH, 13),
                    new MulticlassingRequirement.AbilityRequirement(Ability.DEXTERITY, 13)
            )
    );

    public static final PenClass MONK = new PenClass(
            "Monk",
            "A master of martial arts, harnessing the power of the body in pursuit of physical and " +
                    "spiritual perfection",
            new Roll(new Die(1, 8)),
            Ability.DEXTERITY, // and WISDOM
            Arrays.asList(
                    Ability.STRENGTH,
                    Ability.DEXTERITY
            ),
            Collections.emptyList(),
            Arrays.stream(WeaponType.values())
                    .filter(weaponType ->
                            weaponType.getCategory().isSimple()
                                || weaponType == WeaponType.SHORTSWORD
                    )
                    .collect(Collectors.toList()),
            8,
            5,
            new MulticlassingRequirement.AllRequirement(
                    new MulticlassingRequirement.AbilityRequirement(Ability.DEXTERITY, 13),
                    new MulticlassingRequirement.AbilityRequirement(Ability.WISDOM, 13)
            )
    );

    public static final PenClass PALADIN = new PenClass(
            "Paladin",
            "A holy warrior bound to a sacred oath",
            new Roll(new Die(1, 10)),
            Ability.STRENGTH, // and CHARISMA
            Arrays.asList(
                    Ability.WISDOM,
                    Ability.CHARISMA
            ),
            Arrays.asList(ArmorType.values()),
            Arrays.asList(WeaponType.values()),
            10,
            6,
            new MulticlassingRequirement.AllRequirement(
                    new MulticlassingRequirement.AbilityRequirement(Ability.STRENGTH, 13),
                    new MulticlassingRequirement.AbilityRequirement(Ability.CHARISMA, 13)
            )
    );

    public static final PenClass RANGER = new PenClass(
            "Ranger",
            "A warrior who uses martial prowess and the nature magic to combat threats on the edges of " +
                    "civilization",
            new Roll(new Die(1, 10)),
            Ability.DEXTERITY, // and WISDOM
            Arrays.asList(
                    Ability.STRENGTH,
                    Ability.DEXTERITY
            ),
            Arrays.stream(ArmorType.values())
                    .filter(armorType ->
                            armorType.getCategory() == ArmorCategory.LIGHT_ARMOR
                                    || armorType.getCategory() == ArmorCategory.MEDIUM_ARMOR
                                    || armorType.getCategory() == ArmorCategory.SHIELD
                    )
                    .collect(Collectors.toList()),
            Arrays.asList(WeaponType.values()),
            10,
            6,
            new MulticlassingRequirement.AllRequirement(
                    new MulticlassingRequirement.AbilityRequirement(Ability.DEXTERITY, 13),
                    new MulticlassingRequirement.AbilityRequirement(Ability.WISDOM, 13)
            )
    );

    public static final PenClass ROGUE = new PenClass(
            "Rogue",
            "A scoundrel who uses stealth and trickery to overcome obstacles and enemies",
            new Roll(new Die(1, 8)),
            Ability.DEXTERITY,
            Arrays.asList(
                    Ability.DEXTERITY,
                    Ability.INTELLIGENCE
            ),
            Arrays.stream(ArmorType.values())
                    .filter(armorType -> armorType.getCategory() == ArmorCategory.LIGHT_ARMOR)
                    .collect(Collectors.toList()),
            Arrays.stream(WeaponType.values())
                    .filter(weaponType ->
                            weaponType.getCategory().isSimple()
                                || weaponType == WeaponType.HAND_CROSSBOW
                                || weaponType == WeaponType.LONGSWORD
                                || weaponType == WeaponType.RAPIER
                                || weaponType == WeaponType.SHORTSWORD
                    )
                    .collect(Collectors.toList()),
            8,
            5,
            new MulticlassingRequirement.AbilityRequirement(Ability.DEXTERITY, 13)
    );

    public static final PenClass SORCERER = new PenClass(
            "Sorcerer",
            "A spellcaster who draws on inherent magic from a gift or bloodline",
            new Roll(new Die(1, 6)),
            Ability.CHARISMA,
            Arrays.asList(
                    Ability.CONSTITUTION,
                    Ability.CHARISMA
            ),
            Collections.emptyList(),
            Arrays.asList(
                    WeaponType.DAGGER,
                    WeaponType.DART,
                    WeaponType.SLING,
                    WeaponType.QUARTERSTAFF,
                    WeaponType.LIGHT_CROSSBOW
            ),
            6,
            4,
            new MulticlassingRequirement.AbilityRequirement(Ability.CHARISMA, 13)
    );

    public static final PenClass WARLOCK = new PenClass(
            "Warlock",
            "A wielder of magic that is derived from a bargain with an extraplanar entity",
            new Roll(new Die(1, 8)),
            Ability.CHARISMA,
            Arrays.asList(
                    Ability.WISDOM,
                    Ability.CHARISMA
            ),
            Arrays.stream(ArmorType.values())
                    .filter(armorType -> armorType.getCategory() == ArmorCategory.LIGHT_ARMOR)
                    .collect(Collectors.toList()),
            Arrays.stream(WeaponType.values())
                    .filter(weaponType -> weaponType.getCategory().isSimple())
                    .collect(Collectors.toList()),
            8,
            5,
            new MulticlassingRequirement.AbilityRequirement(Ability.CHARISMA, 13)
    );

    public static final PenClass WIZARD = new PenClass(
            "Wizard",
            "A scholarly magic-user capable of manipulating the structures of reality",
            new Roll(new Die(1, 6)),
            Ability.INTELLIGENCE,
            Arrays.asList(
                    Ability.INTELLIGENCE,
                    Ability.WISDOM
            ),
            Collections.emptyList(),
            Arrays.asList(
                    WeaponType.DAGGER,
                    WeaponType.DART,
                    WeaponType.SLING,
                    WeaponType.QUARTERSTAFF,
                    WeaponType.LIGHT_CROSSBOW
            ),
            6,
            4,
            new MulticlassingRequirement.AbilityRequirement(Ability.INTELLIGENCE, 13)
    );

    public static PenClass valueOf(String name) {
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

    public static PenClass getByName(String name) {
        return Arrays.stream(values())
                .filter(clazz -> clazz.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public static PenClass[] values() {
        return new PenClass[] {
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
    private final MulticlassingRequirement multiclassingRequirement;

    private PenClass(
            String name,
            String description,
            Roll hitDie,
            Ability primaryAbility,
            List<Ability> savingThrowProficiencies,
            List<ArmorType> armorProficiencies,
            List<WeaponType> weaponProficiencies,
            int baseHP,
            int levelHP,
            MulticlassingRequirement multiclassingRequirement
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
        this.multiclassingRequirement = multiclassingRequirement;
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

    public MulticlassingRequirement getMulticlassingRequirement() {
        return multiclassingRequirement;
    }
}
