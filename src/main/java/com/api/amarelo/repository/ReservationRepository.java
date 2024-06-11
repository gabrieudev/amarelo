package com.api.amarelo.repository;

import com.api.amarelo.model.Reservation;
import com.api.amarelo.model.Seat;
import com.api.amarelo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsBySeat(Seat seat);
    Page<Reservation> findByUser(User user, Pageable pageable);
}
