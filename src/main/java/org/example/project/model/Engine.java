package org.example.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String type;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "plane_id", nullable = false, updatable = false)
    private Plane plane;

}
