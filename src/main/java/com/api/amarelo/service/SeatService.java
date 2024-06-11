package com.api.amarelo.service;

import com.api.amarelo.dto.SeatDTO;
import com.api.amarelo.exception.BusinessRuleException;
import com.api.amarelo.exception.EntityNotFoundException;
import com.api.amarelo.model.Seat;
import com.api.amarelo.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private FlightService flightService;

    @Autowired
    private MappingService mappingService;

    /**
     * Retrieves a seat by its id
     *
     * @param id the seat's id
     * @return the seat's DTO
     */
    public SeatDTO getById(Long id) {
        Seat seat = seatRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Seat not found with this id: " + id)
        );
        return mappingService.toDto(seat);
    }

    /**
     * Saves a seat
     *
     * @param seatDTO the seat's DTO
     * @throws BusinessRuleException if there is no more seat capacity on the flight
     */
    public void save(SeatDTO seatDTO) {
        if (flightService.isFull(seatDTO.getFlight().getId())) {
            throw new BusinessRuleException("there is no more space for seats on the flight");
        }
        Seat seat = mappingService.toModel(seatDTO);
        seatRepository.save(seat);
    }

    /**
     * Updates a seat
     *
     * @param id the seat's id
     * @param seatDTO the seat's DTO
     * @return the updated seat's DTO
     * @throws EntityNotFoundException if id is not found
     */
    public SeatDTO update(Long id, SeatDTO seatDTO) {
        Seat seat = seatRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Seat not found with this id: " + id)
        );
        mappingService.toModel(seatDTO, seat);
        Seat updatedSeat = seatRepository.save(seat);
        return mappingService.toDto(updatedSeat);
    }

    /**
     * Deletes a seat by its id
     *
     * @param id the seat's id
     * @throws EntityNotFoundException if id is not found
     */
    public void delete(Long id) {
        Seat seat = seatRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Seat not found with this id: " + id)
        );
        seatRepository.delete(seat);
    }

    /**
     * Retrieves the seats
     *
     * @return the Page of seats
     */
    public Page<SeatDTO> getAll(Pageable pageable) {
        return seatRepository.findAll(pageable).map(
                seat -> mappingService.toDto(seat)
        );
    }
}

