package com.home;

import java.util.List;

public interface TeamDAO {
	
	public List<Team> getTeams();
	
	public void saveTeam(Team theTeam);
}
