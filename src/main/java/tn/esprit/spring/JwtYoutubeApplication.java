package tn.esprit.spring;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tn.esprit.spring.service.EmailSenderService;


@EnableAspectJAutoProxy
@EnableWebMvc
@EnableSwagger2
@SpringBootApplication
public class JwtYoutubeApplication {
	/*@Autowired
	private EmailSenderService senderService;*/

    public static void main(String[] args) {
        SpringApplication.run(JwtYoutubeApplication.class, args);
    }
 /*   @EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
		senderService.sendSimpleEmail("amanicherif431@gmail.com","This is email body","This is email subject");

	}*/
}
