package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private MailCreatorService mailCreatorService;

    @Test
    public void shouldSendEmail(){
        //Given
        Mail mail = new Mail("Test@test.com", "Test", "Test message","TestCc@test.com");

//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(mail.getMailTo());
//        mailMessage.setCc(mail.getToCc());
//        mailMessage.setSubject(mail.getSubject());
//        mailMessage.setText(mail.getMessage());


        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setTo(mail.getMailTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()),true);
            helper.setText(mailCreatorService.howManyTasksInDatabaseEmail(mail.getMessage()), true);
            };

        //When
        simpleEmailService.send(mail);

        //Then
        verify(javaMailSender, times(1)).send(any(MimeMessagePreparator.class));
    }

}