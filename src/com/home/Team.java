package com.home;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	public int id;
	@Column
	public String name;
	@Column
	public String teamName;
	@JoinColumn(name="QB")
	@OneToMany(fetch= FetchType.LAZY)
	public List<Player> QB = new ArrayList();
	@JoinColumn(name="WR")
	@OneToMany(fetch= FetchType.LAZY)
	public List<Player> WR = new ArrayList();
	@JoinColumn(name="RB")
	@OneToMany(fetch= FetchType.LAZY)
	public List<Player> RB = new ArrayList();
	@JoinColumn(name="TE")
	@OneToMany(fetch= FetchType.LAZY)
	public List<Player> TE = new ArrayList();
	@JoinColumn(name="DST")
	@OneToMany(fetch= FetchType.LAZY)
	public List<Player> DST = new ArrayList();
	
	public Team(String theTeamName, String theName){
		teamName = theTeamName;
		name = theName;
	}
	
	public Team(String theTeamName){
		teamName = theTeamName;
	}
	
	public Team(){
		super();
	}
	
	public void addPlayer(Player thePlayer){
		switch(thePlayer.pos){
		case "QB":
			QB.add(thePlayer);
			break;
		case "WR":
			WR.add(thePlayer);
			break;
		case "RB":
			RB.add(thePlayer);
			break;
		case "TE":
			TE.add(thePlayer);
			break;
		case "DST":
			DST.add(thePlayer);
			break;
		}
	}
	
	public List<Player> getRB(){
		return RB;
	}
}
