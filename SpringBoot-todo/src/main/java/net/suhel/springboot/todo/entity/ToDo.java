package net.suhel.springboot.todo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="todo")
public class ToDo {
     @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
     @Column(nullable = false)
    private  String title;
    @Column(nullable = false)
    private String Description;
    private  boolean completed;
}


