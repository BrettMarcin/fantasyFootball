package com.home;

import sun.plugin2.message.Message;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name="Messages")
@XmlRootElement
public class Messages
{
    @Id
    @Column(name="id_message")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;

    @XmlElement
    @Column
    private String text;
    @XmlElement
    @Column
    private String author;
    @XmlElement
    @Column
    private String time;

    public Messages(){
        super();
    }

    public Messages(String text, String author, String time)
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
