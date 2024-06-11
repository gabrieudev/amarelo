package com.api.amarelo.service;

import com.api.amarelo.dto.FlightDTO;
import com.api.amarelo.exception.BusinessRuleException;
import com.api.amarelo.exception.EntityNotFoundException;
import com.api.amarelo.model.Flight;
import com.api.amarelo.model.Seat;
import com.api.amarelo.repository.FlightRepository;
import com.api.amarelo.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private MappingService mappingService;

    /**
     * Retrieves a flight by its id
     *
     * @param id the flight's id
     * @return the flight's DTO
     */
    public FlightDTO getById(Long id) {
        Flight flight = flightRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Flight not found with this id: " + id)
        );
        return mappingService.toDto(flight);
    }

    /**
     * Saves a flight
     *
     * @param flightDTO the flight's DTO
     * @throws BusinessRuleException if the airports are the same
     */
    public void save(FlightDTO flightDTO) {
        if (flightDTO.getOriginAirport().getId().equals(flightDTO.getDestinationAirport().getId())) {
            throw new BusinessRuleException("Airports cannot be the same");
        }
        Flight flight = mappingService.toModel(flightDTO);
        flightRepository.save(flight);
    }

    /**
     * Updates a flight
     *
     * @param id the flight's id
     * @param flightDTO the flight's DTO
     * @return the updated flight's DTO
     * @throws EntityNotFoundException if id is not found
     * @throws BusinessRuleException if the airports are the same
     */
    public FlightDTO update(Long id, FlightDTO flightDTO) {
        Flight flight = flightRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Flight not found with this id: " + id)
        );
        if (flightDTO.getOriginAirport().getId().equals(flightDTO.getDestinationAirport().getId())) {
            throw new BusinessRuleException("Airports cannot be the same");
        }
        mappingService.toModel(flightDTO, flight);
        Flight updatedFlight = flightRepository.save(flight);
        return mappingService.toDto(updatedFlight);
    }

    /**
     * Deletes a flight by its id
     *
     * @param id the flight's id
     * @throws EntityNotFoundException if id is not found
     */
    public void delete(Long id) {
        Flight flight = flightRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Flight not found with this id: " + id)
        );
        flightRepository.delete(flight);
    }

    /**
     * checks whether the flight is full of seats
     *
     * @param id the flight's id
     * @return boolean
     */
    public boolean isFull(Long id) {
        Flight flight = flightRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Flight not found with this id: " + id)
        );
        List<Seat> seats = seatRepository.findByFlight(flight);
        return flight.getSeatCapacity() <= seats.size();
    }

    /**
     * Retrieves the flights
     *
     * @return the Page of flights
     */
    public Page<FlightDTO> getAll(Pageable pageable) {
        return flightRepository.findAll(pageable).map(
                flight -> mappingService.toDto(flight)
        );
    }
}

