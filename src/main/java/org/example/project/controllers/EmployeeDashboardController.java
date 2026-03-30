package org.example.project.controllers;

import org.example.project.model.Flight;
import org.example.project.model.Reservation;
import org.example.project.service.CheckInService;
import org.example.project.service.FlightService;
import org.example.project.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeDashboardController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private CheckInService checkInService;
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/dashboard")
    public String employeeDashboard(Model model) {
        List<Flight> flights = flightService.getAllFlightsAndInitializeDetails();
        model.addAttribute("flights", flights);
        model.addAttribute("dashboardTitle", "Panel Pracownika - Lista Lotów");
        model.addAttribute("checkInService", checkInService);
        return "index";
    }

    @PostMapping("/checkin/initiate")
    public String initiateCheckInForEmployee(@RequestParam String reservationNumber) {
        checkInService.findOrCreateInProgressCheckIn(reservationNumber);
        return "redirect:/employee/checkin/enter-document?reservationNumber=" + reservationNumber;
    }

    @GetMapping("/checkin/enter-document")
    public String showEnterDocumentForm(@RequestParam String reservationNumber, Model model) {
        model.addAttribute("reservationNumber", reservationNumber);
        if (!model.containsAttribute("identityDocument")) {
            model.addAttribute("identityDocument", "");
        }
        return "employee/enter-document";
    }

    @PostMapping("/checkin/process-document")
    public String processPassengerDocument(@RequestParam String reservationNumber,
                                           @RequestParam String identityDocument,
                                           RedirectAttributes redirectAttributes) {
        Optional<Reservation> reservationOpt = reservationService.findByReservationNumberWithDetails(reservationNumber);
        if (reservationOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Nie znaleziono rezerwacji o numerze: " + reservationNumber);
            return "redirect:/employee/dashboard";
        }
        Reservation reservation = reservationOpt.get();

        int docNumber;
        try {
            docNumber = Integer.parseInt(identityDocument.replaceAll("\\s+",""));
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("error", "Niepoprawny format numeru dokumentu.");
            redirectAttributes.addFlashAttribute("reservationNumber", reservationNumber);
            redirectAttributes.addFlashAttribute("identityDocument", identityDocument);
            return "redirect:/employee/checkin/enter-document?reservationNumber=" + reservationNumber;
        }

        boolean passengerFoundOnReservation = reservation.getPassengers().stream()
                .anyMatch(p -> p.getIdentityDocument() == docNumber);

        if (passengerFoundOnReservation) {
            return "redirect:/checkin/form?reservationNumber=" + reservationNumber;
        } else {
            redirectAttributes.addFlashAttribute("error", "Pasażer o podanym numerze dokumentu nie został znaleziony na tej rezerwacji (" + reservationNumber + ").");
            redirectAttributes.addFlashAttribute("reservationNumber", reservationNumber);
            redirectAttributes.addFlashAttribute("identityDocument", identityDocument);
            return "redirect:/employee/checkin/enter-document?reservationNumber=" + reservationNumber;
        }
    }
}