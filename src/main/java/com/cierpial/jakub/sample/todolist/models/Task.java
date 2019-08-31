package com.cierpial.jakub.sample.todolist.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    private TaskList taskList;

    private String title;
    private String description;
    private boolean done;

}
