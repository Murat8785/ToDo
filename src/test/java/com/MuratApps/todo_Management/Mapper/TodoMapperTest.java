package com.MuratApps.todo_Management.Mapper;

import com.MuratApps.todo_Management.Dto.ToDoDto;
import com.MuratApps.todo_Management.Entity.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoMapperTest {


    private  TodoMapper todoMapper;

    @BeforeEach
    void setUp() {
       todoMapper=new TodoMapper();

    }

    @Test
    void mapToTodo() {
        ToDoDto toDoDto=new ToDoDto(1L,"TEST","TEST",false);

        ToDo toDo=todoMapper.mapToTodo(toDoDto);

        assertEquals(toDo.getId(),toDoDto.getId());
        assertEquals(toDo.getTitle(),toDoDto.getTitle());
        assertEquals(toDo.getDescription(),toDoDto.getDescription());
        assertEquals(toDo.isCompleted(),toDoDto.isCompleted());


    }

    @Test
    void mapToTodoDto() {

        ToDo toDo=new ToDo(1L,"TEST","TEST",false);

        ToDoDto toDoDto=todoMapper.mapToTodoDto(toDo);

        assertEquals(toDo.getId(),toDoDto.getId());
        assertEquals(toDo.getTitle(),toDoDto.getTitle());
        assertEquals(toDo.getDescription(),toDoDto.getDescription());
        assertEquals(toDo.isCompleted(),toDoDto.isCompleted());



    }
}