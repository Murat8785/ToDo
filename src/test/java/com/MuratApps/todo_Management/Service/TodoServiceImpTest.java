package com.MuratApps.todo_Management.Service;

import com.MuratApps.todo_Management.Dto.ToDoDto;
import com.MuratApps.todo_Management.Entity.ToDo;
import com.MuratApps.todo_Management.Mapper.TodoMapper;
import com.MuratApps.todo_Management.Repository.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoServiceImpTest {


    @InjectMocks
    private TodoServiceImp todoServiceImp;

    @Mock
    private ToDoRepository toDoRepository;

    @Mock
    private TodoMapper todoMapper;




    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTodo() {

        ToDoDto toDoDto=new ToDoDto(1L,"testtitle","testdesc",false);
        ToDo toDo=new ToDo(1L,"testtitle","testdesc",false);
        ToDo saveTodo=new ToDo(1L,"testtitle","testdesc",false);

        Mockito.when(todoMapper.mapToTodo(toDoDto)).thenReturn(toDo);
        Mockito.when(toDoRepository.save(toDo)).thenReturn(saveTodo);
        Mockito.when(todoMapper.mapToTodoDto(saveTodo)).thenReturn(new ToDoDto(1L,"testtitle","testdesc",false));

        //mockları yazdıktan sonra en sonki returnu buraya kaydederiz
        ToDoDto toDoDto1=todoServiceImp.createTodo(toDoDto);

        assertEquals(toDoDto1.getId(),toDoDto.getId());
        assertEquals(toDoDto1.getDescription(),toDoDto.getDescription());
        assertEquals(toDoDto1.getTitle(),toDoDto.getTitle());
        assertEquals(toDoDto1.getClass(),toDoDto.getClass());

        verify(todoMapper,times(1)).mapToTodo(toDoDto);
        verify(toDoRepository,times(1)).save(toDo);
        verify(todoMapper,times(1)).mapToTodoDto(saveTodo);



    }

    @Test
    void findById() {

        ToDoDto toDoDto=new ToDoDto(1L,"testtitle","testdesc",false);
        ToDo toDo=new ToDo(1L,"testtitle","testdesc",false);

        Mockito.when(toDoRepository.findById(toDoDto.getId())).thenReturn(Optional.of(toDo));
        Mockito.when(todoMapper.mapToTodoDto(toDo)).thenReturn(new ToDoDto(1L,"testtitle","testdesc",false));

        ToDoDto toDoDto1=todoServiceImp.findById(toDoDto.getId());

        assertEquals(toDoDto1.getId(),toDoDto.getId());

        verify(toDoRepository,times(1)).findById(toDoDto.getId());
        verify(todoMapper,times(1)).mapToTodoDto(toDo);





    }

    @Test
    void findAll() {

        List<ToDo> list=new ArrayList<>();
        list.add(new ToDo(1L,"testtitle","testdesc",false));

        when(toDoRepository.findAll()).thenReturn(list);
        when(todoMapper.mapToTodoDto(any(ToDo.class))).thenReturn(new ToDoDto(1L,"testtitle","testdesc",false));

        List<ToDoDto> list1=todoServiceImp.findAll();

        assertEquals(list1.size(),list.size());

        verify(toDoRepository,times(1)).findAll();
        verify(todoMapper,times(1)).mapToTodoDto(any(ToDo.class));







    }

    @Test
    void updateTodo() {

        Long id = 1L;

        ToDoDto toDoDto = new ToDoDto();
        toDoDto.setTitle("test Title");
        toDoDto.setCompleted(true);
        toDoDto.setDescription("Test desc");

        ToDo ToDo = new ToDo();
        ToDo.setId(id);
        ToDo.setTitle("Test title");
        ToDo.setCompleted(false);
        ToDo.setDescription("Test desc");

        ToDo updatedToDo = new ToDo();
        updatedToDo.setId(id);
        updatedToDo.setTitle(toDoDto.getTitle());
        updatedToDo.setCompleted(toDoDto.isCompleted());
        updatedToDo.setDescription(toDoDto.getDescription());

        when(toDoRepository.findById(id)).thenReturn(Optional.of(ToDo));
        when(toDoRepository.save(any(ToDo.class))).thenReturn(updatedToDo);
        when(todoMapper.mapToTodoDto(any(ToDo.class))).thenReturn(toDoDto);


        ToDoDto update = todoServiceImp.updateTodo(id, toDoDto);


        assertEquals(toDoDto.getTitle(), update.getTitle());
        assertEquals(toDoDto.isCompleted(), update.isCompleted());
        assertEquals(toDoDto.getDescription(), update.getDescription());

        verify(toDoRepository, times(1)).findById(id);
        verify(toDoRepository, times(1)).save(any(ToDo.class));
        verify(todoMapper, times(1)).mapToTodoDto(any(ToDo.class));




    }

    @Test
    void isComplete() {

        Long id=1L;
        ToDo ToDo = new ToDo();
        ToDo.setId(id);
        ToDo.setTitle("Test title");
        ToDo.setCompleted(false);
        ToDo.setDescription("Test desc");

        ToDoDto toDoDto = new ToDoDto();
        toDoDto.setTitle("test Title");
        toDoDto.setCompleted(true);
        toDoDto.setDescription("Test desc");

        ToDo updatedToDo = new ToDo();
        updatedToDo.setCompleted(toDoDto.isCompleted());
        updatedToDo.setDescription(toDoDto.getDescription());
        updatedToDo.setId(id);
        updatedToDo.setTitle(toDoDto.getTitle());

        when(toDoRepository.findById(id)).thenReturn(Optional.of(ToDo));
        when(toDoRepository.save(any(ToDo.class))).thenReturn(updatedToDo);
        when(todoMapper.mapToTodoDto(any(ToDo.class))).thenReturn(toDoDto);


        ToDoDto iscomp = todoServiceImp.isComplete(id);

        assertEquals(iscomp.isCompleted(),toDoDto.isCompleted());

        verify(toDoRepository,times(1)).findById(id);
        verify(toDoRepository,times(1)).save(any(ToDo.getClass()));
        verify(todoMapper,times(1)).mapToTodoDto(any(ToDo.getClass()));







    }

    @Test
    void inComplete() {

            Long id=1L;
            ToDo ToDo = new ToDo();
            ToDo.setId(id);
            ToDo.setTitle("Test title");
            ToDo.setCompleted(true);
            ToDo.setDescription("Test desc");

            ToDoDto toDoDto = new ToDoDto();
            toDoDto.setTitle("test Title");
            toDoDto.setCompleted(false);
            toDoDto.setDescription("Test desc");

            ToDo updatedToDo = new ToDo();
            updatedToDo.setCompleted(toDoDto.isCompleted());
            updatedToDo.setDescription(toDoDto.getDescription());
            updatedToDo.setId(id);
            updatedToDo.setTitle(toDoDto.getTitle());

            when(toDoRepository.findById(id)).thenReturn(Optional.of(ToDo));
            when(toDoRepository.save(any(ToDo.class))).thenReturn(updatedToDo);
            when(todoMapper.mapToTodoDto(any(ToDo.class))).thenReturn(toDoDto);


            ToDoDto incomp = todoServiceImp.inComplete(id);;

            assertEquals(incomp.isCompleted(),toDoDto.isCompleted());

            verify(toDoRepository,times(1)).findById(id);
            verify(toDoRepository,times(1)).save(any(ToDo.getClass()));
            verify(todoMapper,times(1)).mapToTodoDto(any(ToDo.getClass()));

        }
}