package com.home;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

@Controller
public class HomeController {
	
	private String theName = null;
	private static ArrayList<Player> thePlayers = null;
	private final static Logger log = Logger.getLogger(HomeController.class.getName());
	@Autowired
	private TeamService teamService;
	
	@javax.annotation.PostConstruct
	public void init() throws IOException {
		thePlayers = theData.getPlayers();
		thePlayers = quick_sort.sort(thePlayers, 0, thePlayers.size()-1);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome(Model model) throws IOException {
		teamService.saveTeam(new Team("Bob's team"));
		List<Team> theTeams = teamService.getTeams();
		System.out.println(theTeams.get(0).teamName);
    	model.addAttribute("listOfPlayers", thePlayers);
    	model.addAttribute("name", this.theName);
    	return "home"; 
    }
	
	@RequestMapping(value = "/changeName", method = RequestMethod.POST)
	public void changeName(HttpServletRequest request, HttpServletResponse response) throws IOException{
		this.theName = request.getParameter("playerName");
		response.sendRedirect("/FantasyFootball/");
    }
	
	@RequestMapping(value = "/draftPlayer", method = RequestMethod.POST)
	public @ResponseBody Player draftPlayer(@RequestBody Player json) {
		/*
		Player newPlayer = new Player();
		
		jsonString = jsonString.substring(0, jsonString.length()-1);
		String parts[] = jsonString.split("\\+");
		newPlayer.first = parts[0];
		newPlayer.last = parts[1];
		newPlayer.rank = Integer.parseInt(parts[2]);
		if(this.playersOpen.contains(newPlayer)){
			System.out.println("we have it");
		}
		
		this.playersOpen.remove(newPlayer);
		*/
		return null;
    }
	
	@RequestMapping(value = "/saveTeam", method = RequestMethod.POST)
	public void saveTeam(HttpServletRequest request, HttpServletResponse response){
		
	}
}
