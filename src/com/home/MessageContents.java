package com.home;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement public class MessageContents
{
    private int id;

    @XmlElement private String text;
    @XmlElement private String author;
    @XmlElement private String time;

    public MessageContents(String text, String author, String time)
    {
        this.text = text;
        this.author = author;
        this.time = time;
    }
    public String getText()
    {
        return text;
    }
    public String getAuthor()
    {
        return author;
    }
    public String getTime()
    {
        return time;
    }
}
