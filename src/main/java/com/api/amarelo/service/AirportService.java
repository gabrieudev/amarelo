package com.api.amarelo.service;

import com.api.amarelo.dto.AirportDTO;
import com.api.amarelo.exception.EntityNotFoundException;
import com.api.amarelo.model.Airport;
import com.api.amarelo.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private MappingService mappingService;

    /**
     * Retrieves an airport by its id
     *
     * @param id the airport's id
     * @return the airport's DTO
     */
    public AirportDTO getById(Long id) {
        Airport airport = airportRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Airport not found with this id: " + id)
        );
        return mappingService.toDto(airport);
    }

    /**
     * Saves an airport
     *
     * @param airportDTO the airport's DTO
     */
    public void save(AirportDTO airportDTO) {
        Airport airport = mappingService.toModel(airportDTO);
        airportRepository.save(airport);
    }

    /**
     * Updates an airport
     *
     * @param id the airport's id
     * @param airportDTO the airport's DTO
     * @return the updated airport's DTO
     * @throws EntityNotFoundException if id is not found
     */
    public AirportDTO update(Long id, AirportDTO airportDTO) {
        Airport airport = airportRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Airport not found with this id: " + id)
        );
        mappingService.toModel(airportDTO, airport);
        Airport updatedAirport = airportRepository.save(airport);
        return mappingService.toDto(updatedAirport);
    }

    /**
     * Deletes an airport by its id
     *
     * @param id the airport's id
     * @throws EntityNotFoundException if id is not found
     */
    public void delete(Long id) {
        Airport airport = airportRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Airport not found with this id: " + id)
        );
        airportRepository.delete(airport);
    }

    /**
     * Retrieves the airports
     *
     * @return the Page of airports
     */
    public Page<AirportDTO> getAll(Pageable pageable) {
        return airportRepository.findAll(pageable).map(
                airport -> mappingService.toDto(airport)
        );
    }

}

