package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entites.Participant;
import com.app.model.ParticipantRepository;
@Service
public class ParticipantServiceImpl implements ParticipantService {

	
	@Autowired
	private ParticipantRepository ParticipantRepository ;
	
	@Override
	public Participant initParticipant(Participant participant) {
		// TODO Auto-generated method stub
		participant.setStatus(0);
		return this.ParticipantRepository.save(participant);
	}

	@Override
	public Participant createParticipant(Participant participant) {
		// TODO Auto-generated method stub
		participant.setStatus(1);
		 		return this.ParticipantRepository.save(participant);

	}
	@Override
	public Participant findByOne(Integer participant) {
		// TODO Auto-generated method stub
		return this.ParticipantRepository.findById(participant).get();
	}
	
	
	
	@Override
	public Participant editParticipant(Participant participant) {
		// TODO Auto-generated method stub
		return this.ParticipantRepository.save(participant);
	}
	
	@Override
	public List<Participant> findAll() {
		// TODO Auto-generated method stub
		return (List<Participant>) this.ParticipantRepository.findAll();
	}
	
	
	
	@Override
	public void deleteParticipant(Participant participant) {
		// TODO Auto-generated method stub
		
		this.ParticipantRepository.delete(participant);
		
	}
	
	
	
	
	
	

	public ParticipantRepository getParticipantRepository() {
		return ParticipantRepository;
	}

	public void setParticipantRepository(ParticipantRepository participantRepository) {
		ParticipantRepository = participantRepository;
	}




}
