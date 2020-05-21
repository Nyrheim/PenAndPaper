/*
 * This file is generated by jOOQ.
 */
package com.github.liamvii.penandpaper.utils.generated.tables;


import java.util.Arrays;
import java.util.List;

import com.github.liamvii.penandpaper.utils.generated.Indexes;
import com.github.liamvii.penandpaper.utils.generated.Keys;
import com.github.liamvii.penandpaper.utils.generated.Penandpaper;
import com.github.liamvii.penandpaper.utils.generated.tables.records.ArchetypesRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
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
public class Archetypes extends TableImpl<ArchetypesRecord> {

    private static final long serialVersionUID = 2106301291;

    /**
     * The reference instance of <code>penandpaper.archetypes</code>
     */
    public static final Archetypes ARCHETYPES = new Archetypes();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ArchetypesRecord> getRecordType() {
        return ArchetypesRecord.class;
    }

    /**
     * The column <code>penandpaper.archetypes.ArchetypeID</code>.
     */
    public final TableField<ArchetypesRecord, String> ARCHETYPEID = createField(DSL.name("ArchetypeID"), org.jooq.impl.SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>penandpaper.archetypes.jobID</code>.
     */
    public final TableField<ArchetypesRecord, String> JOBID = createField(DSL.name("jobID"), org.jooq.impl.SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>penandpaper.archetypes.archetype</code>.
     */
    public final TableField<ArchetypesRecord, String> ARCHETYPE = createField(DSL.name("archetype"), org.jooq.impl.SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * Create a <code>penandpaper.archetypes</code> table reference
     */
    public Archetypes() {
        this(DSL.name("archetypes"), null);
    }

    /**
     * Create an aliased <code>penandpaper.archetypes</code> table reference
     */
    public Archetypes(String alias) {
        this(DSL.name(alias), ARCHETYPES);
    }

    /**
     * Create an aliased <code>penandpaper.archetypes</code> table reference
     */
    public Archetypes(Name alias) {
        this(alias, ARCHETYPES);
    }

    private Archetypes(Name alias, Table<ArchetypesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Archetypes(Name alias, Table<ArchetypesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Archetypes(Table<O> child, ForeignKey<O, ArchetypesRecord> key) {
        super(child, key, ARCHETYPES);
    }

    @Override
    public Schema getSchema() {
        return Penandpaper.PENANDPAPER;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.ARCHETYPES_FKIDX_57);
    }

    @Override
    public UniqueKey<ArchetypesRecord> getPrimaryKey() {
        return Keys.KEY_ARCHETYPES_PRIMARY;
    }

    @Override
    public List<UniqueKey<ArchetypesRecord>> getKeys() {
        return Arrays.<UniqueKey<ArchetypesRecord>>asList(Keys.KEY_ARCHETYPES_PRIMARY);
    }

    @Override
    public List<ForeignKey<ArchetypesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ArchetypesRecord, ?>>asList(Keys.FK_57);
    }

    public Jobs jobs() {
        return new Jobs(this, Keys.FK_57);
    }

    @Override
    public Archetypes as(String alias) {
        return new Archetypes(DSL.name(alias), this);
    }

    @Override
    public Archetypes as(Name alias) {
        return new Archetypes(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Archetypes rename(String name) {
        return new Archetypes(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Archetypes rename(Name name) {
        return new Archetypes(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
