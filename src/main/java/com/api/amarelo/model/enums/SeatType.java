package com.api.amarelo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SeatType {
    ECONOMY("economy"),
    BUSINESS("business"),
    FIRST("first");

    private final String type;

}
