package com.api.amarelo.dto;

import com.api.amarelo.model.Airport;
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
    private Airport originAirport;

    @NotBlank
    private String destination;

    @NotNull
    private Airport destinationAirport;

    @NotNull
    private Instant departureTime;

    private Instant arrivalTime;

    @NotBlank
    private String flightNumber;

    @NotNull
    private int seatCapacity;

}
