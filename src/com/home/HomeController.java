package com.home;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;

@Controller
public class HomeController {
	
	private String theName = null;
	private static HashSet<Player> playersOpen = null;
	private static HashSet<Player> playerInfo = null;
	private final static Logger log = Logger.getLogger(HomeController.class.getName());
	
	@javax.annotation.PostConstruct
	public void init() {
	   
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome(Model model) throws IOException {
		System.out.println("here");
    	theData Data = new theData();
    	if(this.playersOpen == null){
    		this.playersOpen = Data.getRankingPlayers(); 
    	}
    	if(this.playerInfo == null){
    		this.playerInfo = Data.getPlayerInfo();
    	}
    	ArrayList<Player> list = new ArrayList<Player>(this.playersOpen);
    	ArrayList<Player> info = new ArrayList<Player>(this.playerInfo);
    	quick_sort sorting_object = new quick_sort(list);
    	sorting_object.quickSort(0, list.size() - 1);
    	list = sorting_object.theList;
    	for(Player i : info){
    		
    	}
    	
    	model.addAttribute("listOfPlayers", list);
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
}
