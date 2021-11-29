package com.app.model;

import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.entites.Matches;

@Repository
public interface MatchRepository extends CrudRepository<Matches, Integer> {
	
	
	public List<Matches> getWinner(Integer leagueId);
	
	public List<Matches> checkRoundIsPalying(Integer leagueId);
	
	public List<Matches> checkIsFinalMatch(Integer leagueId);

	public List<Matches> collectWinnerAfterClose(Integer leagueId);

	
}
