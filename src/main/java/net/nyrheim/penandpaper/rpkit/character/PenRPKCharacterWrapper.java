package net.nyrheim.penandpaper.rpkit.character;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.CharacterId;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import net.nyrheim.penandpaper.rpkit.player.PenRPKPlayerWrapper;
import net.nyrheim.penandpaper.rpkit.profile.PenRPKMinecraftProfileWrapper;
import net.nyrheim.penandpaper.rpkit.profile.PenRPKProfileWrapper;
import net.nyrheim.penandpaper.rpkit.race.PenRPKRaceWrapper;
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

public final class PenRPKCharacterWrapper implements RPKCharacter {

    private final PenAndPaper plugin;
    private final PenCharacter pnpCharacter;

    public PenRPKCharacterWrapper(PenAndPaper plugin, PenCharacter pnpCharacter) {
        this.plugin = plugin;
        this.pnpCharacter = pnpCharacter;
    }

    public PenCharacter getPnPCharacter() {
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
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(pnpCharacter.getPlayerId());
        return new PenRPKMinecraftProfileWrapper(plugin, penPlayer);
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
        pnpCharacter.setName(name);
    }

    @Nullable
    @Override
    public RPKPlayer getPlayer() {
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(pnpCharacter.getPlayerId());
        return new PenRPKPlayerWrapper(plugin, penPlayer);
    }

    @Override
    public void setPlayer(@Nullable RPKPlayer player) {

    }

    @Nullable
    @Override
    public RPKProfile getProfile() {
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(pnpCharacter.getPlayerId());
        return new PenRPKProfileWrapper(plugin, penPlayer);
    }

    @Override
    public void setProfile(@Nullable RPKProfile profile) {

    }

    @Nullable
    @Override
    public RPKRace getRace() {
        return new PenRPKRaceWrapper(pnpCharacter.getRace());
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
