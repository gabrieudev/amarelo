package com.api.amarelo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AirportDTO {

    private Long id;

    @NotBlank
    private String iataCode;

    @NotBlank
    private String name;

    @NotBlank
    private String city;

    @NotBlank
    private String country;

}
