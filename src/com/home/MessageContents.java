package com.home;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name="Message")
@XmlRootElement public class MessageContents
{
    @Id
    @Column(name="id_message")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column
    @XmlElement private String text;
    @Column
    @XmlElement private String author;
    @Column
    @XmlElement private String time;

    public MessageContents(String newText, String newAuthor, String newTime)
    {
        text = newText;
        author = newAuthor;
        time = newTime;
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
