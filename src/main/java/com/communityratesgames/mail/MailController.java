package com.communityratesgames.mail;

import com.communityratesgames.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @RequestMapping("/send")
    public String sendMail(){

        UserModel user = new UserModel();
        user.setEmail("communityratesgames@gmail.com");
        user.setEmailText("detta fungerar ju också");
        user.setEmailSubject("Test");
        //Send notiser
        try{
            mailService.sendNotification(user);
        }catch(MailException e){
            e.getMessage();
        }

        return "Tack för ditt mail";
    }

}
