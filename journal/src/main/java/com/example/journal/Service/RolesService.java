package com.example.journal.Service;

import com.example.journal.DTO.RolesDTO;
import com.example.journal.Entity.Roles;
import com.example.journal.Repository.RolesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolesService {
    private final RolesRepository rolesRepository;

    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    // Convert Roles to RolesDTO
    private RolesDTO convertToDTO(Roles roles) {
        return new RolesDTO(
                roles.getRoleId(),
                roles.getRoleName()
        );
    }

    public List<RolesDTO> getAllRoles() {
        // Fetch all roles and convert to DTO
        List<Roles> rolesList = rolesRepository.findAll();
        return rolesList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RolesDTO getRoleById(int roleId) {
        // Fetch role by ID and convert to DTO
        Roles roles = rolesRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("No such role"));
        return convertToDTO(roles);
    }

    public void deleteRole(int roleId) {
        // Delete the role by ID
        rolesRepository.deleteById(roleId);
    }

    public RolesDTO createRole(Roles roles) {
        // Create and save a new role, then convert to DTO
        Roles createdRole = rolesRepository.save(roles);
        return convertToDTO(createdRole);
    }

    public RolesDTO updateRole(int roleId, Roles rolesDetails) {
        // Retrieve role to be updated
        Roles roles = rolesRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("No such role"));

        // Update role details
        if (rolesDetails.getRoleName() != null) {
            roles.setRoleName(rolesDetails.getRoleName());
        }

        // Save the updated role and return DTO
        Roles updatedRole = rolesRepository.save(roles);
        return convertToDTO(updatedRole);
    }
}
