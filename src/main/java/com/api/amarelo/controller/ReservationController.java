package com.api.amarelo.controller;

import com.api.amarelo.dto.ReservationDTO;
import com.api.amarelo.service.ReservationService;
import com.api.amarelo.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<Void> save(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody ReservationDTO reservationDTO
    ) {
        if (tokenService.notBelongs(jwt, reservationDTO.getUser().getId())) {
            throw new AccessDeniedException("You don't have access to this");
        }
        reservationService.save(reservationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<ReservationDTO> getById(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable("id") Long id
    ) {
        ReservationDTO reservationDTO = reservationService.getById(id);
        if (tokenService.notBelongs(jwt, reservationDTO.getUser().getId())) {
            throw new AccessDeniedException("You don't have access to this");
        }
        return ResponseEntity.status(HttpStatus.OK).body(reservationDTO);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<ReservationDTO>> getAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getAll(pageable).getContent());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<ReservationDTO> update(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable("id") Long id,
            @Valid @RequestBody ReservationDTO reservationDTO
    ) {
        if (tokenService.notBelongs(jwt, reservationDTO.getUser().getId())) {
            throw new AccessDeniedException("You don't have access to this");
        }
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.update(id, reservationDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable("id") Long id
    ) {
        ReservationDTO reservationDTO = reservationService.getById(id);
        if (tokenService.notBelongs(jwt, reservationDTO.getUser().getId())) {
            throw new AccessDeniedException("You don't have access to this");
        }
        reservationService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/by-user/{userId}")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<List<ReservationDTO>> getByUser(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable("userId") UUID userId,
            Pageable pageable
    ) {
        if (tokenService.notBelongs(jwt, userId)) {
            throw new AccessDeniedException("You don't have access to this");
        }
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getByUser(userId, pageable).getContent());
    }

}

