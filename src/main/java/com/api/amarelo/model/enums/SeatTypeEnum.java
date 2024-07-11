package com.api.amarelo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SeatTypeEnum {
    ECONOMY(1L, "economy"),
    BUSINESS(2L, "business"),
    FIRST(3L, "first");

    private Long id;
    private String type;

}
