package com.home;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;


@Entity
@Table(name="Player")
@XmlRootElement public class Player {
	
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
	@XmlElement public String team;
	@Column
	@XmlElement public String Fpoints;
	@Column
	@XmlElement public String passYards;
	@Column
	@XmlElement public String passTDs;
	@Column
	@XmlElement public String ints;
	@Column
	@XmlElement public String rushYards;
	@Column
	@XmlElement public String rushTDs;
	@Column
	@XmlElement public String fum;
	@Column
	@XmlElement public int rank;
	@Column
	@XmlElement public String recYards;
	@Column
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
	
	public Player(){
		super();
	}
	
}
