/*
 * This file is generated by jOOQ.
 */
package net.nyrheim.penandpaper.database.jooq.tables.records;


import net.nyrheim.penandpaper.database.jooq.tables.Character;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CharacterRecord extends UpdatableRecordImpl<CharacterRecord> {

    private static final long serialVersionUID = 654689369;

    /**
     * Setter for <code>nyrheim.character.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>nyrheim.character.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>nyrheim.character.player_id</code>.
     */
    public void setPlayerId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>nyrheim.character.player_id</code>.
     */
    public Integer getPlayerId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>nyrheim.character.first_name</code>.
     */
    public void setFirstName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>nyrheim.character.first_name</code>.
     */
    public String getFirstName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>nyrheim.character.family_name</code>.
     */
    public void setFamilyName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>nyrheim.character.family_name</code>.
     */
    public String getFamilyName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>nyrheim.character.height</code>.
     */
    public void setHeight(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>nyrheim.character.height</code>.
     */
    public String getHeight() {
        return (String) get(4);
    }

    /**
     * Setter for <code>nyrheim.character.weight</code>.
     */
    public void setWeight(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>nyrheim.character.weight</code>.
     */
    public String getWeight() {
        return (String) get(5);
    }

    /**
     * Setter for <code>nyrheim.character.appearance</code>.
     */
    public void setAppearance(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>nyrheim.character.appearance</code>.
     */
    public String getAppearance() {
        return (String) get(6);
    }

    /**
     * Setter for <code>nyrheim.character.presence</code>.
     */
    public void setPresence(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>nyrheim.character.presence</code>.
     */
    public String getPresence() {
        return (String) get(7);
    }

    /**
     * Setter for <code>nyrheim.character.age</code>.
     */
    public void setAge(Integer value) {
        set(8, value);
    }

    /**
     * Getter for <code>nyrheim.character.age</code>.
     */
    public Integer getAge() {
        return (Integer) get(8);
    }

    /**
     * Setter for <code>nyrheim.character.experience</code>.
     */
    public void setExperience(Integer value) {
        set(9, value);
    }

    /**
     * Getter for <code>nyrheim.character.experience</code>.
     */
    public Integer getExperience() {
        return (Integer) get(9);
    }

    /**
     * Setter for <code>nyrheim.character.exhaustion</code>.
     */
    public void setExhaustion(Integer value) {
        set(10, value);
    }

    /**
     * Getter for <code>nyrheim.character.exhaustion</code>.
     */
    public Integer getExhaustion() {
        return (Integer) get(10);
    }

    /**
     * Setter for <code>nyrheim.character.race</code>.
     */
    public void setRace(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>nyrheim.character.race</code>.
     */
    public String getRace() {
        return (String) get(11);
    }

    /**
     * Setter for <code>nyrheim.character.helmet</code>.
     */
    public void setHelmet(byte[] value) {
        set(12, value);
    }

    /**
     * Getter for <code>nyrheim.character.helmet</code>.
     */
    public byte[] getHelmet() {
        return (byte[]) get(12);
    }

    /**
     * Setter for <code>nyrheim.character.chestplate</code>.
     */
    public void setChestplate(byte[] value) {
        set(13, value);
    }

    /**
     * Getter for <code>nyrheim.character.chestplate</code>.
     */
    public byte[] getChestplate() {
        return (byte[]) get(13);
    }

    /**
     * Setter for <code>nyrheim.character.leggings</code>.
     */
    public void setLeggings(byte[] value) {
        set(14, value);
    }

    /**
     * Getter for <code>nyrheim.character.leggings</code>.
     */
    public byte[] getLeggings() {
        return (byte[]) get(14);
    }

    /**
     * Setter for <code>nyrheim.character.boots</code>.
     */
    public void setBoots(byte[] value) {
        set(15, value);
    }

    /**
     * Getter for <code>nyrheim.character.boots</code>.
     */
    public byte[] getBoots() {
        return (byte[]) get(15);
    }

    /**
     * Setter for <code>nyrheim.character.inventory_contents</code>.
     */
    public void setInventoryContents(byte[] value) {
        set(16, value);
    }

    /**
     * Getter for <code>nyrheim.character.inventory_contents</code>.
     */
    public byte[] getInventoryContents() {
        return (byte[]) get(16);
    }

    /**
     * Setter for <code>nyrheim.character.health</code>.
     */
    public void setHealth(Double value) {
        set(17, value);
    }

    /**
     * Getter for <code>nyrheim.character.health</code>.
     */
    public Double getHealth() {
        return (Double) get(17);
    }

    /**
     * Setter for <code>nyrheim.character.food_level</code>.
     */
    public void setFoodLevel(Integer value) {
        set(18, value);
    }

    /**
     * Getter for <code>nyrheim.character.food_level</code>.
     */
    public Integer getFoodLevel() {
        return (Integer) get(18);
    }

    /**
     * Setter for <code>nyrheim.character.saturation</code>.
     */
    public void setSaturation(Double value) {
        set(19, value);
    }

    /**
     * Getter for <code>nyrheim.character.saturation</code>.
     */
    public Double getSaturation() {
        return (Double) get(19);
    }

    /**
     * Setter for <code>nyrheim.character.food_exhaustion</code>.
     */
    public void setFoodExhaustion(Double value) {
        set(20, value);
    }

    /**
     * Getter for <code>nyrheim.character.food_exhaustion</code>.
     */
    public Double getFoodExhaustion() {
        return (Double) get(20);
    }

    /**
     * Setter for <code>nyrheim.character.world</code>.
     */
    public void setWorld(String value) {
        set(21, value);
    }

    /**
     * Getter for <code>nyrheim.character.world</code>.
     */
    public String getWorld() {
        return (String) get(21);
    }

    /**
     * Setter for <code>nyrheim.character.x</code>.
     */
    public void setX(Double value) {
        set(22, value);
    }

    /**
     * Getter for <code>nyrheim.character.x</code>.
     */
    public Double getX() {
        return (Double) get(22);
    }

    /**
     * Setter for <code>nyrheim.character.y</code>.
     */
    public void setY(Double value) {
        set(23, value);
    }

    /**
     * Getter for <code>nyrheim.character.y</code>.
     */
    public Double getY() {
        return (Double) get(23);
    }

    /**
     * Setter for <code>nyrheim.character.z</code>.
     */
    public void setZ(Double value) {
        set(24, value);
    }

    /**
     * Getter for <code>nyrheim.character.z</code>.
     */
    public Double getZ() {
        return (Double) get(24);
    }

    /**
     * Setter for <code>nyrheim.character.pitch</code>.
     */
    public void setPitch(Double value) {
        set(25, value);
    }

    /**
     * Getter for <code>nyrheim.character.pitch</code>.
     */
    public Double getPitch() {
        return (Double) get(25);
    }

    /**
     * Setter for <code>nyrheim.character.yaw</code>.
     */
    public void setYaw(Double value) {
        set(26, value);
    }

    /**
     * Getter for <code>nyrheim.character.yaw</code>.
     */
    public Double getYaw() {
        return (Double) get(26);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CharacterRecord
     */
    public CharacterRecord() {
        super(Character.CHARACTER);
    }

    /**
     * Create a detached, initialised CharacterRecord
     */
    public CharacterRecord(Integer id, Integer playerId, String firstName, String familyName, String height, String weight, String appearance, String presence, Integer age, Integer experience, Integer exhaustion, String race, byte[] helmet, byte[] chestplate, byte[] leggings, byte[] boots, byte[] inventoryContents, Double health, Integer foodLevel, Double saturation, Double foodExhaustion, String world, Double x, Double y, Double z, Double pitch, Double yaw) {
        super(Character.CHARACTER);

        set(0, id);
        set(1, playerId);
        set(2, firstName);
        set(3, familyName);
        set(4, height);
        set(5, weight);
        set(6, appearance);
        set(7, presence);
        set(8, age);
        set(9, experience);
        set(10, exhaustion);
        set(11, race);
        set(12, helmet);
        set(13, chestplate);
        set(14, leggings);
        set(15, boots);
        set(16, inventoryContents);
        set(17, health);
        set(18, foodLevel);
        set(19, saturation);
        set(20, foodExhaustion);
        set(21, world);
        set(22, x);
        set(23, y);
        set(24, z);
        set(25, pitch);
        set(26, yaw);
    }
}
