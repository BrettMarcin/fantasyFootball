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
	}
	
}