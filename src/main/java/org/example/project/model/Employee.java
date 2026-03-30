package org.example.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 11, max = 11)
    private String pesel;

    @NotNull
    private double salary;
    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private Person person;

    @ManyToMany(mappedBy = "employees")
    private Set<CheckIn> checkIns = new HashSet<>();

    public void displayPersonalData() {
        if (this.person != null) {
            this.person.displayPersonalData();
        } else {
            System.out.println("Personal data for employee not available");
        }
        System.out.println("Employee - PESEL: " + pesel + ", Salary: " + salary +
                ", Start Date: " + startDate + (endDate != null ? ", End Date: " + endDate : ""));
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
