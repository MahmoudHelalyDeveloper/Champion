package com.app.service;

import java.util.List;

import com.app.entites.Participant;

public interface ParticipantService {

	
	public Participant initParticipant(Participant participant);
	
	public Participant createParticipant(Participant participant);
	
	public Participant findByOne(Integer participant);
	
	public Participant editParticipant(Participant participant);
	
	public List<Participant> findAll();
	
	
	public void deleteParticipant(Participant participant);

	
}
