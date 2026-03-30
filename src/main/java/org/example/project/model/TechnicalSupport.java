package org.example.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@PrimaryKeyJoinColumn(name = "id")
public class TechnicalSupport extends Employee {

    @Enumerated(EnumType.STRING)
    private TechnicalSupportStatus status;

    @OneToMany(mappedBy = "technicalSupport", cascade = CascadeType.ALL)
    private Set<MaintenanceHistory> maintenanceHistories = new HashSet<>();
}

