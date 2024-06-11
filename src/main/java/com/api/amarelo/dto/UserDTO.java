package com.api.amarelo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDTO {

    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private LocalDate dateOfBirth;

    @NotBlank
    private String nationality;

    @NotBlank
    private String passportNumber;

    private Set<String> roles;

    private Instant createdAt;

    private Instant updatedAt;

    private boolean isChecked;

}
