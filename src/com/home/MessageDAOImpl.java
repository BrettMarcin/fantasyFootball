package com.home;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MessageDAOImpl implements MessageDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<MessageContents> getMessages() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<MessageContents> theQuery = currentSession.createQuery("from MessageContents", MessageContents.class);
        List<MessageContents> messageContents = theQuery.getResultList();
        return messageContents;
    }

    @Override
    public void addMessage(MessageContents message) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(message);
    }
}
