package de.thb.ics.service.station;

import de.thb.ics.service.station.jooq.tables.records.StationRecord;
import de.thb.ics.service.station.model.Station;
import de.thb.ics.service.station.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<Station> findById(String id) {
        return stationRepository.findById(id).map(this::map);
    }

    public Optional<Station> createStation(Station station) {
        if (stationRepository.findById(station.getStationId()).isPresent()) {
            return Optional.empty();
        } else {
            StationRecord record = new StationRecord();

            record.setId(station.getStationId());
            record.setDate(LocalDate.now());
            record.setTarget(station.getTarget());
            record.setActual(station.getActual());
            record.setVariance(calculateVariance(station.getTarget(), station.getActual()));

            return Optional.ofNullable(map(stationRepository.create(record)));
        }
    }

    public Optional<Station> updateStation(String id, Station station) {
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

    private int calculateVariance(int target, int actual) {
        return target - actual;
    }

    public StationRecord map(Station station) {
        StationRecord record = new StationRecord();

        record.setDate(station.getDate());
        record.setTarget(station.getTarget());
        record.setActual(station.getActual());
        record.setVariance(station.getVariance());

        return record;
    }

    public Station map(StationRecord record) {
        return Station.builder()
                .stationId(record.getId())
                .date(record.getDate())
                .target(record.getTarget())
                .actual(record.getActual())
                .variance(record.getVariance())
                .build();
    }
}
