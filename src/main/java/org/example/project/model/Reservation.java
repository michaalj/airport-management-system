package org.example.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String reservationNumber;

    @ManyToMany()
    @JoinTable(
            name = "reservation_flights",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id")
    )
    private Set<Flight> flights = new HashSet<>();

    @ManyToMany(mappedBy = "reservations")
    private Set<Passenger> passengers = new HashSet<>();

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Baggage> baggageList = new HashSet<>();

    public Long getId() { return id; }
    public String getReservationNumber() { return reservationNumber; }
    public Set<Flight> getFlights() { return flights; }
    public Set<Baggage> getBaggageList() { return baggageList; }
    public Set<Passenger> getPassengers() { return passengers; }

}
