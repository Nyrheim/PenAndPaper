/*
 * This file is generated by jOOQ.
 */
package net.nyrheim.penandpaper.database.jooq.tables;


import java.util.Arrays;
import java.util.List;

import net.nyrheim.penandpaper.database.jooq.Keys;
import net.nyrheim.penandpaper.database.jooq.Nyrheim;
import net.nyrheim.penandpaper.database.jooq.tables.records.CharacterRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Character extends TableImpl<CharacterRecord> {

    private static final long serialVersionUID = 219093704;

    /**
     * The reference instance of <code>nyrheim.character</code>
     */
    public static final Character CHARACTER = new Character();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CharacterRecord> getRecordType() {
        return CharacterRecord.class;
    }

    /**
     * The column <code>nyrheim.character.id</code>.
     */
    public final TableField<CharacterRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>nyrheim.character.player_id</code>.
     */
    public final TableField<CharacterRecord, Integer> PLAYER_ID = createField(DSL.name("player_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.first_name</code>.
     */
    public final TableField<CharacterRecord, String> FIRST_NAME = createField(DSL.name("first_name"), org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.family_name</code>.
     */
    public final TableField<CharacterRecord, String> FAMILY_NAME = createField(DSL.name("family_name"), org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.height</code>.
     */
    public final TableField<CharacterRecord, String> HEIGHT = createField(DSL.name("height"), org.jooq.impl.SQLDataType.VARCHAR(16).nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.weight</code>.
     */
    public final TableField<CharacterRecord, String> WEIGHT = createField(DSL.name("weight"), org.jooq.impl.SQLDataType.VARCHAR(16).nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.appearance</code>.
     */
    public final TableField<CharacterRecord, String> APPEARANCE = createField(DSL.name("appearance"), org.jooq.impl.SQLDataType.VARCHAR(4096).nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.presence</code>.
     */
    public final TableField<CharacterRecord, String> PRESENCE = createField(DSL.name("presence"), org.jooq.impl.SQLDataType.VARCHAR(4096).nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.age</code>.
     */
    public final TableField<CharacterRecord, Integer> AGE = createField(DSL.name("age"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.experience</code>.
     */
    public final TableField<CharacterRecord, Integer> EXPERIENCE = createField(DSL.name("experience"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.exhaustion</code>.
     */
    public final TableField<CharacterRecord, Integer> EXHAUSTION = createField(DSL.name("exhaustion"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.race</code>.
     */
    public final TableField<CharacterRecord, String> RACE = createField(DSL.name("race"), org.jooq.impl.SQLDataType.VARCHAR(64), this, "");

    /**
     * The column <code>nyrheim.character.helmet</code>.
     */
    public final TableField<CharacterRecord, byte[]> HELMET = createField(DSL.name("helmet"), org.jooq.impl.SQLDataType.BLOB, this, "");

    /**
     * The column <code>nyrheim.character.chestplate</code>.
     */
    public final TableField<CharacterRecord, byte[]> CHESTPLATE = createField(DSL.name("chestplate"), org.jooq.impl.SQLDataType.BLOB, this, "");

    /**
     * The column <code>nyrheim.character.leggings</code>.
     */
    public final TableField<CharacterRecord, byte[]> LEGGINGS = createField(DSL.name("leggings"), org.jooq.impl.SQLDataType.BLOB, this, "");

    /**
     * The column <code>nyrheim.character.boots</code>.
     */
    public final TableField<CharacterRecord, byte[]> BOOTS = createField(DSL.name("boots"), org.jooq.impl.SQLDataType.BLOB, this, "");

    /**
     * The column <code>nyrheim.character.inventory_contents</code>.
     */
    public final TableField<CharacterRecord, byte[]> INVENTORY_CONTENTS = createField(DSL.name("inventory_contents"), org.jooq.impl.SQLDataType.BLOB, this, "");

    /**
     * The column <code>nyrheim.character.health</code>.
     */
    public final TableField<CharacterRecord, Double> HEALTH = createField(DSL.name("health"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.food_level</code>.
     */
    public final TableField<CharacterRecord, Integer> FOOD_LEVEL = createField(DSL.name("food_level"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.saturation</code>.
     */
    public final TableField<CharacterRecord, Double> SATURATION = createField(DSL.name("saturation"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.food_exhaustion</code>.
     */
    public final TableField<CharacterRecord, Double> FOOD_EXHAUSTION = createField(DSL.name("food_exhaustion"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.world</code>.
     */
    public final TableField<CharacterRecord, String> WORLD = createField(DSL.name("world"), org.jooq.impl.SQLDataType.VARCHAR(256).nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.x</code>.
     */
    public final TableField<CharacterRecord, Double> X = createField(DSL.name("x"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.y</code>.
     */
    public final TableField<CharacterRecord, Double> Y = createField(DSL.name("y"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.z</code>.
     */
    public final TableField<CharacterRecord, Double> Z = createField(DSL.name("z"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>nyrheim.character.pitch</code>.
     */
    public final TableField<CharacterRecord, Double> PITCH = createField(DSL.name("pitch"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.yaw</code>.
     */
    public final TableField<CharacterRecord, Double> YAW = createField(DSL.name("yaw"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.hp</code>.
     */
    public final TableField<CharacterRecord, Integer> HP = createField(DSL.name("hp"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>nyrheim.character.temp_hp</code>.
     */
    public final TableField<CharacterRecord, Integer> TEMP_HP = createField(DSL.name("temp_hp"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>nyrheim.character</code> table reference
     */
    public Character() {
        this(DSL.name("character"), null);
    }

    /**
     * Create an aliased <code>nyrheim.character</code> table reference
     */
    public Character(String alias) {
        this(DSL.name(alias), CHARACTER);
    }

    /**
     * Create an aliased <code>nyrheim.character</code> table reference
     */
    public Character(Name alias) {
        this(alias, CHARACTER);
    }

    private Character(Name alias, Table<CharacterRecord> aliased) {
        this(alias, aliased, null);
    }

    private Character(Name alias, Table<CharacterRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Character(Table<O> child, ForeignKey<O, CharacterRecord> key) {
        super(child, key, CHARACTER);
    }

    @Override
    public Schema getSchema() {
        return Nyrheim.NYRHEIM;
    }

    @Override
    public Identity<CharacterRecord, Integer> getIdentity() {
        return Keys.IDENTITY_CHARACTER;
    }

    @Override
    public UniqueKey<CharacterRecord> getPrimaryKey() {
        return Keys.KEY_CHARACTER_PRIMARY;
    }

    @Override
    public List<UniqueKey<CharacterRecord>> getKeys() {
        return Arrays.<UniqueKey<CharacterRecord>>asList(Keys.KEY_CHARACTER_PRIMARY);
    }

    @Override
    public Character as(String alias) {
        return new Character(DSL.name(alias), this);
    }

    @Override
    public Character as(Name alias) {
        return new Character(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Character rename(String name) {
        return new Character(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Character rename(Name name) {
        return new Character(name, null);
    }
}
