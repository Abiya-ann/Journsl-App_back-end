package com.example.journal.Repository;

import com.example.journal.Entity.UserExpertise;
import com.example.journal.Entity.UserExpertiseId;
import com.example.journal.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserExpertiseRepository extends JpaRepository<UserExpertise, UserExpertiseId> {

    // Fetch all UserExpertise records associated with a user
    List<UserExpertise> findByUser(Users user);

    // Find UserExpertise by the userId and expertiseId (composite key)
    @Query("SELECT ue FROM UserExpertise ue WHERE ue.user.userId = :userId AND ue.expertise.expertiseId = :expertiseId")
    Optional<UserExpertise> findByUser_UserIdAndExpertise_ExpertiseId(@Param("userId") Long userId, @Param("expertiseId") Long expertiseId);


}
