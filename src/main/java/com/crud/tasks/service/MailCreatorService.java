package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.scheduler.EmailScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;


@Service
public class MailCreatorService {

    @Autowired
    private EmailScheduler sheduler;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message){

        List<String> funcionality = new ArrayList<>();
        funcionality.add("You can manage your tasks");
        funcionality.add("Provides connection with Trello Account");
        funcionality.add("Applicatipn allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("task_url", "http://localhost:8080/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview", "New Trello card created");
        context.setVariable("App_data", companyConfig.getAppName()
                + " " + companyConfig.getContact() + " " + companyConfig.getAddres() + " " + companyConfig.getStreet_numb());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_funcionality", funcionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String howManyTasksInDatabaseEmail(String message){

        List<String> whatNext = new ArrayList<>();
        whatNext.add("You can ignore this mail");
        whatNext.add("You can check your Trello board");
        whatNext.add("Please do not answer for this message");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("task_url", "http://localhost:8080/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview", "New Trello card created");
        context.setVariable("App_data", companyConfig.getAppName()
                + " " + companyConfig.getContact() + " " + companyConfig.getAddres() + " " + companyConfig.getStreet_numb());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("what_next", whatNext);

        return templateEngine.process("mail/Daily-message-how-many-tasks", context);
    }
}
