package com.home;

import com.home.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateOrder {

	public static ArrayList<Team> order(List<Team> theTeams){
		ArrayList<Team> theTimeline = new ArrayList<>();
		Collections.shuffle(theTeams);
		ArrayList<Team> oddTeams = new ArrayList<>(theTeams);
		Collections.reverse(oddTeams);
		for (int i = 0; i < 12; i++){
			theTimeline.add(new Team("Round"));
			if (i % 2 == 0){
				theTimeline.addAll(theTeams);
			} else {
				theTimeline.addAll(oddTeams);
			}
		}
		return theTimeline;
	}
}
