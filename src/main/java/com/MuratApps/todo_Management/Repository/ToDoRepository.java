package com.MuratApps.todo_Management.Repository;

import com.MuratApps.todo_Management.Entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo,Long> {
}
