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
	List<Team> theTimeline;
	private final static Logger log = Logger.getLogger(HomeController.class.getName());
	private int round = -1;
	private int pickNumber = 1;
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
		response.addCookie(new Cookie("teamCookie", cookieValue));
		if (draftStarted){
			model.addAttribute("listOfPlayers", thePlayers);
			model.addAttribute("theTimeline", theTimeline);
			model.addAttribute("round", round);
			model.addAttribute("pickNumber", pickNumber);
			model.addAttribute("RB", localTeam.getRB());
			return "listOfPlayers";
		} else {
			return "home"; 
		}
    }
	
	@RequestMapping(value = "/setLocalTeam", method = RequestMethod.POST)
	public void changeName(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Team localTeam = new Team(request.getParameter("TeamNameInput"), request.getParameter("theName"));
		teamService.saveTeam(localTeam);
		Cookie newCookie = new Cookie("teamCookie", String.valueOf(localTeam.id));
		response.addCookie(newCookie);
		response.sendRedirect("/FantasyFootball/");
    }
	
	@RequestMapping(value = "/startDraft", method = RequestMethod.GET)
	@ResponseBody
	public void startDraft(HttpServletResponse response) throws IOException{
		draftStarted = true;
		getOrder();
		response.sendRedirect("/FantasyFootball/");
	}
	
	@RequestMapping(value = "/nextPick", method = RequestMethod.GET)
	@ResponseBody
	public void nextPick(@RequestBody Player thePlayer){
	    
	}
	
	
	@RequestMapping(value = "/draftPlayer", method = RequestMethod.POST)
	public void draftPlayer(@RequestBody Player json, @CookieValue(value = "teamCookie",defaultValue = "defaultCookieValue") String cookieValue) throws IOException {
		if(!cookieValue.equals("defaultCookieValue")){
			Team localTeam = teamService.getTeam(Integer.valueOf(cookieValue));
			localTeam.addPlayer(json);
			teamService.updateTeam(localTeam);
			for (Player thePlayer : thePlayers){
				if(thePlayer.isMatch(json)){
					thePlayers.remove(thePlayer);
					break;
				}
			}	
		}
    }
	
	@RequestMapping(value = "/getJson", method = RequestMethod.POST)
	@ResponseBody
	public void getJson(@RequestBody Player thePlayer){
	    System.out.println(thePlayer.first + " " + thePlayer.last);
	}
	
	private void getOrder(){
		if (round == -1){
			theTimeline = CreateOrder.order(teamService.getTeams());
			round = 1;
		}
	}
}
