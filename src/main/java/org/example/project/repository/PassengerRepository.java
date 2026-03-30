package org.example.project.repository;

import org.example.project.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByIdentityDocument(int identityDocument);
}