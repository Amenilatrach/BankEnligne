package tn.esprit.ourbank.Service.Implementation;

import org.springframework.stereotype.Service;

import tn.esprit.ourbank.DAO.Entities.SmsRequest;
import tn.esprit.ourbank.Service.Interface.SmsSenderService;
import org.springframework.beans.factory.annotation.Qualifier;


import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ServiceImpl {

	 private final SmsSenderService smsSenderService;

	    @Autowired
	    public ServiceImpl(@Qualifier("twilio") TwilioSmsSenderServiceImpl smsSender) {
	        this.smsSenderService = smsSender;
	    }

	    public void sendSms() {
	    	smsSenderService.sendSms();
	    }
	
}
