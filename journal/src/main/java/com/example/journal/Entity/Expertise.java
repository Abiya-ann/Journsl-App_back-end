package com.example.journal.Entity;

import com.example.journal.Entity.UserExpertise;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expertise {

    @Id
    private Long expertiseId;

    private String name;

    @OneToMany(mappedBy = "expertise", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserExpertise> userExpertises; // Corrected field name for clarity
}
