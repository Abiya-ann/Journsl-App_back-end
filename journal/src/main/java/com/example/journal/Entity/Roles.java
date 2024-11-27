package com.example.journal.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Roles {
    @Id
    private int roleId;
    private String roleName;

    @OneToMany(mappedBy="roles", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRoles> userRoles;
}

