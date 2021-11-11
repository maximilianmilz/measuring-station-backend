package de.thb.ics.service.station.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class Station {
    @Schema(example = "42")
    long stationId;

    @Schema(example = "2006-05-26")
    LocalDate date;

    @Schema(example = "42")
    int target;

    @Schema(example = "33")
    int actual;

    @Schema(example = "-9")
    int variance;
}
