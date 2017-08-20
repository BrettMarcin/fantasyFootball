package com.home.team;

import java.util.List;

public interface TeamService {
	public List<Team> getTeams();
	public void saveTeam(Team theTeam);
	public Team getTeam(int theId);
	public void deleteTeam(int theId);
	public void clearTeams(List<Team> theTeams);
	public void updateTeam(Team localTeam);
	public Team getTeamByTeamName(String theTeamName);
}
