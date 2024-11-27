package com.example.journal.Repository;

import com.example.journal.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // Find all schedules for a specific user
    List<Schedule> findByUser_UserId(Long userId);

    // Find all schedules for a specific event
    List<Schedule> findByEvent_EventId(Integer eventId);

    // Find a schedule by user and event (composite key relationship)
    Optional<Schedule> findByUser_UserIdAndEvent_EventId(Long userId, Integer eventId);
}
