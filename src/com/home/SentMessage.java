package com.home;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement public class SentMessage
{

    @XmlElement private String author;
    @XmlElement private String text;

    public SentMessage()
    {
        super();
    }
    public SentMessage(String newText, String newAuthor)
    {
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