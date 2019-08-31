package com.cierpial.jakub.sample.todolist.services;

import com.cierpial.jakub.sample.todolist.models.TaskList;
import com.cierpial.jakub.sample.todolist.repositories.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskListService {

    @Autowired
    ListRepository listRepository;

    public TaskList createNewTaskList(TaskList taskList)
    {
        return listRepository.save(taskList);
    }
    public TaskList getTaskListById(long id)
    {
        return listRepository.findById(id);
    }
}
