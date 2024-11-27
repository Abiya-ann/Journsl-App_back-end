package com.example.journal.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserRoles {
    @EmbeddedId
    private UserRolesId urId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("userId")
    private Users user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("roleId")
    private Roles roles;
}
