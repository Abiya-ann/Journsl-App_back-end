package com.example.journal.Service;

import com.example.journal.Entity.Users;
import com.example.journal.Repository.UsersRepository;
import com.example.journal.DTO.UsersDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // Convert UsersDTO to Users Entity
    private Users convertToEntity(UsersDTO usersDTO) {
        Users user = new Users();
        user.setUserId((long) usersDTO.getUserId());
        user.setName(usersDTO.getName());
        user.setEmail(usersDTO.getEmail());
        user.setContact(usersDTO.getContact());
        return user;
    }

    public List<UsersDTO> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UsersDTO getUserById(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }

    public UsersDTO createUser(UsersDTO usersDTO) {
        Users user = convertToEntity(usersDTO); // Convert DTO to Entity
        Users savedUser = usersRepository.save(user);
        return convertToDTO(savedUser);  // Return the DTO
    }

    public UsersDTO updateUser(Long userId, UsersDTO usersDTO) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update user fields from DTO
        user.setName(usersDTO.getName());
        user.setEmail(usersDTO.getEmail());
        user.setContact(usersDTO.getContact());

        Users updatedUser = usersRepository.save(user);
        return convertToDTO(updatedUser);
    }

    public void deleteUser(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        usersRepository.delete(user);
    }

    // Helper method to convert Entity to DTO
    private UsersDTO convertToDTO(Users user) {
        return new UsersDTO(user.getUserId(), user.getName(), user.getEmail(), user.getContact());
    }
}
