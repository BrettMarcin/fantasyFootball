package com.home;

import java.util.List;

public interface MessageDAO {
    public List<MessageContents> getMessages();
    public void addMessage(MessageContents message);
}
