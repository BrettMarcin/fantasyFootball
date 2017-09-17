package com.home;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import java.util.logging.Logger;

@XmlRootElement public class SentMessage
{
    private final static Logger log = Logger.getLogger(SentMessage.class.getName());
    @XmlElement private String author;
    @XmlElement private String text;

    public SentMessage()
    {
        super();
        log.info("INSIDE DEFAULT");
    }
    public SentMessage(String newText, String newAuthor)
    {
        log.info("INSIDE ATTRIBUTE");
        text = newText;
        author = newAuthor;
    }
    public String text()
    {
        return text;
    }
    public String author()
    {
        return author;
    }
}