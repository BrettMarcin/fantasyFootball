package com.home;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MessageDAOImpl implements MessageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Message> getTeams() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Message> theQuery = currentSession.createQuery("from Message", Message.class);
        List<Message> messageContents = theQuery.getResultList();
        return messageContents;
    }

    @Override
    public void addMessage() {

    }
}
