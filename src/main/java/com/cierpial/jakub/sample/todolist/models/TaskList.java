package com.cierpial.jakub.sample.todolist.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "listOfTasks" })
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    @OneToMany(mappedBy = "taskList")
    private List<Task> listOfTasks;

    @PrePersist
    public void init()
    {
        this.listOfTasks =  new ArrayList<>();
    }
}
