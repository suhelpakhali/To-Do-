package net.suhel.springboot.todo.repository;

import net.suhel.springboot.todo.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo,Long> {
}
