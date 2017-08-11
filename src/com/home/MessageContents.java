package com.home;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="Message")
@XmlRootElement public class MessageContents
{
    @Id
    @Column(name="id_contents")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column
    @XmlElement private String text;
    @Column
    @XmlElement private String author;
    @Column
    @XmlElement private String date;

    public MessageContents(String text, String author, String date)
    {
        this.text = text;
        this.author = author;
        this.date = date;
    }
}
