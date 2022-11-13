package tn.esprit.spring.entity;

import java.util.Map;

import javax.mail.internet.MimeMessage;

public class Mail {
    private String from;
    private String to;
    private String subject;
    private Map<String, Object> model;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Map<String, Object> getModel() {
		return model;
	}
	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
	public static MimeMessage withHtml(String email, String string, String emailContent) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
