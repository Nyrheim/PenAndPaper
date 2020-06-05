/*
 * This file is generated by jOOQ.
 */
package net.nyrheim.penandpaper.database.jooq.tables.records;


import net.nyrheim.penandpaper.database.jooq.tables.Player;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PlayerRecord extends UpdatableRecordImpl<PlayerRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 1318942653;

    /**
     * Setter for <code>nyrheim.player.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>nyrheim.player.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>nyrheim.player.player_uuid</code>.
     */
    public void setPlayerUuid(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>nyrheim.player.player_uuid</code>.
     */
    public String getPlayerUuid() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Player.PLAYER.ID;
    }

    @Override
    public Field<String> field2() {
        return Player.PLAYER.PLAYER_UUID;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getPlayerUuid();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getPlayerUuid();
    }

    @Override
    public PlayerRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public PlayerRecord value2(String value) {
        setPlayerUuid(value);
        return this;
    }

    @Override
    public PlayerRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PlayerRecord
     */
    public PlayerRecord() {
        super(Player.PLAYER);
    }

    /**
     * Create a detached, initialised PlayerRecord
     */
    public PlayerRecord(Integer id, String playerUuid) {
        super(Player.PLAYER);

        set(0, id);
        set(1, playerUuid);
    }
}