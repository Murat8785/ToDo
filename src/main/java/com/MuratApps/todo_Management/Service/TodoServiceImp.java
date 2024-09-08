package com.MuratApps.todo_Management.Service;


import com.MuratApps.todo_Management.Dto.ToDoDto;
import com.MuratApps.todo_Management.Entity.ToDo;
import com.MuratApps.todo_Management.Mapper.TodoMapper;
import com.MuratApps.todo_Management.Repository.ToDoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImp implements TodoService{


    @Autowired
    private ToDoRepository toDoRepository;
     @Autowired
    private TodoMapper todoMapper;

    public TodoServiceImp(TodoMapper todoMapper, ToDoRepository toDoRepository) {
        this.todoMapper = todoMapper;
        this.toDoRepository = toDoRepository;
    }

    @Override
    public ToDoDto createTodo(ToDoDto toDoDto) {

        ToDo toDo=todoMapper.mapToTodo(toDoDto);

        ToDo savedTodo=toDoRepository.save(toDo);

        return todoMapper.mapToTodoDto(savedTodo);

    }

    @Override
    public ToDoDto findById(Long id) {

        ToDo toDo=toDoRepository.findById(id).orElseThrow(()->new RuntimeException("Böyle bir kullanıcı bulunamadı"));


        return todoMapper.mapToTodoDto(toDo);
    }

    @Override
    public List<ToDoDto> findAll() {

       List<ToDo> find=toDoRepository.findAll();


        return  find.stream().map((toDo)->todoMapper.mapToTodoDto(toDo)).collect(Collectors.toList());
    }

    @Override
    public ToDoDto updateTodo(Long id,ToDoDto toDoDto) {

        ToDo toDo=toDoRepository.findById(id).orElseThrow(()->new RuntimeException("Böyle bir kullanıcı bulunamadı"));

        toDo.setTitle(toDoDto.getTitle());
        toDo.setCompleted(toDoDto.isCompleted());
        toDo.setDescription(toDoDto.getDescription());

        ToDo save = toDoRepository.save(toDo);

        return todoMapper.mapToTodoDto(save);
    }

    @Override
    public ToDoDto isComplete(Long id) {
        ToDo toDo=toDoRepository.findById(id).orElseThrow(()->new RuntimeException("Böyle bir kullanıcı bulunamadı"));

        toDo.setCompleted(Boolean.TRUE);

        ToDo update=toDoRepository.save(toDo);


        return todoMapper.mapToTodoDto(update);
    }

    @Override
    public ToDoDto inComplete(Long id) {
        ToDo toDo=toDoRepository.findById(id).orElseThrow(()->new RuntimeException("Böyle bir kullanıcı bulunamadı"));

        toDo.setCompleted(Boolean.FALSE);

        ToDo update=toDoRepository.save(toDo);


        return todoMapper.mapToTodoDto(update);
    }

    @Override
    public void deleteTodo(Long id) {

        ToDo toDo=toDoRepository.findById(id).orElseThrow(()->new RuntimeException("Böyle bir kullanıcı bulunamadı"));

        toDoRepository.delete(toDo);


    }





}
