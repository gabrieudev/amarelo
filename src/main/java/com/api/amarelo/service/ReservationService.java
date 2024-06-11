package com.api.amarelo.service;

import com.api.amarelo.dto.ReservationDTO;
import com.api.amarelo.exception.BusinessRuleException;
import com.api.amarelo.exception.EntityNotFoundException;
import com.api.amarelo.model.Reservation;
import com.api.amarelo.model.Seat;
import com.api.amarelo.repository.ReservationRepository;
import com.api.amarelo.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private MappingService mappingService;

    /**
     * Retrieves a reservation by its id
     *
     * @param id the reservation's id
     * @return the reservation's DTO
     */
    public ReservationDTO getById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Reservation not found with this id: " + id)
        );
        return mappingService.toDto(reservation);
    }

    /**
     * Saves a reservation
     *
     * @param reservationDTO the reservation's DTO
     * @throws EntityNotFoundException if seat if not found
     * @throws BusinessRuleException if the seat is already reserved
     */
    public void save(ReservationDTO reservationDTO) {
        Seat seat = seatRepository.findById(reservationDTO.getSeat().getId()).orElseThrow(
                () -> new EntityNotFoundException("Seat not found with this id: " + reservationDTO.getSeat().getId())
        );
        if (reservationRepository.existsBySeat(seat)) {
            throw new BusinessRuleException("the seat has already been reserved");
        }
        Reservation reservation = mappingService.toModel(reservationDTO);
        reservationRepository.save(reservation);
    }

    /**
     * Updates a reservation
     *
     * @param id the reservation's id
     * @param reservationDTO the reservation's DTO
     * @return the updated reservation's DTO
     * @throws EntityNotFoundException if id is not found
     * @throws EntityNotFoundException if seat if not found
     * @throws BusinessRuleException if the seat is already reserved
     */
    public ReservationDTO update(Long id, ReservationDTO reservationDTO) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Reservation not found with this id: " + id)
        );
        Seat seat = seatRepository.findById(reservationDTO.getSeat().getId()).orElseThrow(
                () -> new EntityNotFoundException("Seat not found with this id: " + reservationDTO.getSeat().getId())
        );
        if (!reservationDTO.getSeat().getId().equals(reservation.getSeat().getId()) && reservationRepository.existsBySeat(seat)) {
            throw new BusinessRuleException("the seat has already been reserved");
        }
        mappingService.toModel(reservationDTO, reservation);
        Reservation updatedReservation = reservationRepository.save(reservation);
        return mappingService.toDto(updatedReservation);
    }

    /**
     * Deletes a reservation by its id
     *
     * @param id the reservation's id
     * @throws EntityNotFoundException if id is not found
     */
    public void delete(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Reservation not found with this id: " + id)
        );
        reservationRepository.delete(reservation);
    }

    /**
     * Retrieves the reservations
     *
     * @return the Page of reservations
     */
    public Page<ReservationDTO> getAll(Pageable pageable) {
        return reservationRepository.findAll(pageable).map(
                reservation -> mappingService.toDto(reservation)
        );
    }
}

