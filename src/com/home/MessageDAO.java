package com.home;

import java.util.List;

public interface MessageDAO {
    public List<Messages> getMessages();
    public void addMessage(Messages message);
    public void clearMessages(List<Messages> messages);
}
