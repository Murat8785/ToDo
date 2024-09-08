package com.MuratApps.todo_Management.Controller;

import com.MuratApps.todo_Management.Dto.ToDoDto;
import com.MuratApps.todo_Management.Service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;


@WebMvcTest(ToDoController.class)
class ToDoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

     private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper=new ObjectMapper();
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void createTodo()throws Exception {

        ToDoDto toDoDto=new ToDoDto(1L,"test","test",false);
        Mockito.when(todoService.createTodo(Mockito.any(ToDoDto.class))).thenReturn(toDoDto);
        mockMvc.perform(post("/api/todos/create").contentType(MediaType.APPLICATION_JSON).content(objectMapper.
                        writeValueAsString(toDoDto))).
                andExpect(status().isCreated()).andExpect(jsonPath("$.title").value("test"));

        Mockito.verify(todoService,Mockito.times(1)).createTodo(Mockito.any(ToDoDto.class));




    }

    @Test
    void findById()throws Exception {
        ToDoDto toDoDto=new ToDoDto(1L,"test","test",false);
        Mockito.when(todoService.findById(toDoDto.getId())).thenReturn(toDoDto);
        mockMvc.perform(get("/api/todos/{id}",toDoDto.getId()).contentType(MediaType.APPLICATION_JSON).content(objectMapper
                        .writeValueAsString(toDoDto)))
                .andExpect(status().isOk());

        Mockito.verify(todoService,Mockito.times(1)).findById(toDoDto.getId());

    }

    @Test
    void findAll() throws Exception {
        List<ToDoDto> toDoDtos=new ArrayList<>();
        toDoDtos.add(new  ToDoDto(1L,"test","test",false));
        Mockito.when(todoService.findAll()).thenReturn(toDoDtos);
        mockMvc.perform(get("/api/todos/all").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(toDoDtos))).andExpect(status().isOk());

        Mockito.verify(todoService,Mockito.times(1)).findAll();
    }

    @Test
    void update()throws  Exception {
        ToDoDto toDoDto=new ToDoDto(1L,"test","test",false);
        ToDoDto toDoDto1=new ToDoDto(1L,"test","TEST",true);
        Mockito.when(todoService.updateTodo(Mockito.eq(1L), Mockito.any(ToDoDto.class))).thenReturn(toDoDto1);
        mockMvc.perform(put("/api/todos/{id}",toDoDto.getId()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(toDoDto))).
                andExpect(status().isOk());

        Mockito.verify(todoService,Mockito.times(1)).updateTodo(Mockito.eq(1L), Mockito.any(ToDoDto.class));
    }

    @Test
    void deleteById()throws  Exception {
        ToDoDto toDoDto=new ToDoDto(1L,"test","test",false);
        Mockito.doNothing().when(todoService).deleteTodo(toDoDto.getId());
        mockMvc.perform(delete("/api/todos/delete/{id}",toDoDto.getId()).contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(toDoDto))).andExpect(status().isOk());


    }

    @Test
    void complete()throws  Exception {
        ToDoDto toDoDto=new ToDoDto(1L,"test","test",true);
        Mockito.when(todoService.isComplete(toDoDto.getId())).thenReturn(toDoDto);
        mockMvc.perform(patch("/api/todos/{id}/complete",toDoDto.getId()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(toDoDto))).
                andExpect(status().isOk());

        Mockito.verify(todoService,Mockito.times(1)).isComplete(toDoDto.getId());
    }

    @Test
    void incomplete() throws  Exception{

        ToDoDto toDoDto=new ToDoDto(1L,"test","test",true);
        Mockito.when(todoService.inComplete(toDoDto.getId())).thenReturn(toDoDto);
        mockMvc.perform(patch("/api/todos/{id}/in-complete",toDoDto.getId()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(toDoDto))).
                andExpect(status().isOk());

        Mockito.verify(todoService,Mockito.times(1)).inComplete(toDoDto.getId());
    }
}