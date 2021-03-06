package com.home;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerDAOImpl implements PlayerDAO {

    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Player> getDBPlayers(){
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Player> theQuery = currentSession.createQuery("from Player", Player.class);
        List<Player> players = theQuery.getResultList();
        return players;
    }

    @Override
    public void savePlayer(Player thePlayer){
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(thePlayer);
    }

    @Override
    public void deletePlayer(int theId){}

    @Override
    public void clearPlayers(ArrayList<Player> thePlayers) {
        for (Player thePlayer : thePlayers){
            Session currentSession = sessionFactory.getCurrentSession();
            Query theQuery = currentSession.createQuery("delete from Player where id=:playerId");
            theQuery.setParameter("playerId", thePlayer.id());
            theQuery.executeUpdate();
        }
    }

    @Override
    public void updatePlayer(Player thePlayer){}
}
