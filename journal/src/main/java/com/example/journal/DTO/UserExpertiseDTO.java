package com.example.journal.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExpertiseDTO {
    private Long userId;
    private Long expertiseId;
}
