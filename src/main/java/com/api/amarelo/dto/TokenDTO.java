package com.api.amarelo.dto;

import java.time.Instant;

public record TokenDTO(
        String token,
        Instant expiresAt
) {
}
