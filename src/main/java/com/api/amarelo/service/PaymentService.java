package com.api.amarelo.service;

import com.api.amarelo.dto.PaymentDTO;
import com.api.amarelo.exception.EntityNotFoundException;
import com.api.amarelo.model.Payment;
import com.api.amarelo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private MappingService mappingService;

    /**
     * Retrieves a payment by its id
     *
     * @param id the payment's id
     * @return the payment's DTO
     */
    public PaymentDTO getById(UUID id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Payment not found with this id: " + id)
        );
        return mappingService.toDto(payment);
    }

    /**
     * Updates a payment
     *
     * @param id the payment's id
     * @param paymentDTO the payment's DTO
     * @return the updated payment's DTO
     * @throws EntityNotFoundException if id is not found
     */
    public PaymentDTO update(UUID id, PaymentDTO paymentDTO) {
        Payment payment = paymentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Payment not found with this id: " + id)
        );
        mappingService.toModel(paymentDTO, payment);
        Payment updatedPayment = paymentRepository.save(payment);
        return mappingService.toDto(updatedPayment);
    }

    /**
     * Deletes a payment by its id
     *
     * @param id the payment's id
     * @throws EntityNotFoundException if id is not found
     */
    public void delete(UUID id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Payment not found with this id: " + id)
        );
        paymentRepository.delete(payment);
    }

    /**
     * Retrieves the payments
     *
     * @return the Page of payments
     */
    public Page<PaymentDTO> getAll(Pageable pageable) {
        return paymentRepository.findAll(pageable).map(
                payment -> mappingService.toDto(payment)
        );
    }
}

