package org.example.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"flight_id", "checkin_id"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardingPass {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String boardingPassNumber;

    private String travelClass;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight assignedFlight;

    private String seat;

    @ManyToOne
    @JoinColumn(name = "checkin_id")
    private CheckIn checkIn;

    public Long getId() {
        return id;
    }

    public String getBoardingPassNumber() {
        return boardingPassNumber;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public Flight getAssignedFlight() {
        return assignedFlight;
    }

    public String getSeat() {
        return seat;
    }

    public CheckIn getCheckIn() {
        return checkIn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBoardingPassNumber(String boardingPassNumber) {
        this.boardingPassNumber = boardingPassNumber;
    }

    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }

    public void setAssignedFlight(Flight assignedFlight) {
        this.assignedFlight = assignedFlight;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public void setCheckIn(CheckIn checkIn) {
        this.checkIn = checkIn;
    }
}

