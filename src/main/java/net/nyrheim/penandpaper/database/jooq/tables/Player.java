/*
 * This file is generated by jOOQ.
 */
package net.nyrheim.penandpaper.database.jooq.tables;


import java.util.Arrays;
import java.util.List;

import net.nyrheim.penandpaper.database.jooq.Keys;
import net.nyrheim.penandpaper.database.jooq.Nyrheim;
import net.nyrheim.penandpaper.database.jooq.tables.records.PlayerRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
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
public class Player extends TableImpl<PlayerRecord> {

    private static final long serialVersionUID = 699787148;

    /**
     * The reference instance of <code>nyrheim.player</code>
     */
    public static final Player PLAYER = new Player();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PlayerRecord> getRecordType() {
        return PlayerRecord.class;
    }

    /**
     * The column <code>nyrheim.player.id</code>.
     */
    public final TableField<PlayerRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>nyrheim.player.player_uuid</code>.
     */
    public final TableField<PlayerRecord, String> PLAYER_UUID = createField(DSL.name("player_uuid"), org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "");

    /**
     * Create a <code>nyrheim.player</code> table reference
     */
    public Player() {
        this(DSL.name("player"), null);
    }

    /**
     * Create an aliased <code>nyrheim.player</code> table reference
     */
    public Player(String alias) {
        this(DSL.name(alias), PLAYER);
    }

    /**
     * Create an aliased <code>nyrheim.player</code> table reference
     */
    public Player(Name alias) {
        this(alias, PLAYER);
    }

    private Player(Name alias, Table<PlayerRecord> aliased) {
        this(alias, aliased, null);
    }

    private Player(Name alias, Table<PlayerRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Player(Table<O> child, ForeignKey<O, PlayerRecord> key) {
        super(child, key, PLAYER);
    }

    @Override
    public Schema getSchema() {
        return Nyrheim.NYRHEIM;
    }

    @Override
    public Identity<PlayerRecord, Integer> getIdentity() {
        return Keys.IDENTITY_PLAYER;
    }

    @Override
    public UniqueKey<PlayerRecord> getPrimaryKey() {
        return Keys.KEY_PLAYER_PRIMARY;
    }

    @Override
    public List<UniqueKey<PlayerRecord>> getKeys() {
        return Arrays.<UniqueKey<PlayerRecord>>asList(Keys.KEY_PLAYER_PRIMARY);
    }

    @Override
    public Player as(String alias) {
        return new Player(DSL.name(alias), this);
    }

    @Override
    public Player as(Name alias) {
        return new Player(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Player rename(String name) {
        return new Player(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Player rename(Name name) {
        return new Player(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
