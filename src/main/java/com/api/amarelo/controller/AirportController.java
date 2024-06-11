package com.api.amarelo.controller;

import com.api.amarelo.dto.AirportDTO;
import com.api.amarelo.service.AirportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> save(@Valid @RequestBody AirportDTO airportDTO) {
        airportService.save(airportDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<AirportDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(airportService.getById(id));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<List<AirportDTO>> getAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(airportService.getAll(pageable).getContent());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<AirportDTO> update(@PathVariable("id") Long id, @Valid @RequestBody AirportDTO airportDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(airportService.update(id, airportDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        airportService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

