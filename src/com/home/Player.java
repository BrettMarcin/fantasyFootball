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
	}
	
}
