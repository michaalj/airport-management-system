package org.example.project.service;

import org.example.project.model.BoardingPass;
import org.example.project.model.CheckIn;
import org.example.project.model.Flight;
import org.example.project.repository.BoardingPassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class BoardingPassService {

    @Autowired
    private BoardingPassRepository boardingPassRepository;

    @Transactional
    public BoardingPass generateBoardingPass(CheckIn checkIn, Flight flight, String seat, String travelClass) {
        if (checkIn == null || checkIn.getId() == null) {
            throw new IllegalStateException("CheckIn musi być utrwalony i posiadać ID przed wygenerowaniem karty pokładowej.");
        }
        if (flight == null || flight.getId() == null) {
            throw new IllegalArgumentException("Flight nie może być null i musi posiadać ID.");
        }
        if (seat == null || seat.isEmpty()) {
            throw new IllegalArgumentException("Miejsce (seat) nie może być puste.");
        }

        BoardingPass boardingPass = new BoardingPass();

        String uniqueBoardingPassNumber = "BP-" +
                (flight.getNumber() != null ? flight.getNumber().replaceAll("[^A-Za-z0-9]", "") : "FL") +
                "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        boardingPass.setBoardingPassNumber(uniqueBoardingPassNumber);

        boardingPass.setCheckIn(checkIn);
        boardingPass.setAssignedFlight(flight);
        boardingPass.setSeat(seat);
        boardingPass.setTravelClass(travelClass);

        return boardingPassRepository.save(boardingPass);
    }

    @Transactional(readOnly = true)
    public Optional<BoardingPass> findById(Long id) {
        return boardingPassRepository.findById(id);
    }
}