package com.example.journal.Repository;

import com.example.journal.Entity.UserRoles;
import com.example.journal.Entity.UserRolesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRolesRepository extends JpaRepository<UserRoles, UserRolesId> {

    // Custom query to fetch all roles assigned to a user by userId
    @Query("SELECT ur FROM UserRoles ur WHERE ur.user.userId = :userId")
    List<UserRoles> findRolesByUserId(Long userId);

    @Query("SELECT ur FROM UserRoles ur WHERE ur.user.userId = :userId AND ur.roles.roleId = :roleId")
    Optional<UserRoles> findByUser_UserIdAndRoles_RoleId(@Param("userId") Long userId, @Param("roleId") Integer roleId);
}
