package com.cierpial.jakub.sample.todolist.services;

import com.cierpial.jakub.sample.todolist.models.Task;
import com.cierpial.jakub.sample.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public Task createNewTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> findById(long id) {
        return taskRepository.findById(id);
    }

    public void deleteTask(Task task) {

        taskRepository.delete(task);

    }

    public void finishTask(Task task) {
        task.setDone(!task.isDone());
        taskRepository.save(task);
    }
}
