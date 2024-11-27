package com.example.journal.Repository;

import com.example.journal.Entity.PerformanceReports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PerformanceReportsRepository extends JpaRepository<PerformanceReports, Long> {
    List<PerformanceReports> findByUser_UserId(Long userId);
}
