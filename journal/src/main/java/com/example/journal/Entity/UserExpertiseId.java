package com.example.journal.Entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserExpertiseId implements Serializable {

    private Long userId;
    private Long expertiseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserExpertiseId that = (UserExpertiseId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(expertiseId, that.expertiseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, expertiseId);  // Generate hash code based on both fields
    }

}
