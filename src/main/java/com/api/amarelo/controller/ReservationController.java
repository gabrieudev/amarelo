package com.api.amarelo.controller;

import com.api.amarelo.dto.ReservationDTO;
import com.api.amarelo.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<Void> save(@Valid @RequestBody ReservationDTO reservationDTO) {
        reservationService.save(reservationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<ReservationDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getById(id));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<ReservationDTO>> getAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getAll(pageable).getContent());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<ReservationDTO> update(@PathVariable("id") Long id, @Valid @RequestBody ReservationDTO reservationDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.update(id, reservationDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        reservationService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/by-user/{userId}")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<List<ReservationDTO>> getByUser(@PathVariable("userId") UUID userId, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getByUser(userId, pageable).getContent());
    }

}

