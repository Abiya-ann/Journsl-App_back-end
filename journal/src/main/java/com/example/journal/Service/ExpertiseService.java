package com.example.journal.Service;

import com.example.journal.DTO.ExpertiseDTO;
import com.example.journal.DTO.UserExpertiseDTO;
import com.example.journal.Entity.Expertise;
import com.example.journal.Repository.ExpertiseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExpertiseService {
    private final ExpertiseRepository expertiseRepository;

    public ExpertiseService(ExpertiseRepository expertiseRepository) {
        this.expertiseRepository = expertiseRepository;
    }

    // Convert Expertise to ExpertiseDTO
    private ExpertiseDTO convertToDTO(Expertise expertise) {
        // Convert the related UserExpertise entities into UserExpertiseDTO
        Set<UserExpertiseDTO> userExpertiseDTOs = expertise.getUserExpertises().stream()
                .map(userExpertise -> new UserExpertiseDTO(
                        userExpertise.getUser().getUserId(), userExpertise.getExpertise().getExpertiseId()
                )).collect(Collectors.toSet());

        return new ExpertiseDTO(
                expertise.getExpertiseId(),
                expertise.getName()

        );
    }

    public List<ExpertiseDTO> getAllExpertise() {
        // Fetch all expertise and convert to DTO
        List<Expertise> expertises = expertiseRepository.findAll();
        return expertises.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ExpertiseDTO getExpertiseById(Long expertiseId) {
        // Fetch expertise by ID and convert to DTO
        Expertise expertise = expertiseRepository.findById(expertiseId)
                .orElseThrow(() -> new RuntimeException("There is no expertise with id " + expertiseId));
        return convertToDTO(expertise);
    }

    public void deleteExpertise(Long expertiseId) {
        // Deleting expertise
        expertiseRepository.deleteById(expertiseId);
    }

    public ExpertiseDTO updateExpertise(Long expertiseId, Expertise expertiseDetails) {
        // Retrieve the expertise to be updated
        Expertise expertise = expertiseRepository.findById(expertiseId)
                .orElseThrow(() -> new RuntimeException("There is no expertise with id " + expertiseId));

        // Update the fields
        if (expertiseDetails.getName() != null) {
            expertise.setName(expertiseDetails.getName());
        }

        // Save the updated expertise and convert it to DTO
        Expertise updatedExpertise = expertiseRepository.save(expertise);
        return convertToDTO(updatedExpertise);
    }

    public ExpertiseDTO createExpertise(Expertise expertise) {
        // Create the new expertise and convert to DTO
        Expertise createdExpertise = expertiseRepository.save(expertise);
        return convertToDTO(createdExpertise);
    }
}
