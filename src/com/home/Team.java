package com.home;


import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@ElementCollection
	public ArrayList<Player> QB;
	@ElementCollection
	public ArrayList<Player> WR;
	@ElementCollection
	public ArrayList<Player> RB;
	@ElementCollection
	public ArrayList<Player> TE;
	@ElementCollection
	public ArrayList<Player> DST;
	
	public Team(String theTeamName){
		teamName = theTeamName;
	}
}
