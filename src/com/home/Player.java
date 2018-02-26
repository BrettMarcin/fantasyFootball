package com.home;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.logging.Logger;


@Entity
@Table(name="Player")
@XmlRootElement public class Player{

	private final static Logger log = Logger.getLogger(Player.class.getName());

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Id
	@Column(name="id_player")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	@XmlElement public String first;
	@Column
	@XmlElement public String last;
	@Column
	@XmlElement public String pos;
	@Column
	@XmlElement public String team = "FA";
	@Column
	@XmlElement public String Fpoints = "-";
	@Column
	@XmlElement public String passYards = "-";
	@Column
	@XmlElement public String passTDs = "-";
	@Column
	@XmlElement public String ints = "-";
	@Column
	@XmlElement public String rushYards = "-";
	@Column
	@XmlElement public String rushTDs = "-";
	@Column
	@XmlElement public String fum = "-";
	@Column
	@XmlElement public int rank;
	@Column
	@XmlElement public String recYards = "-";
	@Column
	@XmlElement public String recTDs = "-";
	@Column
	@XmlElement public String teamOwner = "";
	
	public Player(int rank, String first, String last, String pos){
		this.first = first;
		this.last = last;
		this.rank = rank;
		this.pos = pos;
	}
	
	public Player(String first, String last, String pos, String team, int rank){
		this.first = first;
		this.last = last;
		this.pos = pos;
		this.team = team;
		this.rank = rank;
	}
	
	public Player(String first, String last, String pos, String FPoints, String passYards, String passTDs, String ints, String rushYards, String rushTDs, String recYards, String recTDs, String fumble, String theTeam){
		this.first = first;
		this.last = last;
		this.pos = pos;
		this.team = theTeam;
		this.Fpoints = FPoints;
		this.passYards = passYards;
		this.passTDs = passTDs;
		this.ints = ints;
		this.rushYards = rushYards;
		this.rushTDs = rushTDs;
		this.fum = fumble;
		this.recYards = recYards;
		this.recTDs = recTDs;
	}
	
	public Player(String first, String last, String pos, String FPoints, String passYards, String passTDs, String ints, String rushYards, String rushTDs, String recYards, String recTDs, String fumble){
		this.first = first;
		this.last = last;
		this.pos = pos;
		this.Fpoints = FPoints;
		this.passYards = passYards;
		this.passTDs = passTDs;
		this.ints = ints;
		this.rushYards = rushYards;
		this.rushTDs = rushTDs;
		this.fum = fumble;
		this.recYards = recYards;
		this.recTDs = recTDs;
	}
	
	public Player(){
		super();
	}

	public int id(){
		return id;
	}
	
	public Player isMatch(Player theOther){
		if(theOther.first.equals(first) && theOther.last.equals(last) && theOther.team.equals(team) && theOther.pos.equals(pos)) {
			return this;
			//return true;
		} else {
			return null;
			//return false;
		}
	}
	public void updateTeamOwner(String owner){
		teamOwner = owner;
	}
	
}
