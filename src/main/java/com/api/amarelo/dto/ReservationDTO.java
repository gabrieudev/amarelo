package com.api.amarelo.dto;

import com.api.amarelo.model.Flight;
import com.api.amarelo.model.Payment;
import com.api.amarelo.model.Seat;
import com.api.amarelo.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationDTO {

    private Long id;

    @NotNull
    private User user;

    @NotNull
    private Flight flight;

    @NotNull
    private Payment payment;

    @NotNull
    private Seat seat;

}
