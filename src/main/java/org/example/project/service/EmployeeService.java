package org.example.project.service;

import org.example.project.model.Employee;
import org.example.project.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public Optional<Employee> findByPesel(String pesel) {
        if (pesel == null || !pesel.matches("\\d{11}")) {
            return Optional.empty();
        }

        Optional<Employee> employeeOpt = employeeRepository.findByPesel(pesel.trim());

        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            LocalDate endDate = employee.getEndDate();

            if (endDate == null) {
                return employeeOpt;
            }

            if (endDate.isBefore(LocalDate.now())) {
                System.out.println("Próba logowania dla pracownika z wygasłą umową (PESEL: " + pesel + ")");
                return Optional.empty();
            } else {
                return employeeOpt;
            }
        }

        return Optional.empty();
    }
}