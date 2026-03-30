package org.example.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"plane_id", "technical_support_id"}))
public class MaintenanceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plane_id", nullable = false)
    private Plane plane;

    @ManyToOne
    @JoinColumn(name = "technical_support_id", nullable = false)
    private TechnicalSupport technicalSupport;

    @Size(max = 500)
    private String inspectionName;

    @NotNull
    private LocalDate fromDate;

    private LocalDate toDate;

    public String showDetails() {
        return "Inspection: " + inspectionName +
                ", Plane: " + plane.getRegistrationNumber() +
                ", Performed by: " + technicalSupport.getId() +
                ", From: " + fromDate +
                ", To: " + toDate;
    }
}
