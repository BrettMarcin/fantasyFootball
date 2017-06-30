package com.home;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateOrder {

	public static List<Team> order(List<Team> theTeams){
		List<Team> theTimeline = new ArrayList<>();
		Collections.shuffle(theTeams);
		List<Team> oddTeams = theTeams;
		Collections.reverse(oddTeams);
		for (int i = 0; i < 12; i++){
			if (i % 2 == 0){
				theTimeline.addAll(theTeams);
			} else {
				theTimeline.addAll(oddTeams);
			}
			theTimeline.add(new Team("Round"));
		}
		return theTimeline;
	}
}
