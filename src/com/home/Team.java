package com.home;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.FetchMode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="Team")
@XmlRootElement public class Team{

	@Id
	@Column(name="id_team")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	@Column
	@XmlElement public String name;
	@Column
	@XmlElement public String teamName;

	@JoinColumn(name="id_player_qb")
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@OneToOne
	@Cascade({CascadeType.SAVE_UPDATE})
	//@Cascade({CascadeType.ALL})
	@Fetch(FetchMode.JOIN)
	@XmlElement public Player QB = null;

	@JoinColumn(name="id_player_wr")
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@OneToOne
	@Cascade({CascadeType.SAVE_UPDATE})
	//@Cascade({CascadeType.ALL})
	@Fetch(FetchMode.JOIN)
	@XmlElement public Player WR = null;


	@JoinColumn(name="id_player_rb")
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@OneToOne
	@Cascade({CascadeType.SAVE_UPDATE})
	//@Cascade({CascadeType.ALL})
	@Fetch(FetchMode.JOIN)
	@XmlElement public Player RB = null;

	@JoinColumn(name="id_player_te")
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@OneToOne
	//@Cascade({CascadeType.ALL})
	@Cascade({CascadeType.SAVE_UPDATE})
	@Fetch(FetchMode.JOIN)
	@XmlElement public Player TE = null;

	@JoinColumn(name="id_player_dst")
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@OneToOne
	//@Cascade({CascadeType.ALL})
	@Cascade({CascadeType.SAVE_UPDATE})
	@Fetch(FetchMode.JOIN)
	@XmlElement public Player DST = null;
	
	public Team(String theTeamName, String theName){
		teamName = theTeamName;
		name = theName;
	}
	
	public Team(String theTeamName){
		teamName = theTeamName;
	}
	
	public Team(){
		super();
	}
	
	public void addPlayer(Player thePlayer){
		if (thePlayer.pos.equals("QB")){
			QB = thePlayer;
		} else if(thePlayer.pos.equals("WR")){
			WR = thePlayer;
		} else if(thePlayer.pos.equals("RB")){
			RB = thePlayer;
		} else if(thePlayer.pos.equals("TE")){
			TE = thePlayer;
		} else if (thePlayer.pos.equals("DST")){
			DST = thePlayer;
		}
	}
}
