package com.example.journal.Service;

import com.example.journal.DTO.EventDTO;
import com.example.journal.Entity.Event;
import com.example.journal.Repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // Convert Entity to DTO
    private EventDTO convertToDTO(Event event) {
        return new EventDTO(
                event.getEventId(),
                event.getEventName(),
                event.getDescription(),
                event.getLocation(),
                event.getStatus(),
                event.getCreatedAt(),
                event.getUpdatedAt(),
                event.getTemplateData()
        );
    }

    // Convert List of Entities to List of DTOs
    private List<EventDTO> convertToDTOs(List<Event> events) {
        return events.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Retrieve all events
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return convertToDTOs(events);
    }

    // Retrieve an event by ID
    public EventDTO getEventById(Integer eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("No such event with id " + eventId));
        return convertToDTO(event);
    }

    // Delete an event by ID
    public void deleteEvent(Integer eventId) {
        eventRepository.deleteById(eventId);
    }

    // Create a new event
    public EventDTO createEvent(Event event) {
        event.setCreatedAt(LocalDateTime.now());
        event.setUpdatedAt(LocalDateTime.now());
        Event savedEvent = eventRepository.save(event);
        return convertToDTO(savedEvent);
    }

    // Update an existing event
    @Transactional
    public EventDTO updateEvent(Integer eventId, Event eventDetails) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("No such event with id " + eventId));

        if (eventDetails.getEventName() != null) {
            event.setEventName(eventDetails.getEventName());
        }

        if (eventDetails.getDescription() != null) {
            event.setDescription(eventDetails.getDescription());
        }

        if (eventDetails.getLocation() != null) {
            event.setLocation(eventDetails.getLocation());
        }

        if (eventDetails.getStatus() != null) {
            event.setStatus(eventDetails.getStatus());
        }

        if (eventDetails.getTemplateData() != null) {
            event.setTemplateData(eventDetails.getTemplateData());
        }

        event.setUpdatedAt(LocalDateTime.now());

        Event updatedEvent = eventRepository.save(event);
        return convertToDTO(updatedEvent);
    }
}
