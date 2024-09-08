package com.MuratApps.todo_Management.Service;

import com.MuratApps.todo_Management.Dto.ToDoDto;

import java.util.List;

public interface TodoService {

    ToDoDto createTodo(ToDoDto toDoDto);

    ToDoDto findById(Long id);

    List<ToDoDto> findAll();

    ToDoDto updateTodo(Long id,ToDoDto toDoDto);

    ToDoDto isComplete(Long id);

    ToDoDto inComplete(Long id);

    void deleteTodo(Long id);
 }
