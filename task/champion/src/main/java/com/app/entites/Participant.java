package com.app.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name ="Participant")
public class Participant {
	
	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participant_generator")
	@SequenceGenerator(name = "participant_generator", sequenceName = "PARTICIPANT_SEQ", allocationSize = 1)

	private Integer id ; 
	@Column(name ="name")

	private String name ;
	
	@Column(name ="status")

	private Integer status ;

	@Column(name ="email")

	private String email;
	

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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
