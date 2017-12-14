package com.home;

import java.util.List;

public interface MessageService {
    public List<MessageContents> getMessages();
    public void addMessage(MessageContents theMessage);
}
