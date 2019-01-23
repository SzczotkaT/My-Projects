package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class MailCreatorService {

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message){
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("task_url", "http://localhost:8080/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview", "New Trello card created");
        context.setVariable("App_data", companyConfig.getAppName()
                + companyConfig.getContact() + companyConfig.getAddres() + companyConfig.getStreet_numb());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
