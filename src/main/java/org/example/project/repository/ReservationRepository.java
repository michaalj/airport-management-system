package org.example.project.repository;

import org.example.project.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r " +
            "LEFT JOIN FETCH r.passengers p " +
            "LEFT JOIN FETCH p.person prs " +
            "LEFT JOIN FETCH r.flights f " +
            "WHERE r.reservationNumber = :reservationNumber")
    Optional<Reservation> findByReservationNumberWithDetails(@Param("reservationNumber") String reservationNumber);

    Optional<Reservation> findByReservationNumber(String reservationNumber);

    @Query("SELECT DISTINCT r FROM Reservation r JOIN r.passengers p LEFT JOIN FETCH r.flights WHERE p.id = :passengerId")
    List<Reservation> findByPassengerIdWithFlights(@Param("passengerId") Long passengerId);

}
