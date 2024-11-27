package com.example.journal.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data

    public class UsersDTO {
    private Long userId;
    private String name;
    private String email;
    private String contact;

}