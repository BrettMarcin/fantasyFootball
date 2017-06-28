package com.home;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TeamDAOImpl implements TeamDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Team> getTeams() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Team> theQuery = 
				currentSession.createQuery("from Team", Team.class);
		List<Team> teams = theQuery.getResultList();		
		return teams;
	}
	
	@Override
	public void saveTeam(Team theTeam){
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(theTeam);
	}

}