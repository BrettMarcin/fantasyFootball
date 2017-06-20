package com.home;

/*
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
*/
import java.util.ArrayList;

//@Entity
//@Table(name="Team")
public class Team {

	//@Id
	//@Column(name="id")
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//@Column
	public String name;
	//@Column
	public String teamName;
	//@Column
	public ArrayList<Player> QB;
	//@Column
	public ArrayList<Player> WR;
	//@Column
	public ArrayList<Player> RB;
	//@Column
	public ArrayList<Player> TE;
	//@Column
	public ArrayList<Player> DST;
	
	public Team(String theTeamName){
		teamName = theTeamName;
	}
}
