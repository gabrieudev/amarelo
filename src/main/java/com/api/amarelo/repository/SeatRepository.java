package com.api.amarelo.repository;

import com.api.amarelo.model.Flight;
import com.api.amarelo.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByFlight(Flight flight);
}
