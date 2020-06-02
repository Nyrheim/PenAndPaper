package com.github.liamvii.penandpaper.race;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.distance.Distance;
import com.github.liamvii.penandpaper.item.armor.ArmorType;
import com.github.liamvii.penandpaper.weight.Weight;
import com.rpkit.core.exception.UnregisteredServiceException;
import com.rpkit.languages.bukkit.language.RPKLanguageProvider;

import java.util.*;

import static com.github.liamvii.penandpaper.ability.Ability.*;
import static com.github.liamvii.penandpaper.distance.DistanceUnit.FEET;
import static com.github.liamvii.penandpaper.distance.DistanceUnit.INCHES;
import static com.github.liamvii.penandpaper.item.armor.ArmorCategory.LIGHT_ARMOR;
import static com.github.liamvii.penandpaper.item.armor.ArmorCategory.MEDIUM_ARMOR;
import static com.github.liamvii.penandpaper.item.weapon.WeaponType.*;
import static com.github.liamvii.penandpaper.skill.Skill.*;
import static com.github.liamvii.penandpaper.weight.WeightUnit.LB;

public final class PenRaceService {

    private final Pen plugin;

    private final List<Race> races = new ArrayList<>();

    public PenRaceService(Pen plugin) {
        this.plugin = plugin;
    }

    public void initializeRaces() {
        RPKLanguageProvider languageProvider;
        try {
            languageProvider = plugin.core.getServiceManager().getServiceProvider(RPKLanguageProvider.class);
        } catch (UnregisteredServiceException exception) {
            throw new RuntimeException("No language provider available", exception);
        }

        Race dwarf = new BaseRace.Builder("Dwarf")
                .minimumHeight(new Distance(43, INCHES))
                .maximumHeight(new Distance(5, FEET))
                .minimumWeight(new Weight(130, LB))
                .maximumWeight(new Weight(226, LB))
                .minimumAge(50)
                .maximumAge(200)
                .languages(
                        languageProvider.getLanguage("Common"),
                        languageProvider.getLanguage("Dwarvish")
                )
                .speed(new Distance(25, FEET))
                .abilityScoreModifier(CONSTITUTION, 2)
                .darkVision(new Distance(60, FEET))
                .weaponProficiencies(
                        BATTLEAXE,
                        HANDAXE,
                        LIGHT_HAMMER,
                        WARHAMMER
                )
                .skillProficiencies(
                        HISTORY
                )
                .traits(
                        new RaceTrait("Dwarven Resilience", "You have advantage on saving throws " +
                                "against poison, and you have resistance against poison damage"),
                        new RaceTrait("Tool Proficiency", "You gain proficiency with the artisan's " +
                                "tools of your choice: smith's tools, brewer's supplies, or mason's tools."),
                        new RaceTrait("Stonecunning", "Whenever you make an Intelligence (History) " +
                                "check related to the origin of stonework, you are considered proficient in the " +
                                "History skill and add double your proficiency bonus to the check, instead of your " +
                                "normal proficiency bonus.")
                )
                .build();

        races.add(dwarf);

        Race goldDwarf = new Subrace.Builder(dwarf, "Gold Dwarf")
                .alias("Hill Dwarf")
                .abilityScoreModifier(WISDOM, 1)
                .traits(
                        new RaceTrait("Dwarven Toughness", "Your hit point maximum increases by 1, " +
                                "and it increases by 1 every time you gain a level.")
                )
                .build();

        races.add(goldDwarf);

        Race shieldDwarf = new Subrace.Builder(dwarf, "Shield Dwarf")
                .alias("Mountain Dwarf")
                .abilityScoreModifier(STRENGTH, 2)
                .armorProficiencies(
                        Arrays.stream(ArmorType.values()).filter(armorType -> armorType.getCategory() == LIGHT_ARMOR
                                || armorType.getCategory() == MEDIUM_ARMOR)
                                .toArray(ArmorType[]::new)
                )
                .build();

        races.add(shieldDwarf);

        Race elf = new BaseRace.Builder("Elf")
                .minimumHeight(new Distance(53, INCHES))
                .maximumHeight(new Distance(6, FEET))
                .minimumWeight(new Weight(80, LB))
                .maximumWeight(new Weight(165, LB))
                .minimumAge(110)
                .maximumAge(750)
                .languages(
                        languageProvider.getLanguage("Common"),
                        languageProvider.getLanguage("Elvish")
                )
                .speed(new Distance(30, FEET))
                .abilityScoreModifier(DEXTERITY, 2)
                .darkVision(new Distance(60, FEET))
                .skillProficiencies(
                        PERCEPTION
                )
                .traits(
                        new RaceTrait("Fey Ancestry", "You have advantage on saving throws against " +
                                "being charmed, and magic can't put you to sleep."),
                        new RaceTrait("Trance", "Elves don't need to sleep. Instead, they meditate " +
                                "deeply, remaining semiconscious, for 4 hours a day. (The Common word for such " +
                                "meditation is \"trance\".) While meditating, you can dream after a fashion; such " +
                                "dreams are actually mental exercises that have become reflexive through years of " +
                                "practice. After resting in this way, you gain the same benefit that a human does " +
                                "from 8 hours of sleep.")
                )
                .build();

        races.add(elf);

        Race highElf = new Subrace.Builder(elf, "High Elf")
                .aliases(
                        "Eladrin",
                        "Moon Elf",
                        "Sun Elf"
                )
                .abilityScoreModifier(CHARISMA, 1)
                .traits(
                        new RaceTrait("Seasonal Folk", "When finishing a long rest, any eladrin can " +
                                "change their season. An eladrin might choose the season that is present in the" +
                                " world or perhaps the season that most closely matches the eladrin's current " +
                                "emotional state. For example, an eladrin might shift to autumn if filled with " +
                                "contentment, another eladrin could change to winter if plunged into sorrow, still " +
                                "another might be bursting with joy and become an eladrin of spring, and fury might " +
                                "cause an eladrin to change to summer."),
                        new RaceTrait("Feystep", "As a bonus action, you can magically teleport up " +
                                "to 30 feet to an unoccupied space you can see. Once you use this trait, you can't " +
                                "do so again until you finish a short or long rest. When you reach 3rd level, your " +
                                "Fey Step gains an additional effect based on your season; if the effect requires a " +
                                "saving throw, the DC equals 8 +your proficiency bonus+ your Charisma modifier"),
                        new RaceTrait("Autumn", "Immediately after you use your Fey Step, up to two " +
                                "creatures of your choice that you can see within 10 feet of you must succeed on a " +
                                "Wisdom saving throw or be charmed by you for 1 minute, or until you or your " +
                                "companions deal any damage to it."),
                        new RaceTrait("Winter", "When you use your Fey Step, one creature of your " +
                                "choice that you can see within 5 feet of you before you teleport must succeed on " +
                                "a Wisdom saving throw or be frightened of you until the end of your next turn.")
                )
                .build();

        races.add(highElf);

        Race woodElf = new Subrace.Builder(elf, "Wood Elf")
                .abilityScoreModifier(WISDOM, 1)
                .traits(
                        new RaceTrait("Mask of the Wild", "Wood Elves can attempt to hide even when " +
                                "they are only lightly obscured by foliage, heavy rain, falling snow, mist and " +
                                "other natural phenomena.")
                )
                .build();

        races.add(woodElf);

        Race halfling = new BaseRace.Builder("Halfling")
                .minimumHeight(new Distance(30, INCHES))
                .maximumHeight(new Distance(40, INCHES))
                .minimumWeight(new Weight(25, LB))
                .maximumWeight(new Weight(38, LB))
                .minimumAge(20)
                .maximumAge(150)
                .languages(
                        languageProvider.getLanguage("Common"),
                        languageProvider.getLanguage("Halfling")
                )
                .speed(new Distance(25, FEET))
                .abilityScoreModifier(DEXTERITY, 2)
                .traits(
                        new RaceTrait("Lucky", "When Halflings roll a 1 on an attack roll, ability " +
                                "check, or saving throw, they can reroll the die and must use the new roll. They " +
                                "regain the ability to use this feature once they finish a long rest (3 IRL hours)."),
                        new RaceTrait("Brave", "Halflings have advantage on saving throws against " +
                                "being frightened."),
                        new RaceTrait("Halfling Nimbleness", "Halflings can move through the space " +
                                "of any creature that is of a size larger than theirs (Medium, Large, etc....)")
                )
                .build();

        races.add(halfling);

        Race lightfootHalfling = new Subrace.Builder(halfling, "Lightfoot Halfling")
                .abilityScoreModifier(CHARISMA, 1)
                .traits(
                        new RaceTrait("Naturally Stealthy", "Lightfoot Halflings can attempt to " +
                                "hide even when they are obscured only by a creature that is at least one size " +
                                "larger than them.")
                )
                .build();

        races.add(lightfootHalfling);

        Race stoutfootHalfling = new Subrace.Builder(halfling, "Stoutfoot Halfling")
                .aliases(
                        "Stout Halfling",
                        "Strongheart Halfling"
                )
                .abilityScoreModifier(CONSTITUTION, 1)
                .traits(
                        new RaceTrait("Stout Resilience", "Stout Halflings have an advantage on " +
                                "saving throws against poison, and they have resistance (takes half damage) against " +
                                "poison damage.")
                )
                .build();

        races.add(stoutfootHalfling);

        Race ghostwiseHalfling = new Subrace.Builder(halfling, "Ghostwise Halfling")
                .abilityScoreModifier(WISDOM, 1)
                .traits(
                        new RaceTrait("Silent Speech", "Ghostwise Halflings can speak telepathically " +
                                " to any creature within 6 blocks of them. The creature understands them only if the " +
                                "two share a language. They can speak telepathically in this way to one creature at " +
                                "a time.")
                )
                .build();

        races.add(ghostwiseHalfling);

        Race human = new BaseRace.Builder("Human")
                .minimumHeight(new Distance(58, INCHES))
                .maximumHeight(new Distance(78, INCHES))
                .minimumWeight(new Weight(120, LB))
                .maximumWeight(new Weight(200, LB))
                .minimumAge(18)
                .maximumAge(90)
                .languages(
                        languageProvider.getLanguage("Common")
                )
                .speed(new Distance(30, FEET))
                .traits(
                        new RaceTrait("Ability Score Increase", "Two different ability scores of " +
                                "your choice increase by 1."),
                        new RaceTrait("Skill Versatility", "You gain proficiency in any combination " +
                                "of three skills, tools, or languages of your choice."),
                        new RaceTrait("Human Specialization", "Gain one feat of your choice from " +
                                "the feat list.")
                )
                .build();

        races.add(human);

        Race gnome = new BaseRace.Builder("Gnome")
                .minimumHeight(new Distance(34, INCHES))
                .maximumHeight(new Distance(44, INCHES))
                .minimumWeight(new Weight(35, LB))
                .maximumWeight(new Weight(48, LB))
                .minimumAge(40)
                .maximumAge(500)
                .languages(
                        languageProvider.getLanguage("Common"),
                        languageProvider.getLanguage("Gnomish")
                )
                .speed(new Distance(25, FEET))
                .abilityScoreModifier(INTELLIGENCE, 2)
                .darkVision(new Distance(60, FEET))
                .traits(
                        new RaceTrait("Gnome Cunning", "Gnomes have an advantage on all Intelligence," +
                                "Wisdom, and Charisma saving throws against magic.")
                )
                .build();

        races.add(gnome);

        Subrace forestGnome = new Subrace.Builder(gnome, "Forest Gnome")
                .abilityScoreModifier(DEXTERITY, 1)
                .traits(
                        new RaceTrait("Natural Illusionist", "Forest Gnomes know the [minor illusion] " +
                                "cantrip. Intelligence is their spellcasting ability for it.")
                )
                .build();

        races.add(forestGnome);

        Subrace rockGnome = new Subrace.Builder(gnome, "Rock Gnome")
                .abilityScoreModifier(CONSTITUTION, 1)
                .skillProficiencies(
                        HISTORY
                )
                .build();

        races.add(rockGnome);

        Race halfElf = new BaseRace.Builder("Half-Elf")
                .minimumHeight(new Distance(53, INCHES))
                .maximumHeight(new Distance(6, FEET))
                .minimumWeight(new Weight(80, LB))
                .maximumWeight(new Weight(180, LB))
                .minimumAge(20)
                .maximumAge(190)
                .languages(
                        languageProvider.getLanguage("Common"),
                        languageProvider.getLanguage("Elvish")
                )
                .speed(new Distance(30, FEET))
                .abilityScoreModifier(CHARISMA, 1)
                .darkVision(new Distance(60, FEET))
                .traits(
                        new RaceTrait("Ability Score Increase", "Two ability scores of your choice " +
                                "are increased by 1, or one ability score by 2, but not charisma by 3 in total."),
                        new RaceTrait("Fey Ancestry", "Half-Elves have advantage on saving throws " +
                                "against being Charmed, and magic can't put them to sleep."),
                        new RaceTrait("Skill Versatility", "Half-Elves gain proficiency in two skills" +
                                "of their choice."),
                        new RaceTrait("Languages", "You can speak, read and write one extra language" +
                                "of your choice.")
                )
                .build();

        races.add(halfElf);

        Race halfMoonElf = new Subrace.Builder(halfElf, "Half-Moon-Elf")
                .alias("Moon-Half-Elf")
                .weaponProficiencies(
                        LONGSWORD,
                        SHORTSWORD,
                        SHORTBOW,
                        LONGBOW
                )
                .traits(
                        new RaceTrait("Cantrips", "Half-Elves of Moon Elf descent know one cantrip of their choice from " +
                                "the wizard spell list. Intelligence is their spellcasting ability for it.")
                )
                .build();

        races.add(halfMoonElf);

        Race halfSunElf = new Subrace.Builder(halfElf, "Half-Sun-Elf")
                .alias("Sun-Half-Elf")
                .weaponProficiencies(
                        LONGSWORD,
                        SHORTSWORD,
                        SHORTBOW,
                        LONGBOW
                )
                .traits(
                        new RaceTrait("Cantrips", "Half-Elves of Sun Elf descent know one cantrip of their choice from " +
                                "the wizard spell list. Intelligence is their spellcasting ability for it.")
                )
                .build();

        races.add(halfSunElf);

        Race halfWoodElf = new Subrace.Builder(halfElf, "Half-Wood-Elf")
                .alias("Wood-Half-Elf")
                .weaponProficiencies(
                        LONGSWORD,
                        SHORTSWORD,
                        SHORTBOW,
                        LONGBOW
                )
                .traits(
                        new RaceTrait("Fleet of Foot", "Half-Elves of Wood Elf descent's walking " +
                                "speed increases to 7 blocks."),
                        new RaceTrait("Mask of the Wild", "Half-Elves of Wood Elf descent can " +
                                "attempt to hide even when they are only lightly obscured by ")
                )
                .build();

        races.add(halfWoodElf);

        Race halfOrc = new BaseRace.Builder("Half-Orc")
                .minimumHeight(new Distance(53, INCHES))
                .maximumHeight(new Distance(82, INCHES))
                .minimumWeight(new Weight(110, LB))
                .maximumWeight(new Weight(342, LB))
                .minimumAge(14)
                .maximumAge(80)
                .languages(
                        languageProvider.getLanguage("Common"),
                        languageProvider.getLanguage("Orcish")
                )
                .speed(new Distance(30, FEET))
                .abilityScoreModifier(STRENGTH, 2)
                .abilityScoreModifier(CONSTITUTION, 1)
                .darkVision(new Distance(60, FEET))
                .skillProficiencies(
                        INTIMIDATION
                )
                .traits(
                        new RaceTrait("Relentless Endurance", "When Half-Orcs are reduced to 0 HP, " +
                                "or lower, they can instead drop to 1 HP instead. Once used, this trait must be " +
                                "refreshed until they finish a long rest (3 IRL hours)."),
                        new RaceTrait("Savage Attacks", "When Half-Orcs score a critical hit with a " +
                                "melee weapon attack, they can roll one of the weapon’s damage dice one additional " +
                                "time and add it to the extra damage of the critical hit.")
                )
                .build();

        races.add(halfOrc);

        Race storaman = new BaseRace.Builder("Storaman")
                .minimumHeight(new Distance(51, INCHES))
                .maximumHeight(new Distance(6, FEET))
                .minimumWeight(new Weight(100, LB))
                .maximumWeight(new Weight(220, LB))
                .minimumAge(20)
                .maximumAge(120)
                .languages(
                        languageProvider.getLanguage("Storan"),
                        languageProvider.getLanguage("Common"),
                        languageProvider.getLanguage("Dwarvish")
                )
                .speed(new Distance(30, FEET))
                .abilityScoreModifier(CONSTITUTION, 1)
                .abilityScoreModifier(WISDOM, 1)
                .darkVision(new Distance(60, FEET))
                .skillProficiencies(
                        NATURE
                )
                .traits(
                        new RaceTrait("Strong Roots", "You have advantage on any history and religion" +
                                " checks related to Brochvik and Nyrheim."),
                        new RaceTrait("Familiar Face", "You gain a +3 modifier to any persuasion " +
                                "rolls with the locals of Brochvik.")
                )
                .build();

        races.add(storaman);

        Race tiefling = new BaseRace.Builder("Tiefling")
                .minimumHeight(new Distance(53, INCHES))
                .maximumHeight(new Distance(78, INCHES))
                .minimumWeight(new Weight(85, LB))
                .maximumWeight(new Weight(280, LB))
                .minimumAge(18)
                .maximumAge(90)
                .languages(
                        languageProvider.getLanguage("Common")
                )
                .speed(new Distance(30, FEET))
                .abilityScoreModifier(CHARISMA, 2)
                .darkVision(new Distance(60, FEET))
                .build();

        races.add(tiefling);

        Race infernalAsmodeusTiefling = new Subrace.Builder(tiefling, "Infernal Tiefling: Asmodeus")
                .abilityScoreModifier(INTELLIGENCE, 1)
                .traits(
                        new RaceTrait("Infernal Legacy", "Infernal tieflings know the thaumaturgy " +
                                "cantrip. Once they reach 3rd level, they can cast the hellish rebuke spell as a " +
                                "2nd-level spell. Once they reach 5th level, they can also cast the darkness spell. " +
                                "Once this trait is used to cast either spell, tieflings can't use it again until " +
                                "they finish a long rest (3 IRL hours). Charisma is their spellcasting ability for " +
                                "these spells.")
                )
                .languages(
                        languageProvider.getLanguage("Infernal")
                )
                .build();

        races.add(infernalAsmodeusTiefling);

        Race infernalBaalzebulTiefling = new Subrace.Builder(tiefling, "Infernal Tiefling: Baalzebul")
                .abilityScoreModifier(INTELLIGENCE, 1)
                .traits(
                        new RaceTrait("Legacy of Maladomini", "You know the thaumaturgy cantrip. " +
                                "When you reach 3rd level, you can cast the ray of sickness spell as a 2nd-level " +
                                "spell once with this trait and regain the ability to do so when you finish a long " +
                                "rest. When you reach 5th level, you can cast the crown of madness spell once with " +
                                "this trait and regain the ability to do so when you finish a long rest. Charisma " +
                                "is your spellcasting ability for these spells.")
                )
                .languages(
                        languageProvider.getLanguage("Infernal")
                )
                .build();

        races.add(infernalBaalzebulTiefling);

        Race infernalDispaterTiefling = new Subrace.Builder(tiefling, "Infernal Tiefling: Dispater")
                .abilityScoreModifier(INTELLIGENCE, 1)
                .traits(
                        new RaceTrait("Legacy of Dis", "You know the thaumaturgy cantrip. When " +
                                "you reach 3rd level, you can cast the disguise self spell once with this trait " +
                                "and regain the ability to do so when you finish a long rest. When you reach 5th " +
                                "level, you can cast the detect thoughts spell once with this trait and regain the " +
                                "ability to do so when you finish a long rest. Charisma is your spellcasting ability " +
                                "for these spells.")
                )
                .languages(
                        languageProvider.getLanguage("Infernal")
                )
                .build();

        races.add(infernalDispaterTiefling);

        Race infernalFiernaTiefling = new Subrace.Builder(tiefling, "Infernal Tiefling: Fierna")
                .abilityScoreModifier(WISDOM, 1)
                .traits(
                        new RaceTrait("Legacy of Phlegethos", "You know the friends cantrip. When " +
                                "you reach 3rd level, you can cast the charm person spell as a 2nd-level spell once " +
                                "with this trait and regain the ability to do so when you finish a long rest. When " +
                                "you reach 5th level, you can cast the suggestion spell once with this trait and " +
                                "regain the ability to do so when you finish a long rest. Charisma is your " +
                                "spellcasting ability for these spells.")
                )
                .languages(
                        languageProvider.getLanguage("Infernal")
                )
                .build();

        races.add(infernalFiernaTiefling);

        Race infernalGlasyaTiefling = new Subrace.Builder(tiefling, "Infernal Tiefling: Glasya")
                .abilityScoreModifier(DEXTERITY, 1)
                .traits(
                        new RaceTrait("Legacy of Malbolge", "You know the minor illusion cantrip. " +
                                "When you reach 3rd level, you can cast the disguise self spell once with this " +
                                "trait and regain the ability to do so when you finish a long rest. When you reach " +
                                "5th level, you can cast the invisibility spell once with this trait and regain " +
                                "the ability to do so when you finish a long rest. Charisma is your spellcasting " +
                                "ability for these spells.")
                )
                .languages(
                        languageProvider.getLanguage("Infernal")
                )
                .build();

        races.add(infernalGlasyaTiefling);

        Race infernalLevistusTiefling = new Subrace.Builder(tiefling, "Infernal Tiefling: Levistus")
                .abilityScoreModifier(CONSTITUTION, 1)
                .traits(
                        new RaceTrait("Legacy of Stygia", "You know the ray of frost cantrip. When " +
                                "you reach 3rd level, you can cast the armor of Agathys spell as a 2nd-level spell " +
                                "once with this trait and regain the ability to do so when you finish a long rest. " +
                                "When you reach 5th level, you can cast the darkness spell once with this trait and " +
                                "regain the ability to do so when you finish a long rest. Charisma is your " +
                                "spellcasting ability for these spells.")
                )
                .languages(
                        languageProvider.getLanguage("Infernal")
                )
                .build();

        races.add(infernalLevistusTiefling);

        Race infernalMammonTiefling = new Subrace.Builder(tiefling, "Infernal Tiefling: Mammon")
                .abilityScoreModifier(INTELLIGENCE, 1)
                .traits(
                        new RaceTrait("Legacy of Minauros", "You know the mage hand cantrip. When " +
                                "you reach 3rd level, you can cast the Tenser's floating disk spell once with this " +
                                "trait and regain the ability to do so when you finish a short or long rest. When " +
                                "you reach 5th level, you can cast the arcane lock spell once with this trait, " +
                                "requiring no material component, and regain the ability to do so when you finish " +
                                "a long rest. Charisma is your spellcasting ability for these spells.")
                )
                .languages(
                        languageProvider.getLanguage("Infernal")
                )
                .build();

        races.add(infernalMammonTiefling);

        Race infernalMephistophelesTiefling = new Subrace.Builder(tiefling, "Infernal Tiefling: Mephistopheles")
                .abilityScoreModifier(INTELLIGENCE, 1)
                .traits(
                        new RaceTrait("Legacy of Cania", "You know the mage hand cantrip. When you " +
                                "reach 3rd level, you can cast the burning hands spell as a 2nd-level spell once " +
                                "with this trait and regain the ability to do so when you finish a long rest. When " +
                                "you reach 5th level, you can cast the flame blade spell once with this trait and " +
                                "regain the ability to do so when you finish a long rest. Charisma is your " +
                                "spellcasting ability for these spells.")
                )
                .languages(
                        languageProvider.getLanguage("Infernal")
                )
                .build();

        races.add(infernalMephistophelesTiefling);

        Race infernalZarielTiefling = new Subrace.Builder(tiefling, "Infernal Tiefling: Zariel")
                .abilityScoreModifier(STRENGTH, 1)
                .traits(
                        new RaceTrait("Legacy of Avernus", "You know the thaumaturgy cantrip. When " +
                                "you reach 3rd level, you can cast the searing smite spell as a 2nd-level spell " +
                                "once with this trait and regain the ability to do so when you finish a long rest. " +
                                "When you reach 5th level, you can cast the branding smite spell once with this " +
                                "trait and regain the ability to do so when you finish a long rest. Charisma is " +
                                "your spellcasting ability for these spells.")
                )
                .languages(
                        languageProvider.getLanguage("Infernal")
                )
                .build();

        races.add(infernalZarielTiefling);

        Race abyssalTiefling = new Subrace.Builder(tiefling, "Abyssal Tiefling")
                .abilityScoreModifier(CONSTITUTION, 1)
                .traits(
                        new RaceTrait("Abyssal Fortitude", "Abyssal Tieflings have their hit point " +
                                "maximum increase by half of their level (minimum of 1)."),
                        new RaceTrait("Abyssal Arcana", "Each time Abyssal Tieflings finish a long " +
                                "rest, you gain the ability to cast cantrips and spells randomly determined from a " +
                                "short list. At 1st level, they can cast a cantrip. At 3rd level, they can cast a " +
                                "1st-level spell. At 5th level, they can cast a 2nd-level spell. They can cast a " +
                                "cantrip gained from this trait at will, as normal. The 1st-level spell is cast as " +
                                "if a spell slot of 2nd level is used. The 2nd-level spell is cast as if a spell " +
                                "slot of 2nd level is used as well. After Abyssal Tieflings use this feature to " +
                                "cast a spell, they can’t use it again until they finish a long rest. Charisma is " +
                                "their spellcasting ability for them. (This replaces Infernal Legacy)")
                )
                .languages(
                        languageProvider.getLanguage("Abyssal")
                )
                .build();

        races.add(abyssalTiefling);
    }

    public List<Race> getRaces() {
        return races;
    }

    public Race getRace(String name) {
        return races.stream()
                .filter(race -> race.getName()
                        .replace('-', ' ')
                        .replace('_', ' ')
                        .replace(":", "")
                        .equalsIgnoreCase(
                                name.replace('-', ' ')
                                        .replace('_', ' ')
                                        .replace(":", "")
                        )
                        || race.getAliases().stream().anyMatch(alias ->
                            alias.replace('-', ' ')
                                    .replace('_', ' ')
                                    .replace(":", "")
                                    .equalsIgnoreCase(
                                            name.replace('-', ' ')
                                                    .replace('_', ' ')
                                                    .replace(":", "")
                                    )
                        )
                )
                .findFirst()
                .orElse(null);
    }

}
