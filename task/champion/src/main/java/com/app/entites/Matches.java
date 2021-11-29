package com.app.entites;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name ="Matches")
@NamedQuery( name = "Matches.getWinner", query = "select m from Matches m  where leagueId=:leagueId and isWin is not null and round is null ")
@NamedQuery( name = "Matches.checkRoundIsPalying", query = "select m from Matches m  where leagueId=:leagueId and isWin is  null")
@NamedQuery( name = "Matches.checkIsFinalMatch", query = "select m from Matches m  where leagueId=:leagueId and round is  null")

@NamedQuery( name = "Matches.collectWinnerAfterClose", query = "select m from Matches m  where leagueId=:leagueId and round=1")

public class Matches {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matches_generator")
	@SequenceGenerator(name = "matches_generator", sequenceName = "MATCHES_SEQ", allocationSize = 1)
	private Integer id; 
	
	@Column(name="LEAGUE_ID")
	private Integer leagueId; 
	
	@Column(name="HOME_PARTICIPANT_ID")

	private Integer homeParticipant;
	
	
	@Column(name="way_PARTICIPANT_ID")

	private Integer wayParticipant;

	@Column(name="HOME_RESULAT")

	private Double homeResulat;
	
	@Column(name="way_RESULAT")

	private Double wayResulat;
	
	@Column(name="ISWIN")

	private Integer isWin; 
	
	@Column(name="START_MATCH")
	@Basic
	@Temporal(TemporalType.DATE)
	private Date startMatch; 
	
	@Column(name="ROUND_NO")
	private Integer round;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Integer leagueId) {
		this.leagueId = leagueId;
	}

	public Integer getHomeParticipant() {
		return homeParticipant;
	}

	public void setHomeParticipant(Integer homeParticipant) {
		this.homeParticipant = homeParticipant;
	}

	public Integer getWayParticipant() {
		return wayParticipant;
	}

	public void setWayParticipant(Integer wayParticipant) {
		this.wayParticipant = wayParticipant;
	}

	public Double getHomeResulat() {
		return homeResulat;
	}

	public void setHomeResulat(Double homeResulat) {
		this.homeResulat = homeResulat;
	}

	public Double getWayResulat() {
		return wayResulat;
	}

	public void setWayResulat(Double wayResulat) {
		this.wayResulat = wayResulat;
	}

	public Integer getIsWin() {
		return isWin;
	}

	public void setIsWin(Integer isWin) {
		this.isWin = isWin;
	}

	public Date getStartMatch() {
		return startMatch;
	}

	public void setStartMatch(Date startMatch) {
		this.startMatch = startMatch;
	}

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}
	
	
	
	
	
	


	
	
	
	
}
