package net.suhel.springboot.todo.service;

import net.suhel.springboot.todo.dto.ToDoDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ToDoService {

    ToDoDto addTodo (ToDoDto toDoDto);
    ToDoDto getToDoById(Long id);

    List <ToDoDto> findAllToDo ();

    void deleteToDoById(Long id);

    ToDoDto updateToDo(ToDoDto toDoDto, Long id);

    ToDoDto completeToDo (Long id);

    ToDoDto incompleteToDo (Long id);



}
