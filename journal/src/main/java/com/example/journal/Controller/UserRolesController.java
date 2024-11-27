package com.example.journal.Controller;

import com.example.journal.DTO.UserRolesDTO;
import com.example.journal.Entity.Users;
import com.example.journal.Service.UserRolesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userRoles")
public class UserRolesController {

    private final UserRolesService userRolesService;

    public UserRolesController(UserRolesService userRolesService) {
        this.userRolesService = userRolesService;
    }
    @GetMapping
    public ResponseEntity<List<UserRolesDTO>> getAllUserRoles() {
        List<UserRolesDTO> userRolesDTOs = userRolesService.getUserRoles();
        return new ResponseEntity<>(userRolesDTOs, HttpStatus.OK);
    }

    @PostMapping("/assignRole")
    public ResponseEntity<Users> assignRoleToUser(@RequestParam Long userId, @RequestParam Integer roleId) {
        Users user = userRolesService.assignRoleToUser(userId, roleId);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/getUserRoles/{userId}")
    public ResponseEntity<List<UserRolesDTO>> getAllRolesOfAUser(@PathVariable Long userId) {
        List<UserRolesDTO> userRolesDTOs = userRolesService.getUserRoles(userId);
        return new ResponseEntity<>(userRolesDTOs, HttpStatus.OK);
    }


    @DeleteMapping("/removeRole/{userId}/{roleId}")
    public ResponseEntity<String> removeRoleFromUser(@PathVariable Long userId, @PathVariable Integer roleId) {
        boolean isRemoved = userRolesService.removeRoleFromUser(userId, roleId);
        if (isRemoved) {
            return new ResponseEntity<>("Role removed successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Role not found for the user.", HttpStatus.NOT_FOUND);
        }
    }
}
