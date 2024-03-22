package net.suhel.springboot.todo.controller;

import lombok.AllArgsConstructor;
import net.suhel.springboot.todo.dto.ToDoDto;
import net.suhel.springboot.todo.entity.ToDo;
import net.suhel.springboot.todo.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/todo")
public class ControllerToDo {

    ToDoService toDoService;

    //====== Adding of Todo
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ToDoDto> addToDo (@RequestBody ToDoDto toDoDto){
        ToDoDto savedToDO=toDoService.addTodo(toDoDto);
        return new ResponseEntity<>(savedToDO, HttpStatus.CREATED);

        }


        // =======Find By ID
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
        @GetMapping("{id}")
    public ResponseEntity<ToDoDto> findToDoById (@PathVariable("id") Long i ){
      ToDoDto savedToDo=  toDoService.getToDoById(i);

        return new ResponseEntity<>(savedToDo,HttpStatus.CREATED);
    }


    //===== Get all ToDo
    @PreAuthorize("hasRole('USER')")
    @GetMapping()
    public ResponseEntity<List<ToDoDto>> getAllTodo (){

         List<ToDoDto> toDoDtos=toDoService.findAllToDo();
         return new ResponseEntity<>(toDoDtos,HttpStatus.OK);
    }

    //======== Delete By Id
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{Userid}")
    public ResponseEntity<String> deleteToDoById (@PathVariable("Userid") Long id){
        toDoService.deleteToDoById(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{Userid}")
    public ResponseEntity<ToDoDto> updateToDo( @RequestBody ToDoDto toDoDto,@PathVariable("Userid") Long id){
     ToDoDto updatedToDoDto=   toDoService.updateToDo(toDoDto,id);

        return new ResponseEntity<>(updatedToDoDto,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{UserId}/complete")
    public ResponseEntity<ToDoDto>  completeToDo(@PathVariable("UserId") Long id){
        ToDoDto toDoDto=toDoService.completeToDo(id);
        return new ResponseEntity<>(toDoDto,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{UserId}/incomplete")
    public ResponseEntity<ToDoDto> incompleteToDo(@PathVariable("UserId") Long id){
        ToDoDto toDoDto =toDoService.incompleteToDo(id);
        return new ResponseEntity<>(toDoDto,HttpStatus.OK);
    }
}
