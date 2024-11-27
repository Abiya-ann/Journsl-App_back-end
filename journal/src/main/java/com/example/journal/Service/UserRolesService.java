package com.example.journal.Service;

import com.example.journal.DTO.UserRolesDTO;
import com.example.journal.Entity.Roles;
import com.example.journal.Entity.UserRoles;
import com.example.journal.Entity.UserRolesId;
import com.example.journal.Entity.Users;
import com.example.journal.Repository.RolesRepository;
import com.example.journal.Repository.UserRolesRepository;
import com.example.journal.Repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserRolesService {
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final UserRolesRepository userRolesRepository;

    public UserRolesService(UsersRepository usersRepository, RolesRepository rolesRepository, UserRolesRepository userRolesRepository) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.userRolesRepository = userRolesRepository;
    }

    // Convert UserRoles to UserRolesDTO
    private UserRolesDTO convertToDTO(UserRoles userRoles) {
        return new UserRolesDTO(
                userRoles.getUser().getUserId(),
                userRoles.getRoles().getRoleId()
        );
    }
    public List<UserRolesDTO> getUserRoles() {
        List<UserRoles> userRolesList = userRolesRepository.findAll();
        return userRolesList.stream()
                .map(userRole -> new UserRolesDTO(userRole.getUser().getUserId(), userRole.getRoles().getRoleId()))
                .collect(Collectors.toList());
    }


    // Assign a role to a user
    public Users assignRoleToUser(Long userId, Integer roleId) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("There is no such user"));
        Roles role = rolesRepository.findById(roleId).orElseThrow(() -> new RuntimeException("There is no such role"));

        UserRolesId userRolesId = new UserRolesId(userId, roleId);
        UserRoles userRoles = new UserRoles();
        userRoles.setUrId(userRolesId);
        userRoles.setUser(user);
        userRoles.setRoles(role);

        userRolesRepository.save(userRoles);
        return user;
    }

    // Get all roles assigned to a user
    public List<UserRolesDTO> getUserRoles(Long userId) {
        return userRolesRepository.findRolesByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public boolean removeRoleFromUser(Long userId, Integer roleId) {
        Optional<UserRoles> userRoles = userRolesRepository.findByUser_UserIdAndRoles_RoleId(userId, roleId);

        if (userRoles.isPresent()) {
            userRolesRepository.delete(userRoles.get());
            return true;
        }
        else {
            return false;
        }
    }

}
