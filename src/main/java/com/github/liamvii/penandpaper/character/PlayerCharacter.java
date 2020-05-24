package com.github.liamvii.penandpaper.character;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.ability.Ability;
import com.github.liamvii.penandpaper.ability.AbilityModifierLookupTable;
import com.github.liamvii.penandpaper.clazz.CharacterClass;
import com.github.liamvii.penandpaper.clazz.DnDClass;
import com.github.liamvii.penandpaper.conversations.StartCreate;
import com.github.liamvii.penandpaper.experience.ExperienceLookupTable;
import com.github.liamvii.penandpaper.player.PlayerId;
import org.bukkit.Location;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public final class PlayerCharacter {

    public static final int MAX_CLASSES = 4;

    private Pen plugin;

    private CharacterId id;
    private final PlayerId playerUUID;
    private String firstName;
    private String familyName;
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
    private DnDClass firstClass;
    private final Map<DnDClass, CharacterClass> classes = new HashMap<>();
    private String race;
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

    public PlayerCharacter(
            Pen plugin,
            CharacterId id,
            PlayerId playerUUID,
            String firstName,
            String familyName,
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
            DnDClass firstClass,
            List<CharacterClass> classes,
            String race,
            ItemStack helmet,
            ItemStack chestplate,
            ItemStack leggings,
            ItemStack boots,
            ItemStack[] inventoryContents,
            double health,
            int foodLevel,
            float saturation,
            float foodExhaustion,
            Location location
    ) {
        this.id = id;
        this.plugin = plugin;
        this.playerUUID = playerUUID;
        this.firstName = firstName;
        this.familyName = familyName;
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
    }

    public PlayerCharacter(
            Pen plugin,
            CharacterId id,
            Player player,
            String firstName,
            String familyName,
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
            DnDClass firstClass,
            List<CharacterClass> classes,
            String race,
            ItemStack helmet,
            ItemStack chestplate,
            ItemStack leggings,
            ItemStack boots,
            ItemStack[] inventoryContents,
            double health,
            int foodLevel,
            float saturation,
            float foodExhaustion,
            Location location
    ) {
        this(
                plugin,
                id,
                new PlayerId(player.getUniqueId()),
                firstName,
                familyName,
                height,
                weight,
                appearance,
                presence,
                age,
                experience,
                exhaustion,
                abilityScores,
                tempScores,
                abilityScoreChoices,
                firstClass,
                classes,
                race,
                helmet,
                chestplate,
                leggings,
                boots,
                inventoryContents,
                health,
                foodLevel,
                saturation,
                foodExhaustion,
                location
        );
    }

    public PlayerCharacter(
            Pen plugin,
            Player player
    ) {
        this(
                plugin,
                new CharacterId(-1),
                new PlayerId(player),
                "",
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
                "",
                null,
                null,
                null,
                null,
                new ItemStack[36],
                20,
                20,
                5,
                0,
                plugin.getServer().getWorlds().get(0).getSpawnLocation()
        );
    }

    private void createConversation(Player player) {
        ConversationFactory cf = new ConversationFactory(plugin);
        Conversation conv = cf.withFirstPrompt(new StartCreate()).withLocalEcho(false).buildConversation(player);
        conv.begin();
    }

    public CharacterId getId() {
        return id;
    }

    public void setId(CharacterId id) {
        this.id = id;
    }

    public PlayerId getPlayerId() {
        return playerUUID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getName() {
        return getFirstName() + " " + getFamilyName();
    }

    public int getAge() {
        return age;
    }

    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public void setFamilyName(String fName) {
        this.familyName = fName;
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
        this.exhaustion = exhaustion;
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
        return AbilityModifierLookupTable.lookupModifier(getAbilityScore(ability) + getTempScore(ability));
    }

    public String getRace() {
        return race;
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

    public CharacterClass clazz(DnDClass clazz) {
        return classes.get(clazz);
    }

    public DnDClass getFirstClass() {
        return firstClass;
    }

    public void addClass(DnDClass clazz) {
        if (classes.size() < MAX_CLASSES) {
            if (classes.isEmpty()) {
                firstClass = clazz;
            }
            classes.put(clazz, new CharacterClass(clazz));
        }
    }

    public void removeClass(DnDClass clazz) {
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
}
