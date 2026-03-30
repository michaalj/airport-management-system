package org.example.project.service;

import org.example.project.model.Flight;
import org.example.project.model.Passenger;
import org.example.project.model.Reservation;
import org.example.project.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Transactional(readOnly = true)
    public List<Flight> getAllFlightsAndInitializeDetails() {
        List<Flight> flights = flightRepository.findAll();
        for (Flight flight : flights) {
            if (flight.getReservations() != null) {
                flight.getReservations().size();
                for (Reservation reservation : flight.getReservations()) {
                    if (reservation.getPassengers() != null) {
                        reservation.getPassengers().size();
                        for (Passenger passenger : reservation.getPassengers()) {
                            if (passenger.getPerson() != null) {
                                passenger.getPerson().getFirstName();
                            }
                        }
                    }
                }
            }
        }
        return flights;
    }
}