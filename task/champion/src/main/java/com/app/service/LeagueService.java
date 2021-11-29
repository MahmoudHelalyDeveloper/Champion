package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.entites.League;
public interface LeagueService {

	public League addLeague(League league);
	
	public  Optional<League> getLeague(Integer id);
	
	public List<League> findAllLeagues(); 
	
	public League editLeage(League league);
	
	public void deleteLeague(League league);
	
}
