package com.api.amarelo.controller;

import com.api.amarelo.dto.PaymentDTO;
import com.api.amarelo.service.PaymentService;
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
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<PaymentDTO> getById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.getById(id));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<PaymentDTO>> getAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.getAll(pageable).getContent());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<PaymentDTO> update(@PathVariable("id") UUID id, @Valid @RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.update(id, paymentDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        paymentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

