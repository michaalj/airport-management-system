package org.example.project.service;

import org.example.project.model.Reservation;
import org.example.project.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Transactional(readOnly = true)
    public Optional<Reservation> findByReservationNumberWithDetails(String reservationNumber) {
        return reservationRepository.findByReservationNumberWithDetails(reservationNumber);
    }

    @Transactional(readOnly = true)
    public List<Reservation> findReservationsByPassengerIdWithFlights(Long passengerId) {
        if (passengerId == null) {
            return new ArrayList<>();
        }
        return reservationRepository.findByPassengerIdWithFlights(passengerId);
    }
}