package com.api.amarelo.dto;

import com.api.amarelo.model.SeatType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class SeatDTO {

    private Long id;

    @NotNull
    private SeatType seatType;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private FlightDTO flight;

}
