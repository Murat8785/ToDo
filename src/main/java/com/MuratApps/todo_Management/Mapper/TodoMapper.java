package com.MuratApps.todo_Management.Mapper;

import com.MuratApps.todo_Management.Dto.ToDoDto;
import com.MuratApps.todo_Management.Entity.ToDo;
import org.springframework.stereotype.Service;

@Service
public class TodoMapper {



    public ToDo mapToTodo(ToDoDto toDoDto){
        ToDo todo=new ToDo(
                toDoDto.getId(),
                toDoDto.getTitle(),
                toDoDto.getDescription(),
                toDoDto.isCompleted()
        );

        return todo;
    }

    public ToDoDto mapToTodoDto(ToDo toDo){

        return new ToDoDto(
                toDo.getId(),
                toDo.getTitle(),
                toDo.getDescription(),
                toDo.isCompleted()
        );
    }


}
