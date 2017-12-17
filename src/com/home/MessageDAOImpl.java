package com.home;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class MessageDAOImpl implements MessageDAO {
    private final static Logger log = Logger.getLogger(MessageDAO.class.getName());
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Messages> getMessages() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Messages> theQuery = currentSession.createQuery("from Messages", Messages.class);
        List<Messages> messageContents = theQuery.getResultList();
        return messageContents;
    }

    @Override
    public void addMessage(Messages message) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.save(message);
        }
        catch(Exception e){
            log.severe(e.toString());
        }
    }
    @Override
    public void clearMessages(List<Messages> messages){
        for (Messages theMessage : messages){
            Session currentSession = sessionFactory.getCurrentSession();
            Query theQuery = currentSession.createQuery("delete from Messages where id_message=:messageId");
            theQuery.setParameter("messageId", theMessage.id);
            theQuery.executeUpdate();
        }
    }
}
