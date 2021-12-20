package de.thb.ics.repository;

import de.thb.ics.repository.jooq.tables.records.StationRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static de.thb.ics.configuration.CacheConfiguration.REPOSITORY_CACHE_MANAGER;
import static de.thb.ics.configuration.CacheConfiguration.STATION_REPOSITORY_CACHE;
import static de.thb.ics.repository.jooq.tables.Station.STATION;

@Slf4j
@Repository
@RequiredArgsConstructor
public class StationRepository {
    private final DSLContext context;

    @Cacheable(cacheManager = REPOSITORY_CACHE_MANAGER, cacheNames = STATION_REPOSITORY_CACHE)
    public List<StationRecord> findAll() {
        log.debug("Called findAll stations method.");
        return context.selectFrom(STATION)
                .orderBy(STATION.ID.asc())
                .fetch();
    }

    public Optional<StationRecord> findById(int id) {
        return context.selectFrom(STATION)
                .where(STATION.ID.eq(id))
                .fetchOptional();
    }

    @CacheEvict(cacheManager = REPOSITORY_CACHE_MANAGER, cacheNames = STATION_REPOSITORY_CACHE, allEntries = true)
    public StationRecord create(StationRecord record) {
        log.debug("Cleared cache.");
        return context.insertInto(STATION)
                .set(record)
                .returning()
                .fetchOne();
    }

    @CacheEvict(cacheManager = REPOSITORY_CACHE_MANAGER, cacheNames = STATION_REPOSITORY_CACHE, allEntries = true)
    public Optional<StationRecord> update(int id, StationRecord record) {
        context.update(STATION)
                .set(record)
                .where(STATION.ID.eq(id))
                .execute();

        return findById(id);
    }

    @CacheEvict(cacheManager = REPOSITORY_CACHE_MANAGER, cacheNames = STATION_REPOSITORY_CACHE, allEntries = true)
    public Optional<StationRecord> delete(int id) {
        return context.delete(STATION)
                .where(STATION.ID.eq(id))
                .returning()
                .fetchOptional();
    }
}
