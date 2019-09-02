package com.cierpial.jakub.sample.todolist.controllers;

import com.cierpial.jakub.sample.todolist.models.Task;
import com.cierpial.jakub.sample.todolist.models.TaskList;
import com.cierpial.jakub.sample.todolist.models.TokenDto;
import com.cierpial.jakub.sample.todolist.services.MailService;
import com.cierpial.jakub.sample.todolist.services.TaskListService;
import com.cierpial.jakub.sample.todolist.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    TaskListService taskListService;
    @Autowired
    TaskService taskService;
    @Autowired
    MailService mailService;

    @GetMapping("/")
    public String getMainPage(Model model) {
        model.addAttribute("taskList", new TaskList());
        model.addAttribute("tokenDto",new TokenDto());
        return "index";
    }
    @PostMapping("/show")
    public String getPageByToken(TokenDto tokenDto)
    {
        return "redirect:/show/"+tokenDto.getToken();
    }

    @GetMapping("/show/{token}")
    public String getTasks(Model model, @PathVariable("token") String token) {
        TaskList taskListByToken = taskListService.getTaskListByToken(token);
        model.addAttribute("tasks", taskListByToken);

        Task task = new Task();
        task.setTaskList(taskListByToken);
        model.addAttribute("newTask", task);
        return "tasks";
    }

    @PostMapping("/createnew")
    public String createNewTaskList(Model model, TaskList taskList, HttpServletRequest httpServletRequest) {
        taskListService.createNewTaskList(taskList);
        mailService.sendEmail(taskList,httpServletRequest);
        return "redirect:/show/" + taskList.getToken();
    }

    @PostMapping("/task/save")
    public String addNewTask(Model model, Task task) {

        taskService.createNewTask(task);
        return "redirect:/show/" + task.getTaskList().getToken();
    }

    @GetMapping("/tasks/finish/{id}")
    public String finishTask(@PathVariable("id") long id) {
        Optional<Task> byId = taskService.findById(id);

        taskService.finishTask(byId.get());
        return "redirect:/show/" + byId.get().getTaskList().getToken();


    }

    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable("id") long id) {
        Optional<Task> byId = taskService.findById(id);

        taskService.deleteTask(byId.get());
        return "redirect:/show/" + byId.get().getTaskList().getToken();


    }


}
