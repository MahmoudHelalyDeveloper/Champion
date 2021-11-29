package com.app.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Entity
@Table(name="LEAGUE")
public class League implements Serializable {
    private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "league_generator")
	@SequenceGenerator(name = "league_generator", sequenceName = "LEAGUE_SEQ", allocationSize = 1)

	@Column(name="id")
	private  Integer id ; 
	@Column(name="name")

	private String name ; 
	@Column(name="status")

	private Integer status; 
	
	@Column(name="start_date")
	@Basic
	@Temporal(TemporalType.DATE)
	private Date startDate ;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	
	
	
}
