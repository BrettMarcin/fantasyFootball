package com.home;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement public class SentMessage
{
    @XmlElement private String text;
    @XmlElement private String author;

    public SentMessage(String text, String author)
    {
        this.text = text;
        this.author = author;
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