package com.communityratesgames.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class MailController {

    @Autowired
    private SmptMailSender smptMailSender;

    @RequestMapping("/send")
    public void sendMail() throws MessagingException {
        smptMailSender.send("communityratesgames@gmail.com", "test mail from our app", "Robin");
    }

}
