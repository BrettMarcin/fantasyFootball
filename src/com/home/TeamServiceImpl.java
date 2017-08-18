package com.home;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService{
	@Autowired
	private TeamDAO teamDAO;
	
	@Override
	@Transactional
	public List<Team> getTeams() {
		return teamDAO.getTeams();
	}
	
	@Override
	@Transactional
	public void saveTeam(Team theTeam) {
		teamDAO.saveTeam(theTeam);
	}
	
	@Override
	@Transactional
	public Team getTeam(int theId){
		Team theTeam = teamDAO.getTeam(theId);
		return theTeam;
	}

	@Override
	@Transactional
	public Team getTeamByTeamName(String theTeamName){
		List<Team> theTeams = getTeams();
		for (Team theTeam : theTeams){
			if(theTeam.teamName.equals(theTeamName))
				return theTeam;
		}
		return null;
	}
	
	@Override
	@Transactional
	public void deleteTeam(int theId){
		teamDAO.deleteTeam(theId);
	}
	@Override
	@Transactional
	public void clearTeams(List<Team> theTeams){
		teamDAO.clearTeams(theTeams);
	}
	@Override
	@Transactional
	public void updateTeam(Team localTeam){
		teamDAO.updateTeam(localTeam);
	}
}
