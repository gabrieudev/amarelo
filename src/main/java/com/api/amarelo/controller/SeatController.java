package com.api.amarelo.controller;

import com.api.amarelo.dto.SeatDTO;
import com.api.amarelo.service.SeatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> save(@Valid @RequestBody SeatDTO seatDTO) {
        seatService.save(seatDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<SeatDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(seatService.getById(id));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<List<SeatDTO>> getAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(seatService.getAll(pageable).getContent());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<SeatDTO> update(@PathVariable("id") Long id, @Valid @RequestBody SeatDTO seatDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(seatService.update(id, seatDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        seatService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/available/{flightId}")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<List<SeatDTO>> getAvailable(@PathVariable("flightId") Long flightId, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(seatService.getAvailable(flightId, pageable).getContent());
    }

    @GetMapping("/available/{flightId}/{min}/{max}")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<List<SeatDTO>> getAvailableByPrice(
            @PathVariable("flightId") Long flightId,
            @PathVariable("min") BigDecimal min,
            @PathVariable("max") BigDecimal max,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(seatService.getAvailableByPrice(flightId, min, max, pageable).getContent());
    }

}

