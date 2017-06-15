package com.home;

public class Node {
	public Node left;
	public Node right;
	public Player theData;
	
	public Node(Player thePlayer){
		theData = thePlayer;
		left = null;
		right = null;
	}
	
	public void addPlayerInfo(Player thePlayer){
		thePlayer.rank = theData.rank;
		theData = thePlayer;
	}
}
