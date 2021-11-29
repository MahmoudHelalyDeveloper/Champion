package com.app.api;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.entites.League;
import com.app.service.LeagueService;

@RestController
@RequestMapping(path = "/api")
public class LeagueApi {

	@Autowired
	private LeagueService leagueService;

	
	
	
	  @PostMapping( path= "/newLeague", consumes = "application/json", produces = "application/json")
	public League addLeague(@RequestBody League league) {
		
		  if (league.getId()==null) {
			  
			  league.setId(0);			  
		  }
		  
	return	this.leagueService.addLeague(league);
		
	}
	
	@RequestMapping( method = RequestMethod.GET , path = "/getLeague" , produces = "application/json")
	public  Optional<League> getLeague( @PathParam(value = "/id")  int id ) {
		
		return leagueService.getLeague(id);
		
	}
	
	
	  @PostMapping(path= "/editLeague", consumes = "application/json", produces = "application/json")
		public League editLeague(@RequestBody League league) {
			
			
		return	this.leagueService.addLeague(league);
			
		}
	
	  @RequestMapping(path = "/findAllLeague" , consumes = "application/json", produces = "application/json")
		public  List<League> finAllLeague(  ) {
			
			return leagueService.findAllLeagues();
			
		}
	  
	  @RequestMapping( method = RequestMethod.DELETE , path = "/deleteLeague" , produces = "application/json")
		public  void deleteLeague( @PathParam(value = "/id")  int id ) {
			
		  
			 leagueService.deleteLeague(this.leagueService.getLeague(id).get());
			
		}
	
	public LeagueService getLeagueService() {
		return leagueService;
	}

	public void setLeagueService(LeagueService leagueService) {
		this.leagueService = leagueService;
	}
	
	
	
	
}
