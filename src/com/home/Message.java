package com.home;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Messages")
@XmlRootElement public class Message {

    @Id
    @Column(name="id_message")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;
    @JoinTable(name="id_message_contents")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @OneToMany
    @Cascade({CascadeType.ALL})
    @Fetch(FetchMode.JOIN)
    @XmlElement private List<MessageContents> messages = null;

    public Message()
    {
        super();
        messages = new ArrayList<>();
    }

    public void addMessage(MessageContents contents)
    {
        messages.add(contents);
    }

    public List<MessageContents> getMessages()
    {
        return messages;
    }
}
