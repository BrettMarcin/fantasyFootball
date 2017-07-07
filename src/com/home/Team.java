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
import javax.transaction.Transactional;

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
	public List<Player> QB;
	@JoinColumn(name="WR")
	@OneToMany(fetch= FetchType.LAZY)
	public List<Player> WR;
	@JoinColumn(name="RB")
	@OneToMany
	public List<Player> RB;
	@JoinColumn(name="TE")
	@OneToMany(fetch= FetchType.LAZY)
	public List<Player> TE;
	@JoinColumn(name="DST")
	@OneToMany(fetch= FetchType.LAZY)
	public List<Player> DST;
	
	public Team(String theTeamName, String theName){
		teamName = theTeamName;
		name = theName;
		QB = new ArrayList<>();
		WR = new ArrayList<>();
		RB = new ArrayList<>();
		TE = new ArrayList<>();
		DST = new ArrayList<>();
	}
	
	public Team(String theTeamName){
		teamName = theTeamName;
		QB = new ArrayList<>();
		WR = new ArrayList<>();
		RB = new ArrayList<>();
		TE = new ArrayList<>();
		DST = new ArrayList<>();
	}
	
	public Team(){
		super();
		QB = new ArrayList<>();
		WR = new ArrayList<>();
		RB = new ArrayList<>();
		TE = new ArrayList<>();
		DST = new ArrayList<>();
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
		if (RB != null){
			return RB;
		} else {
			return null;
		}
	}
}
