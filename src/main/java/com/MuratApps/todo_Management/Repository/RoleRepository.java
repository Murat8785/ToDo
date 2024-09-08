package com.MuratApps.todo_Management.Repository;

import com.MuratApps.todo_Management.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
