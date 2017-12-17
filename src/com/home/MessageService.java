package com.home;

import java.util.List;

public interface MessageService {
    public List<Messages> getMessages();
    public void addMessage(Messages theMessage);
    public void clearMessages();
}
