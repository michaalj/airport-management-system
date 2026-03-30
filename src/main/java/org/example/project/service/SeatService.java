package org.example.project.service;

import org.example.project.model.BoardingPass;
import org.example.project.model.Flight;
import org.example.project.repository.BoardingPassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeatService {

    @Autowired
    private BoardingPassRepository boardingPassRepository;

    private static final List<String> ALL_SEATS = List.of(
            "1A", "1B", "1C", "1D", "1E", "1F",
            "2A", "2B", "2C", "2D", "2E", "2F",
            "3A", "3B", "3C", "3D", "3E", "3F"
    );

    public List<String> getAllSeatsForFlight(Flight flight) {
        return ALL_SEATS;
    }

    @Transactional(readOnly = true)
    public List<String> getAvailableSeats(Flight flight) {
        if (flight == null) {
            return List.of();
        }
        Set<String> takenSeats = boardingPassRepository.findAllByAssignedFlight(flight)
                .stream()
                .map(BoardingPass::getSeat)
                .filter(java.util.Objects::nonNull)
                .collect(Collectors.toSet());

        return getAllSeatsForFlight(flight).stream()
                .filter(seat -> !takenSeats.contains(seat))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public boolean isSeatAvailable(Flight flight, String seat) {
        if (flight == null || seat == null || seat.isEmpty()) {
            return false;
        }
        return getAvailableSeats(flight).contains(seat);
    }

    @Transactional(readOnly = true)
    public String assignRandomSeat(Flight flight) {
        List<String> availableSeats = getAvailableSeats(flight);
        if (availableSeats.isEmpty()) {
            return null;
        }
        return availableSeats.get(new Random().nextInt(availableSeats.size()));
    }
}