package com.cierpial.jakub.sample.todolist.repositories;

import com.cierpial.jakub.sample.todolist.models.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task,Long> {

    @Override
    List<Task> findAll();
}
