package com.example.journal.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceReportsDTO {

    private Long reportId;
    private LocalDateTime generatedAt;
    private LocalDateTime updatedAt;
    private BigDecimal completionRate;
    private Long userId;  // Optionally include userId for reference
}
