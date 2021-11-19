/*
 * This file is generated by jOOQ.
 */
package de.thb.ics.jooq.tables;


import de.thb.ics.jooq.DefaultSchema;
import de.thb.ics.jooq.Keys;
import de.thb.ics.jooq.tables.records.StationRecord;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Station extends TableImpl<StationRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>station</code>
     */
    public static final Station STATION = new Station();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<StationRecord> getRecordType() {
        return StationRecord.class;
    }

    /**
     * The column <code>station.id</code>.
     */
    public final TableField<StationRecord, String> ID = createField(DSL.name("id"), SQLDataType.VARCHAR(8).nullable(false), this, "");

    /**
     * The column <code>station.date</code>.
     */
    public final TableField<StationRecord, LocalDate> DATE = createField(DSL.name("date"), SQLDataType.LOCALDATE.nullable(false), this, "");

    /**
     * The column <code>station.target</code>.
     */
    public final TableField<StationRecord, Integer> TARGET = createField(DSL.name("target"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>station.actual</code>.
     */
    public final TableField<StationRecord, Integer> ACTUAL = createField(DSL.name("actual"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>station.variance</code>.
     */
    public final TableField<StationRecord, Integer> VARIANCE = createField(DSL.name("variance"), SQLDataType.INTEGER.nullable(false), this, "");

    private Station(Name alias, Table<StationRecord> aliased) {
        this(alias, aliased, null);
    }

    private Station(Name alias, Table<StationRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>station</code> table reference
     */
    public Station(String alias) {
        this(DSL.name(alias), STATION);
    }

    /**
     * Create an aliased <code>station</code> table reference
     */
    public Station(Name alias) {
        this(alias, STATION);
    }

    /**
     * Create a <code>station</code> table reference
     */
    public Station() {
        this(DSL.name("station"), null);
    }

    public <O extends Record> Station(Table<O> child, ForeignKey<O, StationRecord> key) {
        super(child, key, STATION);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<StationRecord> getPrimaryKey() {
        return Keys.STATION_PKEY;
    }

    @Override
    public List<UniqueKey<StationRecord>> getKeys() {
        return Arrays.<UniqueKey<StationRecord>>asList(Keys.STATION_PKEY);
    }

    @Override
    public Station as(String alias) {
        return new Station(DSL.name(alias), this);
    }

    @Override
    public Station as(Name alias) {
        return new Station(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Station rename(String name) {
        return new Station(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Station rename(Name name) {
        return new Station(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<String, LocalDate, Integer, Integer, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
