package com.app.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.entites.League;

@Repository
public interface LeagueRepository  extends CrudRepository<League, Integer> {

	 League save( League entity);
	 
	 List<League> findAllByStatus(Integer status);

	
}
