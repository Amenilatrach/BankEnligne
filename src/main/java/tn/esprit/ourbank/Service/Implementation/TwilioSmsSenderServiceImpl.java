package tn.esprit.ourbank.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.ourbank.Configuration.TwilioConfiguration;
import tn.esprit.ourbank.DAO.Entities.SmsRequest;
import tn.esprit.ourbank.Service.Interface.SmsSenderService;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("twilio")
public class TwilioSmsSenderServiceImpl implements SmsSenderService {


    
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSenderServiceImpl.class);

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioSmsSenderServiceImpl(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }
	
	@Override
	public void sendSms() {
		SmsRequest smsRequest = new SmsRequest("+21623218551", "Check out our latest Offers 🤩 🥳 🤩 🥳");
		 if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
			 Twilio.init(
		                twilioConfiguration.getAccountSid(),
		                twilioConfiguration.getAuthToken()
		        );
			    PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
	            PhoneNumber from = new PhoneNumber("+15136476338");
	            String message = smsRequest.getMessage();
	            MessageCreator creator = Message.creator(to, from, message);   
				 creator.create();
	            LOGGER.info("Send sms {}", smsRequest);
	        } else {
	            throw new IllegalArgumentException(
	                    "Phone number [" + smsRequest.getPhoneNumber() + "] is not a valid number"
	            );
	        }

	    }

	    private boolean isPhoneNumberValid(String phoneNumber) {
	        // TODO: Implement phone number validator
	        return true;
	    }

}
