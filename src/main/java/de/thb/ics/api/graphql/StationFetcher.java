package de.thb.ics.api.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import de.thb.ics.model.Station;
import de.thb.ics.service.StationService;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@DgsComponent
@RequiredArgsConstructor
public class StationFetcher {
    private final StationService stationService;
    private final ObjectMapper mapper;

    @DgsData(parentType = "QueryRootType", field = "station")
    public Station getStation(@InputArgument("id") long id) {
        return stationService.findById(id).get();
    }

    @DgsData(parentType = "QueryRootType", field = "stations")
    public List<Station> getStations() {
        return stationService.findAll();
    }

    @DgsData(parentType = "MutationRootType", field = "addStation")
    public Station createStation(DataFetchingEnvironment dfe) {
        Station station = mapper.convertValue(dfe.getArgument("station"), Station.class);
        return stationService.createStation(station).get();
    }

    @DgsData(parentType = "MutationRootType", field = "updateStation")
    public Station updateStation(@InputArgument("id") long id, DataFetchingEnvironment dfe) {
        Station station = mapper.convertValue(dfe.getArgument("station"), Station.class);
        return stationService.updateStation(id, station).get();
    }

    @DgsData(parentType = "SubscriptionRootType", field = "stations")
    public Publisher<List<Station>> subscribeStations() {
        return Flux.interval(Duration.ofSeconds(5)).map(t -> stationService.findAll());
    }
}
