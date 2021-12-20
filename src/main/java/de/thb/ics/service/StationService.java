package de.thb.ics.service;

import de.thb.ics.model.Station;
import de.thb.ics.repository.StationRepository;
import de.thb.ics.repository.jooq.tables.records.StationRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StationService {
    private final StationRepository stationRepository;

    public List<Station> findAll() {
        return stationRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public Optional<Station> findById(int id) {
        return stationRepository.findById(id).map(this::map);
    }

    public Optional<Station> createStation(Station station) {
        if (stationRepository.findById(station.getId()).isPresent()) {
            return Optional.empty();
        } else {
            StationRecord record = new StationRecord();

            record.setDate(LocalDate.now());
            record.setTarget(station.getTarget());
            record.setActual(station.getActual());
            record.setVariance(calculateVariance(station.getTarget(), station.getActual()));

            return Optional.ofNullable(map(stationRepository.create(record)));
        }
    }

    public Optional<Station> updateStation(int id, Station station) {
        Optional<StationRecord> record = stationRepository.findById(id);
        if (record.isPresent()) {
            record.get().setDate(station.getDate());
            record.get().setActual(station.getActual());
            record.get().setVariance(calculateVariance(record.get().getTarget(), station.getActual()));

            return stationRepository.update(id, record.get()).map(this::map);
        } else {
            return Optional.empty();
        }
    }

    public int calculateVariance(int target, int actual) {
        return actual - target;
    }

    public Station map(StationRecord record) {
        return Station.builder()
                .id(record.getId())
                .date(record.getDate())
                .target(record.getTarget())
                .actual(record.getActual())
                .variance(record.getVariance())
                .build();
    }
}
