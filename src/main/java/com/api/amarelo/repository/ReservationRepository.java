package com.api.amarelo.repository;

import com.api.amarelo.model.Reservation;
import com.api.amarelo.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsBySeat(Seat seat);

}
