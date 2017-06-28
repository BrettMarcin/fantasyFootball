package com.home;

import java.util.List;

public interface TeamService {

	public List<Team> getTeams();
	
	public void saveTeam(Team theTeam);
	
}
