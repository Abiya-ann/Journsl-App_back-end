package com.example.journal.Controller;

import com.example.journal.DTO.RolesDTO;
import com.example.journal.Entity.Roles;
import com.example.journal.Service.RolesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {

    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }


    @GetMapping
    public List<RolesDTO> getAllRoles() {
        return rolesService.getAllRoles();
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RolesDTO> getRoleById(@PathVariable int roleId) {
        RolesDTO rolesDTO = rolesService.getRoleById(roleId);
        return ResponseEntity.ok(rolesDTO);
    }


    @PostMapping
    public ResponseEntity<RolesDTO> createRole(@RequestBody Roles roles) {
        RolesDTO createdRole = rolesService.createRole(roles);
        return ResponseEntity.ok(createdRole);
    }


    @PutMapping("/{roleId}")
    public ResponseEntity<RolesDTO> updateRole(@PathVariable int roleId, @RequestBody Roles rolesDetails) {
        RolesDTO updatedRole = rolesService.updateRole(roleId, rolesDetails);
        return ResponseEntity.ok(updatedRole);
    }


    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable int roleId) {
        rolesService.deleteRole(roleId);
        return ResponseEntity.noContent().build();  // 204 No Content
    }
}
