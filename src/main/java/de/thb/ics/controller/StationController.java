package de.thb.ics.controller;

import de.thb.ics.controller.exception.ResourceNotFoundException;
import de.thb.ics.service.station.StationService;
import de.thb.ics.service.station.model.Station;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stations/")
@RequiredArgsConstructor
public class StationController {
    private final StationService stationService;

    @Operation(summary = "Get stations.")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
    })
    @GetMapping
    public List<Station> getStations() {
        return stationService.findAll();
    }

    @Operation(summary = "Get station.")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Station not found.", content = @Content)
    })
    @GetMapping("/{id}/")
    public Station getStation(@PathVariable("id") long id) {
        return stationService.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Operation(summary = "Update Station.")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Station not found.", content = @Content)
    })
    @PutMapping("/{id}/")
    public Station updateStation(@PathVariable("id") long id,
                                 @RequestBody Station station) {
        // TODO
        return null;
    }
}
