package com.example.journal.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "performance_reports")
public class PerformanceReports {

    @Id

    private Long reportId;

    private LocalDateTime generatedAt;

    private LocalDateTime updatedAt;

    private BigDecimal completionRate;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private Users user;
}
