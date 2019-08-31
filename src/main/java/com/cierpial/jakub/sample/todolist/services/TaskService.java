package com.cierpial.jakub.sample.todolist.services;

import com.cierpial.jakub.sample.todolist.models.Task;
import com.cierpial.jakub.sample.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public Task createNewTask(Task task)
    {
        return taskRepository.save(task);
    }
}
