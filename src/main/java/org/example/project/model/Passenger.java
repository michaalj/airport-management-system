package org.example.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private int identityDocument;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private Person person;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CheckIn> checkIns = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "passenger_reservation",
            joinColumns = @JoinColumn(name = "passenger_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id")
    )
    private Set<Reservation> reservations = new HashSet<>();

    public void displayPersonalData() {
        person.displayPersonalData();
        System.out.println("Passenger – document: " + identityDocument);
        System.out.print("Reservations: ");
        if (reservations != null && !reservations.isEmpty()) {
            reservations.forEach(r -> System.out.print(r.getReservationNumber() + " "));
        } else {
            System.out.print("None");
        }
        System.out.println();
    }

}
