package de.thb.ics.configuration;

import de.thb.ics.model.Station;
import de.thb.ics.service.StationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulingConfiguration {
    private final StationService stationService;

    @Value("${scheduling}")
    private boolean scheduling;

    @Scheduled(fixedRate = 30000)
    private void createRandomStation() {
        if(scheduling) {
            int target = getRandomInt(1, 100);
            int actual = getRandomInt(0, target);

            Station station = Station.builder()
                    .target(target)
                    .actual(actual)
                    .build();

            Station newStation = stationService.createStation(station)
                    .orElseThrow();

            log.debug("Created new Station (id={}).", newStation.getId());
        }
    }

    private int getRandomInt(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }
}
