package net.nyrheim.penandpaper.character;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.ability.Ability;
import net.nyrheim.penandpaper.clazz.CharacterClass;
import net.nyrheim.penandpaper.clazz.PenClass;
import net.nyrheim.penandpaper.experience.ExperienceLookupTable;
import net.nyrheim.penandpaper.player.PlayerId;
import net.nyrheim.penandpaper.race.Race;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static net.nyrheim.penandpaper.ability.Ability.*;
import static net.nyrheim.penandpaper.ability.AbilityModifierLookupTable.lookupModifier;

public final class PenCharacter {

    public static final int MAX_CLASSES = 4;

    private PenAndPaper plugin;

    private CharacterId id;
    private final PlayerId playerId;
    private String name;
    private String height;
    private String weight;
    private String appearance;
    private String presence;
    private int age;
    private int experience;
    private int exhaustion;
    private final Map<Ability, Integer> abilityScores = new EnumMap<>(Ability.class);
    private final Map<Ability, Integer> tempScores = new EnumMap<>(Ability.class);
    private final Map<Ability, Integer> abilityScoreChoices = new EnumMap<>(Ability.class);
    private PenClass firstClass;
    private final Map<PenClass, CharacterClass> classes = new HashMap<>();
    private Race race;
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;
    private ItemStack[] inventoryContents;
    private double health;
    private int foodLevel;
    private float saturation;
    private float foodExhaustion;
    private Location location;
    private int hp;
    private int tempHP;

    public PenCharacter(
            PenAndPaper plugin,
            CharacterId id,
            PlayerId playerId,
            String name,
            String height,
            String weight,
            String appearance,
            String presence,
            int age,
            int experience,
            int exhaustion,
            Map<Ability, Integer> abilityScores,
            Map<Ability, Integer> tempScores,
            Map<Ability, Integer> abilityScoreChoices,
            PenClass firstClass,
            List<CharacterClass> classes,
            Race race,
            ItemStack helmet,
            ItemStack chestplate,
            ItemStack leggings,
            ItemStack boots,
            ItemStack[] inventoryContents,
            double health,
            int foodLevel,
            float saturation,
            float foodExhaustion,
            Location location,
            int hp,
            int tempHP
    ) {
        this.id = id;
        this.plugin = plugin;
        this.playerId = playerId;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.appearance = appearance;
        this.presence = presence;
        this.age = age;
        this.experience = experience;
        this.exhaustion = exhaustion;
        this.abilityScores.putAll(abilityScores);
        this.tempScores.putAll(tempScores);
        this.abilityScoreChoices.putAll(abilityScoreChoices);
        this.firstClass = firstClass;
        this.classes.putAll(classes.stream()
                .collect(Collectors.toMap(CharacterClass::getClazz, characterClass -> characterClass)));
        this.race = race;
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.inventoryContents = inventoryContents;
        this.health = health;
        this.foodLevel = foodLevel;
        this.saturation = saturation;
        this.foodExhaustion = foodExhaustion;
        this.location = location;
        this.hp = hp;
        this.tempHP = tempHP;
    }

    public PenCharacter(
            PenAndPaper plugin,
            PlayerId playerId
    ) {
        this(
                plugin,
                new CharacterId(-1),
                playerId,
                "",
                "",
                "",
                "",
                "",
                20,
                0,
                0,
                new EnumMap<>(Ability.class),
                new EnumMap<>(Ability.class),
                new EnumMap<>(Ability.class),
                null,
                new ArrayList<>(),
                null,
                null,
                null,
                null,
                null,
                new ItemStack[36],
                20,
                20,
                5,
                0,
                plugin.getServer().getWorlds().get(0).getSpawnLocation(),
                1,
                0
        );
    }

    public CharacterId getId() {
        return id;
    }

    public void setId(CharacterId id) {
        this.id = id;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return ExperienceLookupTable.getLevelAtExperience(getExperience());
    }

    public int getExhaustion() {
        return exhaustion;
    }

    public void setExhaustion(int exhaustion) {
        this.exhaustion = min(max(exhaustion, 0), 100);
    }

    public int getAbilityScore(Ability ability) {
        return abilityScores.getOrDefault(ability, 0);
    }

    public void setAbilityScore(Ability ability, int score) {
        abilityScores.put(ability, score);
    }

    public int getTempScore(Ability ability) {
        return tempScores.getOrDefault(ability, 0);
    }

    public void setTempScore(Ability ability, int score) {
        tempScores.put(ability, score);
    }

    public int getAbilityScoreChoice(Ability ability) {
        return abilityScoreChoices.getOrDefault(ability, 0);
    }

    public void setAbilityScoreChoice(Ability ability, int score) {
        abilityScoreChoices.put(ability, score);
    }

    public int getModifier(Ability ability) {
        Race race = getRace();
        return lookupModifier(
                getAbilityScore(ability)
                        + getTempScore(ability)
                        + (race != null ? race.getAbilityScoreModifier(ability) : 0)
        );
    }

    @Nullable
    public Race getRace() {
        return race;
    }

    public void setRace(@Nullable Race race) {
        this.race = race;
    }

    public ItemStack getHelmet() {
        return helmet;
    }

    public void setHelmet(ItemStack helmet) {
        this.helmet = helmet;
    }

    public ItemStack getChestplate() {
        return chestplate;
    }

    public void setChestplate(ItemStack chestplate) {
        this.chestplate = chestplate;
    }

    public ItemStack getLeggings() {
        return leggings;
    }

    public void setLeggings(ItemStack leggings) {
        this.leggings = leggings;
    }

    public ItemStack getBoots() {
        return boots;
    }

    public void setBoots(ItemStack boots) {
        this.boots = boots;
    }

    public ItemStack[] getInventoryContents() {
        return inventoryContents;
    }

    public void setInventoryContents(ItemStack[] inventoryContents) {
        this.inventoryContents = inventoryContents;
    }

    public List<CharacterClass> classes() {
        return new ArrayList<>(classes.values());
    }

    public CharacterClass clazz(PenClass clazz) {
        return classes.get(clazz);
    }

    public PenClass getFirstClass() {
        return firstClass;
    }

    public void addClass(PenClass clazz) {
        if (classes.size() < MAX_CLASSES) {
            if (classes.isEmpty()) {
                firstClass = clazz;
            }
            classes.put(clazz, new CharacterClass(clazz));
        }
    }

    public void removeClass(PenClass clazz) {
        classes.remove(clazz);
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getFoodLevel() {
        return foodLevel;
    }

    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }

    public float getSaturation() {
        return saturation;
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
    }

    public float getFoodExhaustion() {
        return foodExhaustion;
    }

    public void setFoodExhaustion(float foodExhaustion) {
        this.foodExhaustion = foodExhaustion;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMaxHP() {
        return (getFirstClass() != null ? getFirstClass().getBaseHP() : 1)
                + (getModifier(CONSTITUTION) * getLevel())
                + classes().stream()
                .map(clazz -> (clazz.getLevel() - (clazz.getClazz() == getFirstClass() ? 1 : 0)) * clazz.getClazz().getLevelHP())
                .reduce(0, Integer::sum);
    }

    public int getHP() {
        return hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public int getTempHP() {
        return tempHP;
    }

    public void setTempHP(int tempHP) {
        this.tempHP = tempHP;
    }
}
