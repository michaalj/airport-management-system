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
@NoArgsConstructor
@AllArgsConstructor
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CheckInStatus status;

    @Column(nullable = false, unique = true)
    private String reservationNumber;

    @ManyToOne(optional = false)
    private Passenger passenger;

    @ManyToMany
    @JoinTable(
            name = "checkin_employee",
            joinColumns = @JoinColumn(name = "checkin_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employee> employees = new HashSet<>();

    @ManyToMany(mappedBy = "checkIns")
    private Set<Terminal> terminals = new HashSet<>();

    @OneToMany(mappedBy = "checkIn", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BoardingPass> boardingPasses = new HashSet<>();

    public Long getId() {return id;}

    public String getReservationNumber() {
        return reservationNumber;
    }

    public CheckInStatus getStatus() {
        return status;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Set<BoardingPass> getBoardingPasses() {
        return boardingPasses;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public void setStatus(CheckInStatus status) {
        this.status = status;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}

