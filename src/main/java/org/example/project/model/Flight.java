package org.example.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_number", unique = true, nullable = false)
    private String number;

    @NotBlank
    private String airline;

    @ManyToMany
    @JoinTable(
            name = "flight_plane",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "plane_id")
    )
    private Set<Plane> planes = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "flight_gate",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "gate_id")
    )
    private Set<Gate> gates = new HashSet<>();

    @ManyToMany(mappedBy = "flights")
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(mappedBy = "assignedFlight", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BoardingPass> boardingPasses = new HashSet<>();


    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'WAW'")
    private String departureAirport = "WAW";

    private String arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

}
