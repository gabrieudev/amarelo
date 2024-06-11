package com.api.amarelo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationDTO {

    private Long id;

    @NotNull
    private UserDTO user;

    @NotNull
    private FlightDTO flight;

    @NotNull
    private PaymentDTO payment;

    @NotNull
    private SeatDTO seat;

}
