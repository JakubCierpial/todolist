package com.cierpial.jakub.sample.todolist.repositories;

import com.cierpial.jakub.sample.todolist.models.TaskList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListRepository extends CrudRepository<TaskList,Long> {

    @Override
    List<TaskList> findAll();

    TaskList findById(long id);

    TaskList findByToken(String token);
}
