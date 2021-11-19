package de.thb.ics.repository;

import de.thb.ics.jooq.tables.records.StationRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static de.thb.ics.jooq.tables.Station.STATION;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StationRepository {
    private final DSLContext context;

    public List<StationRecord> findAll() {
        return context.selectFrom(STATION)
                .orderBy(STATION.ID.asc())
                .fetch();
    }

    public Optional<StationRecord> findById(String id) {
        return context.selectFrom(STATION)
                .where(STATION.ID.eq(id))
                .fetchOptional();
    }

    public StationRecord create(StationRecord record) {
        return context.insertInto(STATION)
                .set(record)
                .returning()
                .fetchOne();
    }

    public Optional<StationRecord> update(String id, StationRecord record) {
        return context.update(STATION)
                .set(record)
                .where(STATION.ID.eq(id))
                .returning()
                .fetchOptional();
    }

    public Optional<StationRecord> delete(String id) {
        return context.delete(STATION)
                .where(STATION.ID.eq(id))
                .returning()
                .fetchOptional();
    }
}
