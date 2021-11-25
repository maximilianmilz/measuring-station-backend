package de.thb.ics.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class Station {
    @Schema(example = "NV141")
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
