package org.example.project.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.project.model.Passenger;
import org.example.project.model.Reservation;
import org.example.project.service.CheckInService;
import org.example.project.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private CheckInService checkInService;

    @GetMapping("/my-flights")
    public String showMyFlights(HttpSession session, Model model) {
        Object userObj = session.getAttribute("loggedInUser");
        String userRole = (String) session.getAttribute("userRole");

        if (!"PASSENGER".equals(userRole) || !(userObj instanceof Passenger)) {
            return "redirect:/auth/passenger/login?error=Proszę+się+zalogować";
        }

        Passenger passenger = (Passenger) userObj;

        List<Reservation> reservations = reservationService.findReservationsByPassengerIdWithFlights(passenger.getId());

        model.addAttribute("passenger", passenger);
        model.addAttribute("reservations", reservations);
        model.addAttribute("checkInService", checkInService);

        return "passenger/my-flights";
    }
}