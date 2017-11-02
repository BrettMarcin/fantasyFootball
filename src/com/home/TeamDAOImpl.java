package com.home;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		Session currentSession = sessionFactory.getCurrentSession();
		if(localTeam.QB == null)
            Hibernate.initialize(localTeam.QB);
        if(localTeam.WR1 == null)
            Hibernate.initialize(localTeam.WR1);
        if(localTeam.WR2 == null)
            Hibernate.initialize(localTeam.WR2);
        if(localTeam.TE == null)
            Hibernate.initialize(localTeam.TE);
        if(localTeam.DST == null)
            Hibernate.initialize(localTeam.DST);
        if(localTeam.RB1 == null)
            Hibernate.initialize(localTeam.RB1);
        if(localTeam.RB2 == null)
            Hibernate.initialize(localTeam.RB2);
        if(localTeam.FLEX == null)
            Hibernate.initialize(localTeam.FLEX);
        if(localTeam.bench.size() == 0)
            Hibernate.initialize(localTeam.bench);
		currentSession.saveOrUpdate(localTeam);
	}
}