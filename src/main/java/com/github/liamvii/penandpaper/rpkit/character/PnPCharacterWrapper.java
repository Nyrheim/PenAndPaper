package com.github.liamvii.penandpaper.rpkit.character;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.CharacterId;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.database.table.PlayerTable;
import com.github.liamvii.penandpaper.player.PenPlayer;
import com.github.liamvii.penandpaper.rpkit.player.PnPPlayerWrapper;
import com.github.liamvii.penandpaper.rpkit.profile.PnPMinecraftProfileWrapper;
import com.github.liamvii.penandpaper.rpkit.profile.PnPProfileWrapper;
import com.github.liamvii.penandpaper.rpkit.race.PnPRaceWrapper;
import com.rpkit.characters.bukkit.character.RPKCharacter;
import com.rpkit.characters.bukkit.gender.RPKGender;
import com.rpkit.characters.bukkit.race.RPKRace;
import com.rpkit.players.bukkit.player.RPKPlayer;
import com.rpkit.players.bukkit.profile.RPKMinecraftProfile;
import com.rpkit.players.bukkit.profile.RPKProfile;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PnPCharacterWrapper implements RPKCharacter {

    private final Pen plugin;
    private final PlayerCharacter pnpCharacter;

    public PnPCharacterWrapper(Pen plugin, PlayerCharacter pnpCharacter) {
        this.plugin = plugin;
        this.pnpCharacter = pnpCharacter;
    }

    public PlayerCharacter getPnPCharacter() {
        return pnpCharacter;
    }

    @Override
    public int getAge() {
        return pnpCharacter.getAge();
    }

    @Override
    public void setAge(int age) {
        pnpCharacter.setAge(age);
    }

    @Nullable
    @Override
    public ItemStack getBoots() {
        return pnpCharacter.getBoots();
    }

    @Override
    public void setBoots(@Nullable ItemStack boots) {
        pnpCharacter.setBoots(boots);
    }

    @Nullable
    @Override
    public ItemStack getChestplate() {
        return pnpCharacter.getChestplate();
    }

    @Override
    public void setChestplate(@Nullable ItemStack chestplate) {
        pnpCharacter.setChestplate(chestplate);
    }

    @NotNull
    @Override
    public String getDescription() {
        return pnpCharacter.getAppearance();
    }

    @Override
    public void setDescription(@NotNull String description) {
        pnpCharacter.setAppearance(description);
    }

    @Override
    public int getFoodLevel() {
        return pnpCharacter.getFoodLevel();
    }

    @Override
    public void setFoodLevel(int foodLevel) {
        pnpCharacter.setFoodLevel(foodLevel);
    }

    @Nullable
    @Override
    public RPKGender getGender() {
        return null;
    }

    @Override
    public void setGender(@Nullable RPKGender rpkGender) {

    }

    @Override
    public double getHealth() {
        return pnpCharacter.getHealth();
    }

    @Override
    public void setHealth(double health) {
        pnpCharacter.setHealth(health);
    }

    @Nullable
    @Override
    public ItemStack getHelmet() {
        return pnpCharacter.getHelmet();
    }

    @Override
    public void setHelmet(@Nullable ItemStack helmet) {
        pnpCharacter.setHelmet(helmet);
    }

    @NotNull
    @Override
    public ItemStack[] getInventoryContents() {
        return pnpCharacter.getInventoryContents();
    }

    @Override
    public void setInventoryContents(ItemStack @NotNull [] inventoryContents) {
        pnpCharacter.setInventoryContents(inventoryContents);
    }

    @Override
    public boolean isAgeHidden() {
        return false;
    }

    @Override
    public void setAgeHidden(boolean ageHidden) {

    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public void setDead(boolean dead) {

    }

    @Override
    public boolean isDescriptionHidden() {
        return false;
    }

    @Override
    public void setDescriptionHidden(boolean descriptionHidden) {

    }

    @Override
    public boolean isGenderHidden() {
        return true;
    }

    @Override
    public void setGenderHidden(boolean genderHidden) {

    }

    @Override
    public boolean isNameHidden() {
        return false;
    }

    @Override
    public void setNameHidden(boolean nameHidden) {

    }

    @Override
    public boolean isPlayerHidden() {
        return false;
    }

    @Override
    public void setPlayerHidden(boolean playerHidden) {

    }

    @Override
    public boolean isProfileHidden() {
        return false;
    }

    @Override
    public void setProfileHidden(boolean profileHidden) {

    }

    @Override
    public boolean isRaceHidden() {
        return false;
    }

    @Override
    public void setRaceHidden(boolean raceHidden) {

    }

    @Nullable
    @Override
    public ItemStack getLeggings() {
        return pnpCharacter.getLeggings();
    }

    @Override
    public void setLeggings(@Nullable ItemStack leggings) {
        pnpCharacter.setLeggings(leggings);
    }

    @NotNull
    @Override
    public Location getLocation() {
        return pnpCharacter.getLocation();
    }

    @Override
    public void setLocation(@NotNull Location location) {
        pnpCharacter.setLocation(location);
    }

    @Override
    public int getMana() {
        return 0;
    }

    @Override
    public void setMana(int mana) {

    }

    @Override
    public double getMaxHealth() {
        return 20;
    }

    @Override
    public void setMaxHealth(double maxHealth) {

    }

    @Override
    public int getMaxMana() {
        return 0;
    }

    @Override
    public void setMaxMana(int maxMana) {

    }

    @Nullable
    @Override
    public RPKMinecraftProfile getMinecraftProfile() {
        PlayerTable playerTable = plugin.getDatabase().getTable(PlayerTable.class);
        PenPlayer penPlayer = playerTable.get(pnpCharacter.getPlayerId());
        return new PnPMinecraftProfileWrapper(plugin, penPlayer);
    }

    @Override
    public void setMinecraftProfile(@Nullable RPKMinecraftProfile minecraftProfile) {

    }

    @NotNull
    @Override
    public String getName() {
        return pnpCharacter.getName();
    }

    @Override
    public void setName(@NotNull String name) {
        String[] nameParts = name.split(" ");
        if (nameParts.length > 0) {
            pnpCharacter.setFirstName(nameParts[0]);
        }
        if (nameParts.length > 1) {
            pnpCharacter.setFamilyName(nameParts[1]);
        }
    }

    @Nullable
    @Override
    public RPKPlayer getPlayer() {
        PlayerTable playerTable = plugin.getDatabase().getTable(PlayerTable.class);
        PenPlayer penPlayer = playerTable.get(pnpCharacter.getPlayerId());
        return new PnPPlayerWrapper(plugin, penPlayer);
    }

    @Override
    public void setPlayer(@Nullable RPKPlayer player) {

    }

    @Nullable
    @Override
    public RPKProfile getProfile() {
        PlayerTable playerTable = plugin.getDatabase().getTable(PlayerTable.class);
        PenPlayer penPlayer = playerTable.get(pnpCharacter.getPlayerId());
        return new PnPProfileWrapper(plugin, penPlayer);
    }

    @Override
    public void setProfile(@Nullable RPKProfile profile) {

    }

    @Nullable
    @Override
    public RPKRace getRace() {
        return new PnPRaceWrapper(pnpCharacter.getRace());
    }

    @Override
    public void setRace(@Nullable RPKRace race) {

    }

    @Override
    public int getThirstLevel() {
        return 0;
    }

    @Override
    public void setThirstLevel(int thirstLevel) {

    }

    @Override
    public void showCharacterCard(@NotNull RPKPlayer player) {

    }

    @Override
    public void showCharacterCard(@NotNull RPKMinecraftProfile minecraftProfile) {

    }

    @Override
    public int getId() {
        return pnpCharacter.getId().getValue();
    }

    @Override
    public void setId(int id) {
        pnpCharacter.setId(new CharacterId(id));
    }
}
