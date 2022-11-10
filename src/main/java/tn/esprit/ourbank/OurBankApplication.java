package tn.esprit.ourbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableScheduling
@EnableSwagger2
@SpringBootApplication
public class OurBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(OurBankApplication.class, args);
        
        
        
    }

}
