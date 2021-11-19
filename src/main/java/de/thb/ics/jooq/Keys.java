/*
 * This file is generated by jOOQ.
 */
package de.thb.ics.jooq;


import de.thb.ics.jooq.tables.Station;
import de.thb.ics.jooq.tables.records.StationRecord;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in 
 * the default schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<StationRecord> STATION_PKEY = Internal.createUniqueKey(Station.STATION, DSL.name("station_pkey"), new TableField[] { Station.STATION.ID }, true);
}
