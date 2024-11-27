package com.example.journal.Controller;

import com.example.journal.DTO.UsersDTO;
import com.example.journal.Entity.Users;
import com.example.journal.Service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        List<UsersDTO> usersDTOList = usersService.getAllUsers();
        return new ResponseEntity<>(usersDTOList, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable Long userId) {
        try {
            UsersDTO userDTO = usersService.getUserById(userId);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Create a new user
    @PostMapping("/create")
    public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO usersDTO) {
        UsersDTO createdUserDTO = usersService.createUser(usersDTO);  // Directly pass DTO
        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
    }

    // Update an existing user
    @PutMapping("/update/{userId}")
    public ResponseEntity<UsersDTO> updateUser(@PathVariable Long userId, @RequestBody UsersDTO usersDTO) {
        UsersDTO updatedUserDTO = usersService.updateUser(userId, usersDTO);  // Directly pass DTO
        return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        try {
            usersService.deleteUser(userId);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.NO_CONTENT);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}