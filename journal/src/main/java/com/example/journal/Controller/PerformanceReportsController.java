package com.example.journal.Controller;

import com.example.journal.DTO.PerformanceReportsDTO;
import com.example.journal.Entity.PerformanceReports;
import com.example.journal.Service.PerformanceReportsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/performance-reports")
public class PerformanceReportsController {

    private final PerformanceReportsService performanceReportsService;

    public PerformanceReportsController(PerformanceReportsService performanceReportsService) {
        this.performanceReportsService = performanceReportsService;
    }

    // Get all performance reports
    @GetMapping
    public List<PerformanceReportsDTO> getAllPerformanceReports() {
        return performanceReportsService.getAllPerformanceReports();
    }

    // Get performance report by ID
    @GetMapping("/{reportId}")
    public Optional<PerformanceReportsDTO> getPerformanceReportById(@PathVariable Long reportId) {
        return performanceReportsService.getPerformanceReportsById(reportId);
    }

    // Get performance reports by User ID
    @GetMapping("/user/{userId}")
    public List<PerformanceReportsDTO> getPerformanceReportsByUserId(@PathVariable Long userId) {
        return performanceReportsService.getPerformanceReportsByUserId(userId);
    }

    // Create new performance report
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PerformanceReportsDTO createPerformanceReport(@RequestBody PerformanceReports performanceReports) {
        return performanceReportsService.createPerformanceReport(performanceReports);
    }

    // Update performance report's completion rate
    @PutMapping("/{reportId}")
    public PerformanceReportsDTO updatePerformanceReport(@PathVariable Long reportId, @RequestParam BigDecimal completionRate) {
        return performanceReportsService.updatePerformanceReports(reportId, completionRate);
    }

    // Delete performance report
    @DeleteMapping("/{reportId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerformanceReport(@PathVariable Long reportId) {
        performanceReportsService.deletePerformanceReport(reportId);
    }
}
