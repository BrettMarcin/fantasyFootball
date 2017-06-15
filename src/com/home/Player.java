package com.home;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement public class Player {
	@XmlElement public String first;
	@XmlElement public String last;
	@XmlElement public String pos;
	@XmlElement public String team;
	@XmlElement public String Fpoints;
	@XmlElement public String passYards;
	@XmlElement public String passTDs;
	@XmlElement public String ints;
	@XmlElement public String rushYards;
	@XmlElement public String rushTDs;
	@XmlElement public String fum;
	@XmlElement public int rank;
	@XmlElement public String recYards;
	@XmlElement public String recTDs;
	
	public Player(int rank, String first, String last, String pos){
		this.first = first;
		this.last = last;
		this.rank = rank;
		this.pos = pos;
	}
	public Player(String first, String last, String pos, String FPoints, String passYards, String passTDs, String ints, String rushYards, String rushTDs, String recYards, String recTDs, String fumble){
		this.first = first;
		this.last = last;
		this.pos = pos;
		this.team = null;
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
}
