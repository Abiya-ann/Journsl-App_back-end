package com.example.journal.Service;

import com.example.journal.DTO.UserExpertiseDTO;
import com.example.journal.DTO.UserRolesDTO;
import com.example.journal.Entity.*;
import com.example.journal.Repository.ExpertiseRepository;
import com.example.journal.Repository.UserExpertiseRepository;
import com.example.journal.Repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserExpertiseService {

    private final UsersRepository usersRepository;
    private final ExpertiseRepository expertiseRepository;
    private final UserExpertiseRepository userExpertiseRepository;

    public UserExpertiseService(UsersRepository usersRepository, ExpertiseRepository expertiseRepository, UserExpertiseRepository userExpertiseRepository) {
        this.usersRepository = usersRepository;
        this.expertiseRepository = expertiseRepository;
        this.userExpertiseRepository = userExpertiseRepository;
    }
    private UserExpertiseDTO convertToDTO(UserExpertise userExpertise) {
        return new UserExpertiseDTO(
                userExpertise.getUser().getUserId(),
                userExpertise.getExpertise().getExpertiseId()
        );
    }

    // Method to assign expertise to a user
    public Users assignExpertiseToUser(Long userId, Long expertiseId) {
        // Retrieve user and expertise by their IDs
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("No such user"));
        Expertise expertise = expertiseRepository.findById(expertiseId).orElseThrow(() -> new RuntimeException("No such expertise"));

        UserExpertiseId userExpertiseId = new UserExpertiseId(userId, expertiseId);

        // Create a new UserExpertise entity and set the composite key and the entities
        UserExpertise userExpertise = new UserExpertise();
        userExpertise.setId(userExpertiseId);  // Set the composite ID
        userExpertise.setUser(user);
        userExpertise.setExpertise(expertise);

        userExpertiseRepository.save(userExpertise);

       return user;
    }

    public List<UserExpertiseDTO> getUserExpertise(Long userId) {

        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));


        List<UserExpertise> userExpertises = userExpertiseRepository.findByUser(user);

        return userExpertises.stream()
                .map(userExpertise -> new UserExpertiseDTO(user.getUserId(), userExpertise.getExpertise().getExpertiseId()
                ))
                .collect(Collectors.toList());
    }



    public boolean removeExpertiseFromUser(Long userId, Long expertiseId) {

        Optional<UserExpertise> userExpertise = userExpertiseRepository.findByUser_UserIdAndExpertise_ExpertiseId(userId, expertiseId);
        if (userExpertise.isPresent()) {
            userExpertiseRepository.delete(userExpertise.get());
            return true;
        } else {

            return false;
        }
    }


}
