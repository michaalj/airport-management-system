package org.example.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_number", unique = true, nullable = false)
    private String registrationNumber;

    @NotBlank
    private String model;

    @NotNull
    private int capacity;

    @ManyToMany(mappedBy = "planes")
    private Set<Flight> flights = new HashSet<>();

    @OneToMany(mappedBy = "plane", cascade = CascadeType.REMOVE)
    private Set<Engine> engines = new HashSet<>();
    @ToString.Exclude
    @EqualsAndHashCode.Exclude

    @OneToMany(mappedBy = "plane", cascade = CascadeType.ALL)
    private Set<MaintenanceHistory> maintenanceHistories = new HashSet<>();


    public boolean isReadyToFly() {
        return !engines.isEmpty();
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

}
