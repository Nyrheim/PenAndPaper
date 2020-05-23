package com.github.liamvii.penandpaper.character;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.ability.Ability;
import com.github.liamvii.penandpaper.ability.AbilityModifierLookupTable;
import com.github.liamvii.penandpaper.clazz.CharacterClass;
import com.github.liamvii.penandpaper.clazz.DnDClass;
import com.github.liamvii.penandpaper.conversations.StartCreate;
import com.github.liamvii.penandpaper.player.PlayerId;
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
    private DnDClass firstClass;
    private final Map<DnDClass, CharacterClass> classes = new HashMap<>();
    private String race;
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;
    private ItemStack[] inventoryContents;

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
            DnDClass firstClass,
            List<CharacterClass> classes,
            String race,
            ItemStack helmet,
            ItemStack chestplate,
            ItemStack leggings,
            ItemStack boots,
            ItemStack[] inventoryContents
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
        this.firstClass = firstClass;
        this.classes.putAll(classes.stream()
                .collect(Collectors.toMap(CharacterClass::getClazz, characterClass -> characterClass)));
        this.race = race;
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.inventoryContents = inventoryContents;
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
            DnDClass firstClass,
            List<CharacterClass> classes,
            String race,
            ItemStack helmet,
            ItemStack chestplate,
            ItemStack leggings,
            ItemStack boots,
            ItemStack[] inventoryContents
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
                firstClass,
                classes,
                race,
                helmet,
                chestplate,
                leggings,
                boots,
                inventoryContents
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

    public int getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getAppearance() {
        return appearance;
    }

    public String getPresence() {
        return presence;
    }

    public int getExperience() {
        return experience;
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
        return abilityScores.getOrDefault(ability, 0);
    }

    public void setTempScore(Ability ability, int score) {
        tempScores.put(ability, score);
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

}
