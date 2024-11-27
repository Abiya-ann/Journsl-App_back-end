package com.example.journal.Controller;

import com.example.journal.DTO.ScheduleDTO;
import com.example.journal.Service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<ScheduleDTO>> getAllUsersOfAnEvent(@PathVariable Integer eventId){
        List<ScheduleDTO> schedules =scheduleService.getAllUsersAssignedToEvent(eventId);
        return ResponseEntity.ok(schedules);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ScheduleDTO>> getSchedulesByUser(@PathVariable Long userId) {
        List<ScheduleDTO> schedules = scheduleService.getAllSchedulesFromUser(userId);
        return ResponseEntity.ok(schedules);
    }

    @PostMapping("/assign")
    public ResponseEntity<ScheduleDTO> assignEventToUser(@RequestParam Long userId, @RequestParam Integer eventId) {
        ScheduleDTO scheduleDTO = scheduleService.assignEventToUser(userId, eventId);
        return ResponseEntity.status(201).body(scheduleDTO);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeEventFromUser(@RequestParam Long userId, @RequestParam Integer eventId) {
        scheduleService.removeEventFromUser(userId, eventId);
        return ResponseEntity.ok("Event removed successfully from user");
    }
}
