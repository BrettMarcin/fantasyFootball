package com.home;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement public class MessageContents
{
    @XmlElement private String text;
    @XmlElement private String author;
    @XmlElement private String time;

    public MessageContents(String text, String author, String time)
    {
        this.text = text;
        this.author = author;
        this.time = time;
    }
    public String text()
    {
        return text;
    }
    public String author()
    {
        return author;
    }
    public String time()
    {
        return time;
    }
}
