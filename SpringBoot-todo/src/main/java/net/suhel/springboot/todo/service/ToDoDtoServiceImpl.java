package net.suhel.springboot.todo.service;

import lombok.AllArgsConstructor;
import net.suhel.springboot.todo.dto.ToDoDto;
import net.suhel.springboot.todo.entity.ToDo;
import net.suhel.springboot.todo.exception.ResourceNotFoundException;
import net.suhel.springboot.todo.repository.ToDoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ToDoDtoServiceImpl implements ToDoService{

    ToDoRepository toDoRepository;
    ModelMapper modelMapper;
    @Override
    public ToDoDto addTodo(ToDoDto toDoDto) {

ToDo todo = modelMapper.map(toDoDto,ToDo.class);

toDoRepository.save(todo);

ToDoDto toDoDto1 = modelMapper.map(todo,ToDoDto.class);

return toDoDto1;

    }

    @Override
    public ToDoDto getToDoById(Long id) {

     ToDo toDo= toDoRepository.findById(id)
             .orElseThrow(()-> new ResourceNotFoundException("ToDo not found eith Id "+id));

     ToDoDto toDoDto = modelMapper.map(toDo,ToDoDto.class);

     return toDoDto;

    }
    @Override
    public List<ToDoDto> findAllToDo() {
       List<ToDo> l= toDoRepository.findAll();

     return l.stream().map((todo)-> modelMapper.map(todo,ToDoDto.class)).
             collect(Collectors.toList());
    }

    //Delete ToDo
    @Override
    public void deleteToDoById(Long id) {

        toDoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("ToDo is not found for "+id));
        toDoRepository.deleteById(id);
    }

    @Override
    public ToDoDto updateToDo(ToDoDto toDoDto, Long id) {
      ToDo toDo=  toDoRepository.findById(id)
              .orElseThrow(()->new ResourceNotFoundException("ToDo not found with "+id));

      toDo.setTitle(toDoDto.getTitle());
      toDo.setCompleted((toDoDto.isCompleted()));
      toDo.setDescription(toDoDto.getDescription());
      toDoRepository.save(toDo);
      return modelMapper.map(toDo,ToDoDto.class);
    }

    @Override
    public ToDoDto completeToDo(Long id) {
       ToDo toDo= toDoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ToDo not found with id"+id));

        toDo.setCompleted(Boolean.TRUE);

        return modelMapper.map(toDo,ToDoDto.class);
    }

    @Override
    public ToDoDto incompleteToDo(Long id) {
       ToDo todo= toDoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Resource is not found with"+id));

       todo.setCompleted(Boolean.FALSE);

       return modelMapper.map(todo,ToDoDto.class);


    }


}
