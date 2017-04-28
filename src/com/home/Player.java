package com.home;

public class Player {
	public String first;
	public String last;
	public String pos;
	public String team;
	public double Fpoints;
	public int passYards;
	public int passTDs;
	public int ints;
	public int rushYards;
	public int rushTDs;
	public int fum;
	public int rank;
	public int recYards;
	public int recTDs;
	
	public Player(int rank, String first, String last){
		this.first = first;
		this.last = last;
		this.rank = rank;
		this.pos = null;
		this.team = null;
		this.Fpoints = 0;
		this.passYards = 0;
		this.passTDs = 0;
		this.ints = 0;
		this.rushYards = 0;
		this.rushTDs = 0;
		this.fum = 0;
		this.recYards = 0;
		this.recTDs = 0;
	}
	public Player(String first, String last, int FPoints, int passYards, int passTDs, int ints, int rushYards, int rushTDs, int recYards, int recTDs){
		this.first = first;
		this.last = last;
		this.pos = null;
		this.team = null;
		this.Fpoints = FPoints;
		this.passYards = passYards;
		this.passTDs = passTDs;
		this.ints = ints;
		this.rushYards = rushYards;
		this.rushTDs = rushTDs;
		this.fum = 0;
		this.recYards = recYards;
		this.recTDs = recTDs;
	}
	public Player(){
		this.first = null;
		this.last = null;
		this.rank = 0;
		this.pos = null;
		this.team = null;
		this.Fpoints = 0;
		this.passYards = 0;
		this.passTDs = 0;
		this.ints = 0;
		this.rushYards = 0;
		this.rushTDs = 0;
		this.fum = 0;
		this.recYards = 0;
		this.recTDs = 0;
	}
	
}
