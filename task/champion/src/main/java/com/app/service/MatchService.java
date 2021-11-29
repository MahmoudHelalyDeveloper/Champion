package com.app.service;

import java.util.List;

import com.app.entites.League;
import com.app.entites.Matches;
import com.app.entites.Participant;

public interface MatchService {

	public List<Participant> findAllParticipant( Integer status );
	
	public Integer getLeague();
	
	public List<Matches> startLeague( List<Matches> matches); 
	
	public List<Matches> prepareLeague(List<Participant> participants , Integer league) throws Exception;
	
	public Matches getMatch(Integer id); 
	
	
	public Matches editresultMatch(Integer id ,   Double hoemeResult , Double wayResult , Integer isWin ) throws Exception;
	
	
	public List<Matches> getWinners(Integer leagueId);

	
	public List<Matches> checkRoundIsplaying(Integer leagueId);

	public List<Matches> nextRound(Integer leagueId , List<Matches> win  ) throws Exception ;
	
	
	public List<Matches> closeRound(List<Matches> winner ) throws Exception ;
	
	public Matches newMatch(Matches matches);

	public Matches editMatch(Matches matches);
	
	public Matches findMatch(Integer match);
	
	
	public List<Matches> findAllMatches();


	
	
	
}
