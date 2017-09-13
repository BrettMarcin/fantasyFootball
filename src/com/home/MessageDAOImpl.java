package com.home;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Service
public class MessageDAOImpl implements MessageDAO {
    private final static Logger log = Logger.getLogger(MessageDAO.class.getName());
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
        String json = null;
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            json = ow.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("inside messageDAO: " + json);
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(message);
    }
}
