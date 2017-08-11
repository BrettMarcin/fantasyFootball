package com.home;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.persistence.*;
import javax.swing.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="Message")
@XmlRootElement public class MessagingServer
{
    @Id
    @Column(name="id_message")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;
    @Column
    @XmlElement private String text;
    @Column
    @XmlElement private String author;
    @Column
    @XmlElement private String recipient;
    @Column
    @XmlElement private int time;

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;

    public MessagingServer(String text, String author, String recipient, int time)
    {
        this.text = text;
        this.author = author;
        this.recipient = recipient;
        this.time = time;
    }
    public void startRunning()
    {
        try
        {
            server = new ServerSocket(6789, 100);
            while(true)
            {
                try
                {
                    waitForConnection();
                    setupStreams();
                    whileChatting();
                }
                catch(EOFException f)
                {
                    showMessage("Server ended connection");
                }
                finally
                {
                    closeStuff();
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    //wait for connection, display information after connected
    private void waitForConnection() throws IOException
    {
        showMessage("Waiting for second person to connect...");
        connection = server.accept();
        showMessage("Now connected to " + connection.getInetAddress().getHostName());

    }

    //get stream to send and receive data
    private void setupStreams() throws IOException
    {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        showMessage("Streams are now set up");
    }

    //during the chat conversation
    private void whileChatting() throws IOException
    {
        String message = "You are now connected!";
        sendMessage(message);
        do
        {
            try
            {
                message = (String)input.readObject();
                //TODO add to database and display all messages
                showMessage(message);
            }
            catch(ClassNotFoundException e)
            {
                showMessage("Can't understand what user sent.");
            }
        }
        while(!message.equals("CLIENT - END"));
    }
    //close streams and sockets after you are done chatting
    private void closeStuff()
    {
        showMessage("Closing connections...");
        try
        {
            output.close();
            input.close();
            connection.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    //send a message to the client
    private void sendMessage(String message)
    {
        try
        {   //TODO change to be dynamic user or not
            //TODO store sent message to database with author, time, destination, etc., display to user
            output.writeObject("USER: " + message);
            output.flush();
            showMessage("\nUSER - " + message);
        }
        catch(IOException e)
        {
            //convert to js
            chatWindow.append("ERROR: Can't send the message.");
        }
    }
}
