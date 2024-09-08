package com.MuratApps.todo_Management.Controller;


import com.MuratApps.todo_Management.Dto.ToDoDto;
import com.MuratApps.todo_Management.Service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")

public class ToDoController {

    @Autowired
    private TodoService todoService;

    public ToDoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/create")
    public ResponseEntity<ToDoDto> createTodo( @RequestBody ToDoDto toDoDto){

        ToDoDto toDoDto1=todoService.createTodo(toDoDto);

        return new ResponseEntity<>(toDoDto1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoDto> findById( @PathVariable Long id){

        ToDoDto toDoDto=todoService.findById(id);

        return ResponseEntity.ok(toDoDto);

    }

    @GetMapping("/all")
    public ResponseEntity<List<ToDoDto>> findAll(){

        List<ToDoDto> find=todoService.findAll();

        return ResponseEntity.ok(find);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoDto> update(@PathVariable Long id,@RequestBody ToDoDto toDoDto){

        ToDoDto toDoDto1=todoService.updateTodo(id,toDoDto);



        return ResponseEntity.ok(toDoDto1);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){

        todoService.deleteTodo(id);

         return ResponseEntity.ok("kullanıcı silindi" + id);

    }

    @PatchMapping("{id}/complete")
    public  ResponseEntity<ToDoDto> complete(@PathVariable Long id){

        ToDoDto complete = todoService.isComplete(id);

        return ResponseEntity.ok(complete);

    }


    @PatchMapping("{id}/in-complete")
    public  ResponseEntity<ToDoDto> incomplete(@PathVariable Long id){

        ToDoDto complete = todoService.inComplete(id);

        return ResponseEntity.ok(complete);

    }








}
