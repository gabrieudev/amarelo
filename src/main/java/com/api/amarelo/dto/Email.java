package com.api.amarelo.dto;

public record Email(
        String to,
        String subject,
        String body
) {
}
