package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CompanyConfig {

    @Value("${info.app.name}")
    private String appName;

    @Value("${info.app.administrator.email}")
    private String contact;

    @Value("${info.app.administrator.address.street}")
    private String addres;

    @Value("${info.app.administrator.address.number}")
    private int street_numb;
}
