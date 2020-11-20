package com.ishtoo.interviewscheduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	public void sendEmail(List<String> to, String body, String topic) {
		String[] receivers=new String[to.size()];
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setFrom("ishtmeet.singh00@gmail.com");
		simpleMailMessage.setTo(to.toArray(receivers));
		simpleMailMessage.setSubject(topic);
		simpleMailMessage.setText(body);
		javaMailSender.send(simpleMailMessage);
	} 
}
