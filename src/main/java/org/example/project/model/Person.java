package org.example.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private LocalDate birthDate;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Passenger passenger;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Employee employee;

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public void displayPersonalData() {
        System.out.println("Person: " + firstName + " " + lastName + ", birth: " + birthDate + " (age: " + getAge() + ")");
    }

}
