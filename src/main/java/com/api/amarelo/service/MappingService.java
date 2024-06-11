package com.api.amarelo.service;

import com.api.amarelo.dto.*;
import com.api.amarelo.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MappingService {

    @Autowired
    private ModelMapper modelMapper;

    public User toModel(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO toDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public Airport toModel(AirportDTO airportDTO) {
        return modelMapper.map(airportDTO, Airport.class);
    }

    public AirportDTO toDto(Airport airport) {
        return modelMapper.map(airport, AirportDTO.class);
    }

    public void toModel(AirportDTO airportDTO, Airport airport) {
        modelMapper.map(airportDTO, airport);
    }

    public Payment toModel(PaymentDTO paymentDTO) {
        return modelMapper.map(paymentDTO, Payment.class);
    }

    public PaymentDTO toDto(Payment payment) {
        return modelMapper.map(payment, PaymentDTO.class);
    }

    public void toModel(PaymentDTO paymentDTO, Payment payment) {
        modelMapper.map(paymentDTO, payment);
    }

    public Reservation toModel(ReservationDTO reservationDTO) {
        return modelMapper.map(reservationDTO, Reservation.class);
    }

    public ReservationDTO toDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDTO.class);
    }

    public void toModel(ReservationDTO reservationDTO, Reservation reservation) {
        modelMapper.map(reservationDTO, reservation);
    }

    public Flight toModel(FlightDTO flightDTO) {
        return modelMapper.map(flightDTO, Flight.class);
    }

    public FlightDTO toDto(Flight flight) {
        return modelMapper.map(flight, FlightDTO.class);
    }

    public void toModel(FlightDTO flightDTO, Flight flight) {
        modelMapper.map(flightDTO, flight);
    }

    public Seat toModel(SeatDTO seatDTO) {
        return modelMapper.map(seatDTO, Seat.class);
    }

    public SeatDTO toDto(Seat seat) {
        return modelMapper.map(seat, SeatDTO.class);
    }

    public void toModel(SeatDTO seatDTO, Seat seat) {
        modelMapper.map(seatDTO, seat);
    }

}
