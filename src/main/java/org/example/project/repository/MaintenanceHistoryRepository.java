package org.example.project.repository;

import org.example.project.model.MaintenanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceHistoryRepository extends JpaRepository<MaintenanceHistory, Long> {
}