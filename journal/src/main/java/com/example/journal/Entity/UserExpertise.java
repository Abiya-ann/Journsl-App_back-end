package com.example.journal.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExpertise {

    @EmbeddedId
    private UserExpertiseId Id; // Composite key for UserExpertise (userId and expertiseId)

    @ManyToOne
    @MapsId("userId")
    private Users user; // User linked to this expertise

    @ManyToOne
    @MapsId("expertiseId")
    private Expertise expertise; // Expertise linked to this user
}
