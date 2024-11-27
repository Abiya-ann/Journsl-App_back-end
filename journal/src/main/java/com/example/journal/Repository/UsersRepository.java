package com.example.journal.Repository;

import com.example.journal.Entity.Roles;
import com.example.journal.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Long> {

}
