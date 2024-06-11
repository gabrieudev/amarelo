package com.api.amarelo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class FlightDTO {

    private Long id;

    @NotBlank
    private String origin;

    @NotNull
    private AirportDTO originAirport;

    @NotBlank
    private String destination;

    @NotNull
    private AirportDTO destinationAirport;

    @NotNull
    private Instant departureTime;

    private Instant arrivalTime;

    @NotBlank
    private String flightNumber;

    @NotNull
    private int seatCapacity;

}
