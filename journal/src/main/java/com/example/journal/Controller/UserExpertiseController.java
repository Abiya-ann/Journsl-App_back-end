package com.example.journal.Controller;

import com.example.journal.DTO.UserExpertiseDTO;
import com.example.journal.DTO.UserRolesDTO;
import com.example.journal.Entity.Users;
import com.example.journal.Service.UserExpertiseService;
import com.example.journal.Service.UserRolesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userExpertise")
public class UserExpertiseController {

    private final UserExpertiseService userExpertiseService;

    public UserExpertiseController(UserExpertiseService userExpertiseService) {
        this.userExpertiseService = userExpertiseService;
    }

    // Endpoint to assign a role to a user
    @PostMapping("/assignRole")
    public ResponseEntity<Users> assignExpertiseToUser(@RequestParam Long userId, @RequestParam Long expertiseId) {
        Users user = userExpertiseService.assignExpertiseToUser(userId, expertiseId);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/getUserExpertise/{userId}")
    public ResponseEntity<List<UserExpertiseDTO>> getUserExpertise(@PathVariable Long userId) {
        List<UserExpertiseDTO> userExpertiseDTOs = userExpertiseService.getUserExpertise(userId);
        return new ResponseEntity<>(userExpertiseDTOs, HttpStatus.OK);
    }

    // Endpoint to remove a role from a user
    @DeleteMapping("/removeRole")
    public ResponseEntity<Void> removeExpertiseFromUser(@RequestParam Long userId, @RequestParam Long expertiseId) {
        boolean isRemoved = userExpertiseService.removeExpertiseFromUser(userId, expertiseId);
        if (isRemoved) {
            return  ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
