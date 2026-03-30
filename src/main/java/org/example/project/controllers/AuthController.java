package org.example.project.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.project.model.Employee;
import org.example.project.model.Passenger;
import org.example.project.service.EmployeeService;
import org.example.project.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/employee/login")
    public String showEmployeeLoginForm(Model model) {
        if (!model.containsAttribute("pesel")) {
            model.addAttribute("pesel", "");
        }
        return "auth/employee-login";
    }

    @PostMapping("/employee/authenticate")
    public String authenticateEmployee(@RequestParam String pesel,
                                       RedirectAttributes redirectAttributes,
                                       HttpSession session) {
        if (pesel == null || !pesel.matches("\\d{11}")) {
            redirectAttributes.addFlashAttribute("error", "Niepoprawny format numeru PESEL. Oczekiwano 11 cyfr.");
            redirectAttributes.addFlashAttribute("pesel", pesel);
            return "redirect:/auth/employee/login";
        }

        Optional<Employee> employeeOpt = employeeService.findByPesel(pesel);
        if (employeeOpt.isPresent()) {
            session.setAttribute("userRole", "EMPLOYEE");
            session.setAttribute("loggedInUser", employeeOpt.get());
            return "redirect:/employee/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Nie znaleziono pracownika o podanym numerze PESEL lub konto jest nieaktywne.");
            redirectAttributes.addFlashAttribute("pesel", pesel);
            return "redirect:/auth/employee/login";
        }
    }

    @GetMapping("/passenger/login")
    public String showPassengerLoginForm(Model model) {
        if (!model.containsAttribute("identityDocument")) {
            model.addAttribute("identityDocument", "");
        }
        return "auth/passenger-login";
    }

    @PostMapping("/passenger/authenticate")
    public String authenticatePassenger(@RequestParam String identityDocument,
                                        RedirectAttributes redirectAttributes,
                                        HttpSession session) {
        int docNumber;
        try {
            docNumber = Integer.parseInt(identityDocument.replaceAll("\\s+",""));
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("error", "Niepoprawny format numeru dokumentu.");
            redirectAttributes.addFlashAttribute("identityDocument", identityDocument);
            return "redirect:/auth/passenger/login";
        }

        Optional<Passenger> passengerOpt = passengerService.findByIdentityDocument(docNumber);
        if (passengerOpt.isPresent()) {
            session.setAttribute("userRole", "PASSENGER");
            session.setAttribute("loggedInUser", passengerOpt.get());
            return "redirect:/passenger/my-flights";
        } else {
            redirectAttributes.addFlashAttribute("error", "Nie znaleziono pasażera o podanym numerze dokumentu.");
            redirectAttributes.addFlashAttribute("identityDocument", identityDocument);
            return "redirect:/auth/passenger/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}