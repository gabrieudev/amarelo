package com.api.amarelo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentStatusEnum {
    PENDING(1L, "pending"),
    CONFIRMED(2L, "confirmed"),
    CANCELED(3L, "canceled");

    private Long id;
    private String status;

}
