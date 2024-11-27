package com.example.journal.Controller;

import com.example.journal.DTO.ExpertiseDTO;
import com.example.journal.Entity.Expertise;
import com.example.journal.Service.ExpertiseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expertises")
public class ExpertiseController {

    private final ExpertiseService expertiseService;

    public ExpertiseController(ExpertiseService expertiseService) {
        this.expertiseService = expertiseService;
    }

    // Get all expertise
    @GetMapping
    public List<ExpertiseDTO> getAllExpertise() {
        return expertiseService.getAllExpertise();
    }

    // Get expertise by ID
    @GetMapping("/{expertiseId}")
    public ResponseEntity<ExpertiseDTO> getExpertiseById(@PathVariable Long expertiseId) {
        ExpertiseDTO expertise = expertiseService.getExpertiseById(expertiseId);
        return ResponseEntity.ok(expertise);
    }

    // Create expertise
    @PostMapping
    public ResponseEntity<ExpertiseDTO> createExpertise(@RequestBody Expertise expertise) {
        ExpertiseDTO createdExpertise = expertiseService.createExpertise(expertise);
        return ResponseEntity.ok(createdExpertise);
    }

    // Update expertise
    @PutMapping("/{expertiseId}")
    public ResponseEntity<ExpertiseDTO> updateExpertise(@PathVariable Long expertiseId, @RequestBody Expertise expertiseDetails) {
        ExpertiseDTO updatedExpertise = expertiseService.updateExpertise(expertiseId, expertiseDetails);
        return ResponseEntity.ok(updatedExpertise);
    }

    // Delete expertise
    @DeleteMapping("/{expertiseId}")
    public ResponseEntity<Void> deleteExpertise(@PathVariable Long expertiseId) {
        expertiseService.deleteExpertise(expertiseId);
        return ResponseEntity.noContent().build();  // 204 No Content
    }
}
