package com.example.journal.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {

    private Long scheduleId;
    private LocalDateTime assignedAt;
    private Long eventId; // Reference to the Event entity
    private Long userId; // Reference to the User entity
}
