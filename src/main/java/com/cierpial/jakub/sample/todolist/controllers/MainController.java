package com.cierpial.jakub.sample.todolist.controllers;

import com.cierpial.jakub.sample.todolist.models.Task;
import com.cierpial.jakub.sample.todolist.models.TaskList;
import com.cierpial.jakub.sample.todolist.services.TaskListService;
import com.cierpial.jakub.sample.todolist.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @Autowired
    TaskListService taskListService;
    @Autowired
    TaskService taskService;

    @GetMapping("/")
    public String getMainPage(Model model)
    {
        model.addAttribute("taskList", new TaskList());
        return "index";
    }
    @GetMapping("/show/{id}")
    public String getTasks(Model model, @PathVariable("id") long id)
    {
        TaskList taskListById = taskListService.getTaskListById(id);
        model.addAttribute("tasks",taskListById);

        Task task = new Task();
        task.setTaskList(taskListById);
        model.addAttribute("newTask", task);
        return "tasks";
    }
    @PostMapping("/createnew")
    public String createNewTaskList(Model model,TaskList taskList)
    {
        taskListService.createNewTaskList(taskList);
        return "redirect:/show/"+taskList.getId();
    }
    @PostMapping("/task/save")
    public String addNewTask(Model model, Task task)
    {

        taskService.createNewTask(task);
        System.out.println(task.getTaskList().getId());
        return "redirect:/show/"+task.getTaskList().getId();
    }


}
