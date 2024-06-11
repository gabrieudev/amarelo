package com.api.amarelo.dto;

public record UserLoginResponse(String token, Long expiresIn) {
}
