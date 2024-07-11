package com.api.amarelo.dto;

import com.api.amarelo.model.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
public class PaymentDTO {

    private UUID id;

    @NotBlank
    @CreditCardNumber
    private String creditCard;

    @NotNull
    @Positive
    private BigDecimal amount;

    private Instant date;

    @NotNull
    private PaymentStatus paymentStatus;

}
