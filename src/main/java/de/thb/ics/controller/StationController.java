package de.thb.ics.controller;

import de.thb.ics.controller.exception.ResourceExistsException;
import de.thb.ics.controller.exception.ResourceNotFoundException;
import de.thb.ics.service.StationService;
import de.thb.ics.model.Station;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Station getStation(@PathVariable("id") Long id) {
        return stationService.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Operation(summary = "Create Station.")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "409", description = "Station already exists.", content = @Content)
    })
    @PostMapping()
    public Station createStation(@RequestBody Station station) {
        Optional<Station> newStation = stationService.createStation(station);
        if (newStation.isPresent()) {
            return newStation.get();
        } else {
            throw new ResourceExistsException();
        }
    }

    @Operation(summary = "Update Station.")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Station not found.", content = @Content)
    })
    @PutMapping("/{id}/")
    public Station updateStation(@PathVariable("id") Long id,
                                 @RequestBody Station station) {
        Optional<Station> updatedStation = stationService.updateStation(id, station);
        if (updatedStation.isPresent()) {
            return updatedStation.get();
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
