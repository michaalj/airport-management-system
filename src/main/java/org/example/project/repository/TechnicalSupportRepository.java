package org.example.project.repository;

import org.example.project.model.TechnicalSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalSupportRepository extends JpaRepository<TechnicalSupport, Long> {
}