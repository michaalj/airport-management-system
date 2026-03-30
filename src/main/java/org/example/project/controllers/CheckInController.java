package org.example.project.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.project.model.*;
import org.example.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/checkin")
public class CheckInController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private BoardingPassService boardingPassService;
    @Autowired
    private SeatService seatService;
    @Autowired
    private CheckInService checkInService;

    @GetMapping
    public String startCheckin() {
        return "checkin/start";
    }

    @GetMapping("/form")
    public String showForm(Model model,
                           @RequestParam(required = false) String error,
                           @RequestParam(name = "reservationNumber", required = false) String reservationNumberFromUrl) {

        String initialReservationNumber = "";
        if (model.containsAttribute("reservationNumber")) {
            initialReservationNumber = (String) model.asMap().get("reservationNumber");
        } else if (reservationNumberFromUrl != null && !reservationNumberFromUrl.isEmpty()) {
            initialReservationNumber = reservationNumberFromUrl;
        }
        model.addAttribute("reservationNumber", initialReservationNumber);

        if (!model.containsAttribute("error") && error != null) {
            model.addAttribute("error", error);
        }

        return "checkin/form";
    }

    @PostMapping("/initiate")
    public String initiateCheckIn(@RequestParam String reservationNumber) {
        checkInService.findOrCreateInProgressCheckIn(reservationNumber);
        return "redirect:/checkin/confirm?reservationNumber=" + reservationNumber;
    }

    @GetMapping("/confirm")
    public String showConfirmPage(@RequestParam String reservationNumber, Model model, RedirectAttributes redirectAttributes) {
        Optional<Reservation> reservationOpt = reservationService.findByReservationNumberWithDetails(reservationNumber);
        if (reservationOpt.isPresent()) {
            model.addAttribute("reservation", reservationOpt.get());
            return "checkin/confirm";
        } else {
            redirectAttributes.addFlashAttribute("error", "Rezerwacja o numerze '" + reservationNumber + "' nie została znaleziona.");
            return "redirect:/checkin/form";
        }
    }

    @PostMapping("/validate")
    public String validateReservation(@RequestParam String reservationNumber, RedirectAttributes redirectAttributes) {
        String processedReservationNumber = reservationNumber != null ? reservationNumber.trim().split(",")[0] : "";
        if (processedReservationNumber.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Numer rezerwacji nie może być pusty.");
            redirectAttributes.addFlashAttribute("reservationNumber", reservationNumber);
            return "redirect:/checkin/form";
        }

        Optional<Reservation> reservationOpt = reservationService.findByReservationNumberWithDetails(processedReservationNumber);

        if (reservationOpt.isPresent()) {
            checkInService.findOrCreateInProgressCheckIn(processedReservationNumber);
            return "redirect:/checkin/confirm?reservationNumber=" + processedReservationNumber;
        } else {
            redirectAttributes.addFlashAttribute("error", "Rezerwacja o numerze '" + processedReservationNumber + "' nie została znaleziona.");
            redirectAttributes.addFlashAttribute("reservationNumber", reservationNumber);
            return "redirect:/checkin/form";
        }
    }

    @PostMapping("/seat-selection")
    public String seatSelection(@RequestParam String reservationNumber, Model model, RedirectAttributes redirectAttributes) {
        Optional<Reservation> reservationOpt = reservationService.findByReservationNumberWithDetails(reservationNumber);
        if (reservationOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Nie znaleziono rezerwacji.");
            redirectAttributes.addFlashAttribute("reservationNumber", reservationNumber);
            return "redirect:/checkin/form";
        }

        Reservation reservation = reservationOpt.get();
        Flight flight = reservation.getFlights().stream().findFirst().orElse(null);

        if (flight == null) {
            redirectAttributes.addFlashAttribute("error", "Do rezerwacji nie jest przypisany żaden lot.");
            redirectAttributes.addFlashAttribute("reservationNumber", reservationNumber);
            return "redirect:/checkin/form";
        }

        List<String> availableSeats = seatService.getAvailableSeats(flight);
        model.addAttribute("availableSeats", availableSeats);
        model.addAttribute("reservationNumber", reservationNumber);
        model.addAttribute("flightId", flight.getId());
        return "checkin/select-seat";
    }

    @PostMapping("/generate")
    public String generateBoardingPass(@RequestParam String reservationNumber,
                                       @RequestParam(name = "flightId", required = false) Long flightId,
                                       @RequestParam(required = false) String selectedSeat,
                                       @RequestParam(required = false) String autoAssign,
                                       Model model, RedirectAttributes redirectAttributes) {

        Optional<Reservation> reservationOpt = reservationService.findByReservationNumberWithDetails(reservationNumber);
        if (reservationOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Nie znaleziono rezerwacji " + reservationNumber);
            return "redirect:/checkin/form";
        }
        Reservation reservation = reservationOpt.get();

        CheckIn checkIn = checkInService.findOrCreateInProgressCheckIn(reservationNumber);

        if (checkIn.getStatus() == CheckInStatus.COMPLETED) {
            Optional<BoardingPass> existingBoardingPass = checkIn.getBoardingPasses().stream().findFirst();
            if (existingBoardingPass.isPresent()) {
                model.addAttribute("boardingPass", existingBoardingPass.get());
                model.addAttribute("reservationNumber", reservationNumber);
                return "checkin/boarding-pass";
            } else {
                model.addAttribute("error", "Odprawa jest zakończona, ale wystąpił błąd przy pobieraniu karty pokładowej. Proszę skontaktować się z obsługą.");
                List<String> availableSeats = seatService.getAvailableSeats(reservation.getFlights().stream().findFirst().orElse(null));
                model.addAttribute("availableSeats", availableSeats);
                model.addAttribute("reservationNumber", reservationNumber);
                model.addAttribute("flightId", flightId);
                return "checkin/select-seat";
            }
        }

        Flight flight = reservation.getFlights().stream()
                .filter(f -> flightId == null || f.getId().equals(flightId))
                .findFirst()
                .orElse(null);

        if (flight == null) {
            redirectAttributes.addFlashAttribute("error", "Nie można zidentyfikować lotu dla rezerwacji " + reservationNumber);
            return "redirect:/checkin/form";
        }

        String seatToAssign;
        List<String> availableSeatsForView = seatService.getAvailableSeats(flight);

        if (autoAssign != null && autoAssign.equals("true")) {
            seatToAssign = seatService.assignRandomSeat(flight);
            if (seatToAssign == null) {
                model.addAttribute("error", "Brak wolnych miejsc do automatycznego przypisania.");
                model.addAttribute("availableSeats", availableSeatsForView);
                model.addAttribute("reservationNumber", reservationNumber);
                model.addAttribute("flightId", flight.getId());
                return "checkin/select-seat";
            }
        } else if (selectedSeat != null && !selectedSeat.isEmpty()) {
            if (!seatService.isSeatAvailable(flight, selectedSeat)) {
                model.addAttribute("error", "Wybrane miejsce (" + selectedSeat + ") jest już zajęte. Proszę wybrać inne.");
                model.addAttribute("availableSeats", availableSeatsForView);
                model.addAttribute("reservationNumber", reservationNumber);
                model.addAttribute("flightId", flight.getId());
                return "checkin/select-seat";
            }
            seatToAssign = selectedSeat;
        } else {
            model.addAttribute("error", "Proszę wybrać miejsce lub zlecić automatyczne przypisanie.");
            model.addAttribute("availableSeats", availableSeatsForView);
            model.addAttribute("reservationNumber", reservationNumber);
            model.addAttribute("flightId", flight.getId());
            return "checkin/select-seat";
        }

        String travelClass = "EKONOMICZNA";

        try {
            BoardingPass pass = boardingPassService.generateBoardingPass(checkIn, flight, seatToAssign, travelClass);
            checkInService.completeCheckIn(checkIn);

            model.addAttribute("boardingPass", pass);
            model.addAttribute("reservationNumber", reservationNumber);
            return "checkin/boarding-pass";
        } catch (Exception e) {
            model.addAttribute("error", "Wystąpił błąd podczas generowania karty pokładowej: " + e.getMessage());
            model.addAttribute("availableSeats", availableSeatsForView);
            model.addAttribute("reservationNumber", reservationNumber);
            model.addAttribute("flightId", flight.getId());
            return "checkin/select-seat";
        }
    }

    @PostMapping("/cancel")
    public String cancelCheckin(@RequestParam(name = "reservationNumber", required = false) String reservationNumber, HttpSession session) {
        checkInService.cancelCheckInProcess(reservationNumber);
        String userRole = (String) session.getAttribute("userRole");
        if ("EMPLOYEE".equals(userRole)) {
            return "redirect:/employee/dashboard";
        } else if ("PASSENGER".equals(userRole)) {
            return "redirect:/passenger/my-flights";
        }
        return "redirect:/";
    }

    @PostMapping("/finish")
    public String finishCheckInProcess(HttpSession session) {
        String userRole = (String) session.getAttribute("userRole");
        if ("EMPLOYEE".equals(userRole)) {
            return "redirect:/employee/dashboard";
        } else if ("PASSENGER".equals(userRole)) {
            return "redirect:/passenger/my-flights";
        }
        return "redirect:/";
    }
}