/*
 * This file is generated by jOOQ.
 */
package com.github.liamvii.penandpaper.database.jooq.nyrheim.tables;


import com.github.liamvii.penandpaper.database.jooq.nyrheim.Keys;
import com.github.liamvii.penandpaper.database.jooq.nyrheim.Nyrheim;
import com.github.liamvii.penandpaper.database.jooq.nyrheim.tables.records.ActiveCharacterRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
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
public class ActiveCharacter extends TableImpl<ActiveCharacterRecord> {

    private static final long serialVersionUID = 1376871286;

    /**
     * The reference instance of <code>nyrheim.active_character</code>
     */
    public static final ActiveCharacter ACTIVE_CHARACTER = new ActiveCharacter();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ActiveCharacterRecord> getRecordType() {
        return ActiveCharacterRecord.class;
    }

    /**
     * The column <code>nyrheim.active_character.player_uuid</code>.
     */
    public final TableField<ActiveCharacterRecord, String> PLAYER_UUID = createField(DSL.name("player_uuid"), org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "");

    /**
     * The column <code>nyrheim.active_character.character_id</code>.
     */
    public final TableField<ActiveCharacterRecord, Integer> CHARACTER_ID = createField(DSL.name("character_id"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>nyrheim.active_character</code> table reference
     */
    public ActiveCharacter() {
        this(DSL.name("active_character"), null);
    }

    /**
     * Create an aliased <code>nyrheim.active_character</code> table reference
     */
    public ActiveCharacter(String alias) {
        this(DSL.name(alias), ACTIVE_CHARACTER);
    }

    /**
     * Create an aliased <code>nyrheim.active_character</code> table reference
     */
    public ActiveCharacter(Name alias) {
        this(alias, ACTIVE_CHARACTER);
    }

    private ActiveCharacter(Name alias, Table<ActiveCharacterRecord> aliased) {
        this(alias, aliased, null);
    }

    private ActiveCharacter(Name alias, Table<ActiveCharacterRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> ActiveCharacter(Table<O> child, ForeignKey<O, ActiveCharacterRecord> key) {
        super(child, key, ACTIVE_CHARACTER);
    }

    @Override
    public Schema getSchema() {
        return Nyrheim.NYRHEIM;
    }

    @Override
    public UniqueKey<ActiveCharacterRecord> getPrimaryKey() {
        return Keys.KEY_ACTIVE_CHARACTER_PRIMARY;
    }

    @Override
    public List<UniqueKey<ActiveCharacterRecord>> getKeys() {
        return Arrays.<UniqueKey<ActiveCharacterRecord>>asList(Keys.KEY_ACTIVE_CHARACTER_PRIMARY);
    }

    @Override
    public List<ForeignKey<ActiveCharacterRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ActiveCharacterRecord, ?>>asList(Keys.ACTIVE_CHARACTER_CHARACTER_ID_FK);
    }

    public Character character() {
        return new Character(this, Keys.ACTIVE_CHARACTER_CHARACTER_ID_FK);
    }

    @Override
    public ActiveCharacter as(String alias) {
        return new ActiveCharacter(DSL.name(alias), this);
    }

    @Override
    public ActiveCharacter as(Name alias) {
        return new ActiveCharacter(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ActiveCharacter rename(String name) {
        return new ActiveCharacter(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ActiveCharacter rename(Name name) {
        return new ActiveCharacter(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<String, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
