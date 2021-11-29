package com.app.api;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entites.Participant;
import com.app.service.ParticipantService;

@RestController
@RequestMapping("/api")
public class participantApi {

	@Autowired
	private ParticipantService participantService;

	@PostMapping(path = "/initParticipant", consumes = "application/json", produces = "application/json")
	public Participant initParticipant(@RequestBody Participant participant) {

		return this.participantService.initParticipant(participant);
	}

	@PostMapping(path = "/approveParticipant", consumes = "application/json", produces = "application/json")
	public Participant createParticipant(@RequestBody @Valid Participant participant) {

		return this.participantService.createParticipant(participant);
	}

	@GetMapping(path = "/findParticipant")
	public Participant findParticipant(@PathParam(value = "/id") int id) {

		return this.participantService.findByOne(id);
	}

	@PostMapping(path = "/editParticipant", consumes = "application/json", produces = "application/json")
	public Participant editParticipant(@RequestBody Participant participant) {

		return this.participantService.editParticipant(participant);
	}

	@GetMapping(path = "/findAllParticipant")
	public List<Participant> findAllParticipant() {

		return this.participantService.findAll();
	}
	
	
	@DeleteMapping(path = "/deleteParticipant")
	public void deleteParticipant( @PathParam(value = "/id")  int id ) {

		 this.participantService.deleteParticipant(this.findParticipant(id));
	}

	public ParticipantService getParticipantService() {
		return participantService;
	}

	public void setParticipantService(ParticipantService participantService) {
		this.participantService = participantService;
	}

}
