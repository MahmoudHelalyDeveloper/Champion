package com.app.model;

import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.entites.Participant;

@Repository
public interface ParticipantRepository  extends CrudRepository<Participant, Integer> {
	
	public List<Participant> findBystatus(Integer status);

}
