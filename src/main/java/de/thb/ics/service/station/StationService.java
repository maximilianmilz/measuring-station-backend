package de.thb.ics.service.station;

import de.thb.ics.service.station.jooq.tables.records.StationRecord;
import de.thb.ics.service.station.model.Station;
import de.thb.ics.service.station.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public Optional<Station> findById(long id) {
        return stationRepository.findById(id).map(this::map);
    }

    public Station createStation(Station station) {
        return map(stationRepository.create(map(station)));
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
