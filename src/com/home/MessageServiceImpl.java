package com.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
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
        MessageDAO messageDAO = new MessageDAOImpl();
        messageDAO.addMessage(theMessage);
    }
}
