package com.MuratApps.todo_Management.Repository;

import com.MuratApps.todo_Management.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByName(String name);

    Boolean existsByEmail(String email);

    Optional<User> findByNameOrEmail(String name,String email);

    Boolean existsByName(String name);
}
