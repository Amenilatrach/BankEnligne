package tn.esprit.ourbank.Configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration


public class TwilioConfiguration {
	
	private final String accountSid = "AC01b1617f1aa70fddca75a48511f7442c";
    private final String authToken = "672f49213cd08dba5b8516893e4b32ef";
    private final String trialNumber = "+15136476338";

   
    
    public TwilioConfiguration() {
    	
    }    

	public String getAccountSid() {
        return accountSid;
	}

    public String getAuthToken() {
        return authToken;
    }

   
    public String getTrialNumber() {
        return trialNumber;
    }

	
	
}
