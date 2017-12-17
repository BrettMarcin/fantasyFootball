package com.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Service
public class MessageServiceImpl implements MessageService {
    private final static Logger log = Logger.getLogger(MessageService.class.getName());
    @Autowired
    private MessageDAO messageDAO;

    @Override
    @Transactional
    public List<Messages> getMessages() {
        return messageDAO.getMessages();
    }

    @Override
    @Transactional
    public void addMessage(Messages theMessage) {
        messageDAO.addMessage(theMessage);
    }
    @Override
    @Transactional
    public void clearMessages(){
        List<Messages> messages = messageDAO.getMessages();
        if(messages.size() > 0) {
            messageDAO.clearMessages(messages);
        }
    }
}
