package com.example.journal.Service;

import com.example.journal.DTO.ScheduleDTO;
import com.example.journal.Entity.Schedule;
import com.example.journal.Entity.Users;
import com.example.journal.Entity.Event;
import com.example.journal.Repository.ScheduleRepository;
import com.example.journal.Repository.EventRepository;
import com.example.journal.Repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UsersRepository usersRepository;
    private final EventRepository eventRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, UsersRepository usersRepository, EventRepository eventRepository) {
        this.scheduleRepository = scheduleRepository;
        this.usersRepository = usersRepository;
        this.eventRepository = eventRepository;
    }

    // Convert Schedule entity to ScheduleDTO
    private ScheduleDTO convertToDTO(Schedule schedule) {
        return new ScheduleDTO(
                schedule.getScheduleId(),                      // Long
                schedule.getAssignedAt(),                      // LocalDateTime
                Long.valueOf(schedule.getEvent().getEventId()), // Convert int to Long
                Long.valueOf(schedule.getUser().getUserId())   // Convert int to Long
        );
    }


    // Get all schedules assigned to a specific user
    public List<ScheduleDTO> getAllSchedulesFromUser(Long userId) {
        List<Schedule> schedules = scheduleRepository.findByUser_UserId(userId);
        return schedules.stream()
                .map(this::convertToDTO)  // Convert each Schedule entity to ScheduleDTO
                .collect(Collectors.toList());
    }


    public List<ScheduleDTO> getAllUsersAssignedToEvent(Integer eventId) {
        List<Schedule> schedules = scheduleRepository.findByEvent_EventId(eventId);
        return schedules.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public ScheduleDTO assignEventToUser(Long userId, Integer eventId) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));

        Schedule schedule = new Schedule();
        schedule.setUser(user);
        schedule.setEvent(event);
        schedule.setAssignedAt(LocalDateTime.now());

        Schedule savedSchedule = scheduleRepository.save(schedule);

        // Return the ScheduleDTO after saving the schedule
        return convertToDTO(savedSchedule);
    }

    // Remove an event assignment from a user
    public void removeEventFromUser(Long userId, Integer eventId) {
        Schedule schedule = scheduleRepository.findByUser_UserIdAndEvent_EventId(userId, eventId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        scheduleRepository.delete(schedule);
    }
}
