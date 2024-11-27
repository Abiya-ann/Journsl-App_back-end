package com.example.journal.Service;

import com.example.journal.DTO.PerformanceReportsDTO;
import com.example.journal.Entity.PerformanceReports;
import com.example.journal.Repository.PerformanceReportsRepository;
import com.example.journal.Repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PerformanceReportsService {

    private final PerformanceReportsRepository performanceReportsRepository;
    private final UsersRepository usersRepository;

    public PerformanceReportsService(PerformanceReportsRepository performanceReportsRepository, UsersRepository usersRepository) {
        this.performanceReportsRepository = performanceReportsRepository;
        this.usersRepository = usersRepository;
    }

    // Convert Entity to DTO
    private PerformanceReportsDTO convertToDTO(PerformanceReports report) {
        return new PerformanceReportsDTO(
                report.getReportId(),
                report.getGeneratedAt(),
                report.getUpdatedAt(),
                report.getCompletionRate(),
                report.getUser().getUserId() // Assuming you only need userId here
        );
    }

    // Get Performance Report by Id
    public Optional<PerformanceReportsDTO> getPerformanceReportsById(Long reportId) {
        Optional<PerformanceReports> report = performanceReportsRepository.findById(reportId);
        return report.map(this::convertToDTO);
    }

    // Get all Performance Reports
    public List<PerformanceReportsDTO> getAllPerformanceReports() {
        return performanceReportsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public List<PerformanceReportsDTO> getPerformanceReportsByUserId(Long userId) {
        usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return performanceReportsRepository.findByUser_UserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deletePerformanceReport(Long reportId) {
        performanceReportsRepository.deleteById(reportId);
    }

    public PerformanceReportsDTO updatePerformanceReports(Long reportId, BigDecimal completionRate) {
        PerformanceReports performanceReports = performanceReportsRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        performanceReports.setCompletionRate(completionRate);
        performanceReports.setUpdatedAt(LocalDateTime.now());
        performanceReports = performanceReportsRepository.save(performanceReports);

        return convertToDTO(performanceReports); // Return updated report as DTO
    }

    public PerformanceReportsDTO createPerformanceReport(PerformanceReports performanceReports) {
        performanceReports.setGeneratedAt(LocalDateTime.now());
        performanceReports.setUpdatedAt(LocalDateTime.now());
        performanceReports = performanceReportsRepository.save(performanceReports);
        return convertToDTO(performanceReports);
    }
}
