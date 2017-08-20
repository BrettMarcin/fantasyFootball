package com.home.team;


import com.home.Player;
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

	@Column
	@XmlElement public boolean isACpu;

	@JoinColumn(name="id_player_qb")
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@OneToOne
	@Cascade({CascadeType.SAVE_UPDATE})
	//@Cascade({CascadeType.ALL})
	@Fetch(FetchMode.JOIN)
	@XmlElement public Player QB = null;

	@JoinColumn(name="id_player_wr1")
	@OneToOne
	@Cascade({CascadeType.SAVE_UPDATE})
	@Fetch(FetchMode.JOIN)
	@XmlElement public Player WR1 = null;

	@JoinColumn(name="id_player_theW2")
	@OneToOne
	@Cascade({CascadeType.ALL})
	@Fetch(FetchMode.JOIN)
	@XmlElement public Player WR2 = null;

	@JoinColumn(name="id_player_rb1")
	@OneToOne
	@Cascade({CascadeType.SAVE_UPDATE})
	@Fetch(FetchMode.JOIN)
	@XmlElement public Player RB1 = null;

	@JoinColumn(name="id_player_rb2")
	@OneToOne
	@Cascade({CascadeType.SAVE_UPDATE})
	@Fetch(FetchMode.JOIN)
	@XmlElement public Player RB2 = null;

	@JoinColumn(name="id_player_te")
	@OneToOne
	@Cascade({CascadeType.SAVE_UPDATE})
	@Fetch(FetchMode.JOIN)
	@XmlElement public Player TE = null;

	@JoinColumn(name="id_player_flex")
	@OneToOne
	@Cascade({CascadeType.SAVE_UPDATE})
	@Fetch(FetchMode.JOIN)
	@XmlElement public Player FLEX = null;

	@JoinColumn(name="id_player_dst")
	@OneToOne
	@Cascade({CascadeType.SAVE_UPDATE})
	@Fetch(FetchMode.JOIN)
	@XmlElement public Player DST = null;

	@JoinTable(name="id_player")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@OneToMany
	@Cascade({CascadeType.ALL})
	@Fetch(FetchMode.JOIN)
	@XmlElement public List<Player> bench = null;
	
	public Team(String theTeamName, String theName, boolean isCpu){
		teamName = theTeamName;
		name = theName;
		bench = new ArrayList<>();
		isACpu = isCpu;
	}

	public Team(String theTeamName, boolean isCpu){
		teamName = theTeamName;
		bench = new ArrayList<>();
		isACpu = isCpu;
	}
	
	public Team(String theTeamName){
		teamName = theTeamName;
		bench = new ArrayList<>();
	}
	
	public Team(){
		super();
		bench = new ArrayList<>();
	}
	
	public void addPlayer(Player thePlayer){
		if (thePlayer.pos.equals("QB")){
			if (QB == null){
				QB = thePlayer;
			} else {
				bench.add(thePlayer);
			}
		} else if(thePlayer.pos.equals("WR")){
			if(WR1 == null){
				WR1 = thePlayer;
			} else if(WR2 == null){
				WR2 = thePlayer;
			} else if(FLEX == null){
				FLEX = thePlayer;
			} else {
				bench.add(thePlayer);
			}
		} else if(thePlayer.pos.equals("RB")){
			if(RB1 == null){
				RB1 = thePlayer;
			} else if(RB2 == null){
				RB2 = thePlayer;
			} else if(FLEX == null){
				FLEX = thePlayer;
			} else {
				bench.add(thePlayer);
			}
		} else if(thePlayer.pos.equals("TE")){
			if(TE == null){
				TE = thePlayer;
			}  else if(FLEX == null){
				FLEX = thePlayer;
			} else {
				bench.add(thePlayer);
			}
		} else if (thePlayer.pos.equals("DST")){
			DST = thePlayer;
		}
	}
}
