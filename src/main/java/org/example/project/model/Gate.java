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
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GateStatus status;

    @ManyToMany(mappedBy = "gates")
    private Set<Flight> flights = new HashSet<>();

    @ManyToMany(mappedBy = "gates")
    private Set<Terminal> terminals = new HashSet<>();

    public boolean isAvailable() {
        return status == GateStatus.AVAILABLE;
    }

    public void release() {
        this.status = GateStatus.AVAILABLE;
    }
}

