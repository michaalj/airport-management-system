package org.example.project.service;

import org.example.project.model.*;
import org.example.project.repository.CheckInRepository;
import org.example.project.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckInService {

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Transactional
    public CheckIn findOrCreateInProgressCheckIn(String reservationNumber) {
        Reservation reservation = reservationRepository.findByReservationNumberWithDetails(reservationNumber)
                .orElseThrow(() -> new IllegalArgumentException("Rezerwacja o numerze " + reservationNumber + " nie została znaleziona."));

        Passenger passenger = reservation.getPassengers().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Rezerwacja " + reservationNumber + " nie ma przypisanych pasażerów."));

        CheckIn checkIn = checkInRepository.findByReservationNumber(reservationNumber)
                .orElseGet(() -> {
                    CheckIn newCheckIn = new CheckIn();
                    newCheckIn.setReservationNumber(reservationNumber);
                    newCheckIn.setPassenger(passenger);
                    return newCheckIn;
                });

        if (checkIn.getStatus() != CheckInStatus.COMPLETED) {
            checkIn.setStatus(CheckInStatus.IN_PROGRESS);
        }

        return checkInRepository.save(checkIn);
    }

    @Transactional
    public CheckIn completeCheckIn(CheckIn checkIn) {
        if (checkIn == null) {
            throw new IllegalArgumentException("CheckIn nie może być null.");
        }
        checkIn.setStatus(CheckInStatus.COMPLETED);
        return checkInRepository.save(checkIn);
    }

    @Transactional
    public void cancelCheckInProcess(String reservationNumber) {
        if (reservationNumber == null || reservationNumber.isEmpty()) {
            return;
        }
        checkInRepository.findByReservationNumber(reservationNumber).ifPresent(checkIn -> {
            if (checkIn.getStatus() == CheckInStatus.IN_PROGRESS) {
                checkIn.setStatus(CheckInStatus.NOT_COMPLETED);
                checkInRepository.save(checkIn);
            }
        });
    }

    @Transactional(readOnly = true)
    public CheckInStatus getCheckInStatus(String reservationNumber) {
        return checkInRepository.findByReservationNumber(reservationNumber)
                .map(CheckIn::getStatus)
                .orElse(null);
    }

}