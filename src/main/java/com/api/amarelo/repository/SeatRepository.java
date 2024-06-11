package com.api.amarelo.repository;

import com.api.amarelo.model.Flight;
import com.api.amarelo.model.Seat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByFlight(Flight flight);

    @Query(
            value = "SELECT s.* " +
                    "FROM tb_seats s " +
                    "WHERE s.flight_id = :flightId " +
                    "AND NOT EXISTS (" +
                    "   SELECT r.* " +
                    "   FROM tb_reservations r " +
                    "   WHERE r.seat_id = s.id)",
            nativeQuery = true
    )
    Page<Seat> findAvailable(@Param("flightId") Long flightId, Pageable pageable);

}
