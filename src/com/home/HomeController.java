package com.home;
import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.*;

@Controller
public class HomeController {
	
	private static ArrayList<Player> thePlayers = null;
	private boolean draftStarted = false;
	private final static Logger log = Logger.getLogger(HomeController.class.getName());
	@Autowired
	private TeamService teamService;
	
	@javax.annotation.PostConstruct
	public void init() throws IOException {
		thePlayers = theData.getPlayers();
		thePlayers = quick_sort.sort(thePlayers, 0, thePlayers.size()-1);
		List<Team> theTeams = teamService.getTeams();
		if (theTeams.size() > 0)
			teamService.clearTeams(theTeams);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome(@CookieValue(value = "teamCookie",defaultValue = "defaultCookieValue") String cookieValue, Model model, HttpServletResponse response) throws IOException {
		Team localTeam = null;
		if (!cookieValue.equals("defaultCookieValue")){
			localTeam = teamService.getTeam(Integer.valueOf(cookieValue));
		}
		model.addAttribute("localTeam", localTeam);
		model.addAttribute("theTeams", teamService.getTeams());
		if (draftStarted){
			model.addAttribute("listOfPlayers", thePlayers);
		} else {
			
		}
		response.addCookie(new Cookie("teamCookie", cookieValue));
    	return "home"; 
    }
	
	@RequestMapping(value = "/setLocalTeam", method = RequestMethod.POST)
	public void changeName(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Team localTeam = new Team(request.getParameter("TeamNameInput"), request.getParameter("theName"));
		teamService.saveTeam(localTeam);
		Cookie newCookie = new Cookie("teamCookie", String.valueOf(localTeam.id));
		response.addCookie(newCookie);
		response.sendRedirect("/FantasyFootball/");
    }
	
	@RequestMapping(value = "/getJson", method = RequestMethod.POST)
	@ResponseBody
	public void getJson(@RequestBody Player thePlayer){
	    System.out.println(thePlayer.first + " " + thePlayer.last);
	}
	
	@RequestMapping(value = "/draftPlayer", method = RequestMethod.POST)
	public @ResponseBody Player draftPlayer(@RequestBody Player json) {
		return null;
    }
}
