package org.example.project.repository;

import org.example.project.model.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {

    @Query("SELECT ci FROM CheckIn ci LEFT JOIN FETCH ci.passenger p LEFT JOIN FETCH p.person WHERE ci.reservationNumber = :reservationNumber")
    Optional<CheckIn> findByReservationNumberWithPassenger(@Param("reservationNumber") String reservationNumber);

    Optional<CheckIn> findByReservationNumber(String reservationNumber);
}