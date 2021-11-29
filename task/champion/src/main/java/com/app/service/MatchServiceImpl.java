package com.app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entites.League;
import com.app.entites.Matches;
import com.app.entites.Participant;
import com.app.model.LeagueRepository;
import com.app.model.MatchRepository;
import com.app.model.ParticipantRepository;
import com.app.util.ApiExecption;
import com.app.util.SendEmail;

@Service
public class MatchServiceImpl implements MatchService {

	@Autowired
	private ParticipantRepository participantRepository;

	@Autowired
	private MatchRepository matchRepository;
	@Autowired

	private LeagueRepository leagueRepository;
	
	@Autowired 
	private SendEmail sendEmail;
	

	private int maxMatches = 3;
	
	private int maxParticipants = 12;


	private int nextMatches = 0;
	
	private int currnt;
	
	List<Matches> matches =null;
	
	
	

	@Override
	public List<Participant> findAllParticipant(Integer status) {
		// TODO Auto-generated method stub
		return (List<Participant>) participantRepository.findBystatus(status);
	}

	@Override
	public Integer getLeague() {
		// TODO Auto-generated method stub
		return null;
	}


	
	

	@Override
	public List<Matches> prepareLeague(List<Participant> participants, Integer leagueId) throws Exception {
		// TODO Auto-generated method stub
		
		matches = null;
		matches =  new ArrayList<Matches>();
		nextMatches=0;
		Collections.shuffle(participants);
		currnt = 0;
		int checkMaxNumber = maxMatches;

		// to check maxParticipants 
		if(maxParticipants<participants.size()) {
			
	   		throw new Exception(" please make sure participants lessthan 12  ");

			
		}
		

       /// to check league is already exists
       Optional<League> league = leagueRepository.findById(leagueId);
       if (league.equals(Optional.empty())  ) {
    	   
    	
   		throw new Exception(" invalid league ");

    	   
    	   
       }
       /// to check league is already plays
       if (league.get().getStatus()==2) {
    		throw new Exception(" league is inprogress ");
    		  
       }
       
     /// to validate maximum mateches per days
       Calendar calendar = Calendar.getInstance();

		for (int i = 0; i < participants.size() / 2; i++) {
			Matches match = new Matches();

			match.setHomeParticipant(participants.get(currnt).getId());
			currnt++;
			match.setWayParticipant(participants.get(currnt).getId());
			match.setLeagueId(leagueId);

			if (checkMaxNumber - 1 == 0) {
				checkMaxNumber = maxMatches;

				calendar.setTime(league.get().getStartDate());
				calendar.add(Calendar.DATE, nextMatches);
				match.setStartMatch(calendar.getTime());
				nextMatches = nextMatches + 1;
			} else {

				calendar.setTime(league.get().getStartDate());
				calendar.add(Calendar.DATE, nextMatches);
				match.setStartMatch(calendar.getTime());
				checkMaxNumber--;
			}

			matches.add(match);

			currnt++;

		}
		//  league is not new
		league.get().setStatus(2);
		leagueRepository.save(league.get());
		return matches;

	}
	
	@Override
	public List<Matches> startLeague(List<Matches> matches) {
		// TODO Auto-generated method stub

		return (List<Matches>) matchRepository.saveAll(matches);

	}

	
	
	@Override
	public Matches getMatch(Integer id) {
		// TODO Auto-generated method stub
		Optional<Matches> findById = this.matchRepository.findById(id);
		
		return findById.get() ;
	}

	@Override
	public Matches editresultMatch(Integer id , Double hoemeResult, Double wayResult, Integer isWin) throws Exception {
		// TODO Auto-generated method stub
		// get current match
		
		///  match was played and resilat submt
		
		Matches match = this.getMatch(id);
		
		if ( null ==match) {
			
			throw new Exception("invalid input paramter");
			
		}
		
if ( null !=match.getRound()) {
	
	throw new Exception("match was played");
	
}



		match.setIsWin(isWin);
		match.setHomeResulat(hoemeResult);
		match.setWayResulat(wayResult);

		League leagueId = this.leagueRepository.findById(match.getLeagueId()).get();
		List<Matches> finalMatch = this.matchRepository.checkIsFinalMatch(leagueId.getId());
		
		/// to set winner and end champion
if(finalMatch.size()==1 || finalMatch.isEmpty()  ) {
	
	match.setRound(2);
	leagueId.setStatus(3);
	this.leagueRepository.save(leagueId); 
	
	try {
		Participant participant = participantRepository.findById(isWin).get();
		sendEmail.sendmail(participant.getEmail(),leagueId.getName());
	} catch (AddressException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

		
		this.matchRepository.save(match);
		
		return match;
	}

	@Override
	public List<Matches> nextRound(Integer leagueId, List<Matches> winners) throws Exception {
		// TODO Auto-generated method stub

		matches =  new ArrayList<Matches>();
		int checkMaxNumber = maxMatches;
currnt=0;
		///  to check invalid inout from user 
		  Optional<League> league = leagueRepository.findById(leagueId);
	       if (league.equals(Optional.empty())  ) {
	    	   
	    	   throw new Exception(" invalid league ");
	    	   
	    	   
	       }
	       
	       if (league.get().getStatus()!=2) {
	    		throw new Exception(" league is new or done ");
	    	   
	       }
	       checkRoundIsplaying(leagueId);
	       
	       if(checkRoundIsplaying(leagueId).size()>0) {
	    	   
	    	   throw new Exception("please make sure all matches were played before sumbit ");
	    	   
	       }
	       
	       
	       
	       ///  end  round
	       winners.stream().forEach(elt -> elt.setRound(1) );
	       
			matchRepository.saveAll(winners);

			//  if user uses close round feature 
			
			if (winners.size()==0)
			{
				
				winners=matchRepository.collectWinnerAfterClose(leagueId);
			}
	       
			
	       ////    get wiiner form pri
	       
	       List<Participant> participants = new ArrayList<Participant>();
			for (Matches matches : winners) {
				Participant winnerMatch = new Participant();
				winnerMatch.setId(matches.getIsWin());
				participants.add(winnerMatch);
			}
	    
	       
	       Calendar calendar = Calendar.getInstance();
			calendar.setTime(league.get().getStartDate());

			for (int i = 0; i < participants.size() / 2; i++) {
				Matches match = new Matches();

				match.setHomeParticipant(participants.get(currnt).getId());
				currnt++;
				match.setWayParticipant(participants.get(currnt).getId());
				match.setLeagueId(leagueId);

				if (checkMaxNumber - 1 == 0) {
					checkMaxNumber = maxMatches;

					calendar.add(Calendar.DATE, nextMatches);
					match.setStartMatch(calendar.getTime());
					nextMatches = nextMatches + 1;
				} else {

					calendar.add(Calendar.DATE, nextMatches);
					match.setStartMatch(calendar.getTime());
					checkMaxNumber--;
				}

				matches.add(match);

				currnt++;

			}
		
			matchRepository.saveAll(matches);
	       
		return matches;
	}
	

	@Override
	public List<Matches> getWinners(Integer leagueId) {

	return	     this.matchRepository.getWinner(leagueId);

	
	}
	
	

	@Override
	public List<Matches> checkRoundIsplaying(Integer leagueId) {
		// TODO Auto-generated method stub
		return this.matchRepository.checkRoundIsPalying(leagueId);
	}

	
	
	
	@Override
	public List<Matches> closeRound(List<Matches> winners ) throws Exception {
		// TODO Auto-generated method stub
	
		  winners.stream().forEach(elt -> elt.setRound(1) );
		  this.matchRepository.saveAll(winners);
		return winners;
	}


	
	
	
	
	@Override
	public Matches newMatch(Matches matches) {
		// TODO Auto-generated method stub
		return this.matchRepository.save(matches);
	}

	@Override
	public Matches editMatch(Matches matches) {
		// TODO Auto-generated method stub
		return this.matchRepository.save(matches);
	}

	@Override
	public Matches findMatch(Integer match) {
		// TODO Auto-generated method stub
		return this.matchRepository.findById(match).get();
	}

	@Override
	public List<Matches> findAllMatches() {
		// TODO Auto-generated method stub
		 	return (List<Matches>) this.matchRepository.findAll();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ParticipantRepository getParticipantRepository() {
		return participantRepository;
	}

	public void setParticipantRepository(ParticipantRepository participantRepository) {
		this.participantRepository = participantRepository;
	}

	
	public MatchRepository getMatchRepository() {
		return matchRepository;
	}

	public void setMatchRepository(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}

	public LeagueRepository getLeagueRepository() {
		return leagueRepository;
	}

	public void setLeagueRepository(LeagueRepository leagueRepository) {
		this.leagueRepository = leagueRepository;
	}

	public SendEmail getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(SendEmail sendEmail) {
		this.sendEmail = sendEmail;
	}



	

}
