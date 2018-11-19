package com.communityratesgames.mail;

import com.communityratesgames.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender)throws MailException {
        this.javaMailSender = javaMailSender;
    }

    public void sendNotification(UserModel user){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setSubject(user.getEmailSubject());
        mail.setText(user.getEmailText());

        javaMailSender.send(mail);
    }

}
