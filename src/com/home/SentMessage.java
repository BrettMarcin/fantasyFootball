package com.home;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import java.util.logging.Logger;

public class SentMessage
{
    private final static Logger log = Logger.getLogger(SentMessage.class.getName());
    private String text;
    private String author;

    public SentMessage() {
    }
    public SentMessage(String text, String author)
    {
        this.text = text;
        this.author = author;
    }
    public String getText()
    {
        return text;
    }
    public String getAuthor(){
        return author;
    }
}