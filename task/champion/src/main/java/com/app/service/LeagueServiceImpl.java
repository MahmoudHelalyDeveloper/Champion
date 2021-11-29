package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.app.entites.League;
import com.app.model.LeagueRepository;

@Service
@ComponentScan("com.app.model")

public class LeagueServiceImpl implements LeagueService {

	@Autowired
	private LeagueRepository LeagueRepository;
	
	
	@Override
	public Optional<League> getLeague(Integer id) {
		// TODO Auto-generated method stub
		return this.LeagueRepository.findById(id);
	}




	@Override
	public List<League> findAllLeagues() {
		// TODO Auto-generated method stub
		return (List<League>) this.LeagueRepository.findAll();
	}




	@Override
	public League editLeage(League league) {
		// TODO Auto-generated method stub
		return this.LeagueRepository.save(league);
	}
	
	


	@Override
	public League addLeague(League league) {
		// TODO Auto-generated method stub
		return LeagueRepository.save(league);
	} 
	
	
	

	public LeagueRepository getLeagueRepository() {
		return LeagueRepository;
	}
	
	
	public void setLeagueRepository(LeagueRepository leagueRepository) {
		LeagueRepository = leagueRepository;
	}




	@Override
	public void deleteLeague(League league) {
		// TODO Auto-generated method stub
		this.LeagueRepository.delete(league);
		
	}


	
	
	
	
	
	



}
