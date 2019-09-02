package com.cierpial.jakub.sample.todolist.services;

import com.cierpial.jakub.sample.todolist.models.TaskList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Async
    public void sendEmail(TaskList taskList,HttpServletRequest request) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(taskList.getEmail());
        mailMessage.setSubject("Your ");
        mailMessage.setFrom(sender);
        String serverName = request.getServerName();
        String regex = "\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b";
        String port = (serverName.matches(regex) || serverName.equals("localhost")) ? ":" + request.getServerPort() : "";
        mailMessage.setText("Yor task list titled :"+taskList.getTitle()
                + " http://" + request.getServerName() + port + "/show/" + taskList.getToken());
        javaMailSender.send(mailMessage);
    }
}
