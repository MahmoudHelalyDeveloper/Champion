package com.app.api;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.Mapper.SendEmailMapper;
import com.app.service.LeagueService;
import com.app.service.ParticipantService;
import com.app.util.SendEmail;
@RestController
@RequestMapping("/api")
public class SendEmailApi {
	
	@Autowired
	private SendEmail sendEmail;

	@Autowired
	private LeagueService leagueService; 
	
	@Autowired
	private ParticipantService participantService;
	
	@PostMapping(path = "/sendEmail")
	public String sendEmail( @RequestBody  SendEmailMapper sendEmailMapper) throws AddressException, MessagingException, IOException {
		
		
		
		
	
	this.sendEmail.sendmail( this.participantService.findByOne(sendEmailMapper.getParticipant()).getEmail() ,this.leagueService.getLeague(sendEmailMapper.getLeagueId()).get().getName());
//	this.sendEmail.sendmail(this.leagueService.getLeague(sendEmailMapper.getLeagueId()).get().getName(), this.participantService.findByOne(sendEmailMapper.getParticipant()).getEmail());
		
	return "Email send successfully";
	}
	
	public SendEmail getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(SendEmail sendEmail) {
		this.sendEmail = sendEmail;
	}

	public LeagueService getLeagueService() {
		return leagueService;
	}

	public void setLeagueService(LeagueService leagueService) {
		this.leagueService = leagueService;
	}
	
	

}
