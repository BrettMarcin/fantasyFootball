package com.home;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.logging.Logger;

@Service
public class MessageServiceImpl implements MessageService {
    private final static Logger log = Logger.getLogger(MessageService.class.getName());
    @Autowired
    private MessageDAO messageDAO;

    @Override
    @Transactional
    public List<MessageContents> getMessages() {
        return messageDAO.getMessages();
    }

    @Override
    @Transactional
    public void addMessage(MessageContents theMessage) {
        String json = null;
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            json = ow.writeValueAsString(theMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("inside messageService: " + json);
        MessageDAO messageDAO = new MessageDAOImpl();
        messageDAO.addMessage(theMessage);
    }
}
