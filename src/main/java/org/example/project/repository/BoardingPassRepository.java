package org.example.project.repository;

import org.example.project.model.BoardingPass;
import org.example.project.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardingPassRepository extends JpaRepository<BoardingPass, Long> {
    List<BoardingPass> findAllByAssignedFlight(Flight flight);
    Optional<BoardingPass> findByBoardingPassNumber(String boardingPassNumber);
}
