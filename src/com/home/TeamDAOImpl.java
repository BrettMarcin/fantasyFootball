package com.home;

import java.util.List;

import org.hibernate.Hibernate;
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
		Query<Team> theQuery = currentSession.createQuery("from Team", Team.class);
		List<Team> teams = theQuery.getResultList();		
		return teams;
	}
	
	@Override
	public void saveTeam(Team theTeam){
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(theTeam);
	}
	
	@Override
	public Team getTeam(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Team theTeam = currentSession.get(Team.class, theId);
		return theTeam;
	}
	
	@Override
	public void deleteTeam(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("delete from Team where id=:teamId");
		theQuery.setParameter("teamId", theId);
		theQuery.executeUpdate();		
	}
	
	@Override
	public void clearTeams(List<Team> theTeams){
		for (Team theTeam : theTeams){
			Session currentSession = sessionFactory.getCurrentSession();
			Query theQuery = currentSession.createQuery("delete from Team where id=:teamId");
			theQuery.setParameter("teamId", theTeam.id);
			theQuery.executeUpdate();	
		}
	}
	
	@Override
	public void updateTeam(Team localTeam){
        sessionFactory.getCurrentSession().update(localTeam);;
	}
}