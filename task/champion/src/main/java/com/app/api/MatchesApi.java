package com.app.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.Mapper.ResultMatch;
import com.app.entites.League;
import com.app.entites.Matches;
import com.app.entites.Participant;
import com.app.service.LeagueService;
import com.app.service.MatchService;
import com.app.util.ApiExecption;

@RestController
@RequestMapping("/api")
/// oranganzeration leageue
public class MatchesApi {

	@Autowired
	private MatchService matchService;

	@Autowired

	private LeagueService leagueService;

	List<Participant> Participants = null;
	List<Matches> matches = null;
	ArrayList<Integer> randomNumber = new ArrayList<Integer>();
	int currnt = 0;

	@RequestMapping(method = RequestMethod.POST, path = "/stratLeague", consumes = "application/json", produces = "application/json")

	// start league
	public List<Matches> startLeague(@RequestBody Integer leagueId) throws Exception {

		ResponseEntity<Matches> response = null;
		// get active Participants
		Participants = this.matchService.findAllParticipant(1);

//		Optional<League> league = leagueService.getLeague(leagueId);
//		if (league.equals(Optional.empty())  ) {
//			
//			
//			throw new Exception(" invalid league ");
//		}
		// check validarion like league is new
		matches = this.matchService.prepareLeague(Participants, leagueId);

// start round one 
		matchService.startLeague(matches);

		return matches;

	}

	@PostMapping(path = "/editResult", consumes = "application/json", produces = "application/json")
	public Matches editResult(@RequestBody ResultMatch resultMatch) throws Exception {

		return this.matchService.editresultMatch(resultMatch.getId(), resultMatch.getHomeResult(),
				resultMatch.getWayResult(), resultMatch.getIsWin());

	}

	@PostMapping(path = "/nextRound", consumes = "application/json", produces = "application/json")
	public List<Matches> nextRound(@RequestBody Integer leagueId) throws Exception {

		List<Matches> winners = this.matchService.getWinners(leagueId);

		
		
		return this.matchService.nextRound(leagueId, winners);
	}

	/// close round by user
	@PostMapping(path = "/closeRound", consumes = "application/json", produces = "application/json")
	public List<Matches> closeRound(@RequestBody Integer leagueId) throws Exception {

		League league = leagueService.getLeague(leagueId).get();

		if (league.getStatus() == 3) {

			throw new Exception("league is completed");
		}

		if (league.getStatus() == 1) {

			throw new Exception("league is new");
		}

		List<Matches> checkRoundIsplaying = this.matchService.checkRoundIsplaying(leagueId);

		if (checkRoundIsplaying.size() > 0) {
			throw new Exception("please make sure all matches are played");

		}

		List<Matches> winners = this.matchService.getWinners(leagueId);

		return this.matchService.closeRound(winners);
	}

///  edit any thing like winner
	@PostMapping(path = "/editMatch", consumes = "application/json", produces = "application/json")
	public Matches editMatch(@RequestBody Matches matches) throws Exception {

		return this.matchService.editMatch(matches);
	}

	/// add new match manuel
	@PostMapping(path = "/newMatch", consumes = "application/json", produces = "application/json")
	public Matches editResult(@RequestBody Matches matches) throws Exception {

		return this.matchService.newMatch(matches);
	}

	@GetMapping(path = "/findMatch")
	public Matches findMatch(@PathParam(value = "/id") int id) {

		return this.matchService.findMatch(id);
	}

	@GetMapping(path = "/findAllMatches")
	public List<Matches> findAllParticipant() {

		return this.matchService.findAllMatches();
	}

	public MatchService getMatchService() {
		return matchService;
	}

	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}

	public LeagueService getLeagueService() {
		return leagueService;
	}

	public void setLeagueService(LeagueService leagueService) {
		this.leagueService = leagueService;
	}

}
