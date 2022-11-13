package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.service.EmailSenderService;

@RestController
public class EmailController {
    @Autowired
	private EmailSenderService mailService;
	
    
   @PostMapping({"/sendEmail"})
    String SendEmailMessage() {
	   this.mailService.sendSimpleEmail("amanicherif431@gmail.com","This is email body","This is email subject");
	return "Message sent";
   }
}
