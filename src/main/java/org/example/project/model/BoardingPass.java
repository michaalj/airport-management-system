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

}

