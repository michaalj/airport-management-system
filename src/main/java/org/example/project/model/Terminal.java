package org.example.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Terminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String number;

    @ManyToMany
    @JoinTable(
            name = "terminal_gate",
            joinColumns = @JoinColumn(name = "terminal_id"),
            inverseJoinColumns = @JoinColumn(name = "gate_id")
    )
    private Set<Gate> gates = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "terminal_checkin",
            joinColumns = @JoinColumn(name = "terminal_id"),
            inverseJoinColumns = @JoinColumn(name = "checkin_id")
    )
    private Set<CheckIn> checkIns = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "terminal_checkin_desks", joinColumns = @JoinColumn(name = "terminal_id"))
    @Column(name = "checkin_desk")
    private Set<String> checkInDesks = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "terminal_airlines", joinColumns = @JoinColumn(name = "terminal_id"))
    @Column(name = "airline")
    private Set<String> supportedAirlines = new HashSet<>();

}
