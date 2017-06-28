package com.home;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Team")
public class Team {

	@Id
	@Column(name="id_team")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	public String name;
	@Column
	public String teamName;
	
	@JoinColumn(name="QB")
	@OneToMany
	public List<Player> QB;
	@JoinColumn(name="WR")
	@OneToMany
	public List<Player> WR;
	@JoinColumn(name="RB")
	@OneToMany
	public List<Player> RB;
	@JoinColumn(name="TE")
	@OneToMany
	public List<Player> TE;
	@JoinColumn(name="DST")
	@OneToMany
	public List<Player> DST;
	
	public Team(String theTeamName){
		teamName = theTeamName;
	}
	
	public Team(){
		super();
	}
}
