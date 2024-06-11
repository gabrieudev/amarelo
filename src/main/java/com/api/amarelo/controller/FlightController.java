package com.api.amarelo.controller;

import com.api.amarelo.dto.FlightDTO;
import com.api.amarelo.service.FlightService;
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
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> save(@Valid @RequestBody FlightDTO flightDTO) {
        flightService.save(flightDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<FlightDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(flightService.getById(id));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<List<FlightDTO>> getAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(flightService.getAll(pageable).getContent());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<FlightDTO> update(@PathVariable("id") Long id, @Valid @RequestBody FlightDTO flightDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(flightService.update(id, flightDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        flightService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

