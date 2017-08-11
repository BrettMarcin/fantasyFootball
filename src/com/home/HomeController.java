package com.home;
import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class HomeController {
	
	private static ArrayList<Player> thePlayers = null;
	private boolean draftStarted = false;
	private List<Team> theTimeline;
	private final static Logger log = Logger.getLogger(HomeController.class.getName());
	private int round = -1;
	private int pickNumber = 1;
    private Timer timer;
    private long startTime;
	@Autowired
	private TeamService teamService;
	private MessageService messageService;
	
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
		response.sendRedirect("/");
    }
	
	@RequestMapping(value = "/startDraft", method = RequestMethod.GET)
	@ResponseBody
	public void startDraft(HttpServletResponse response) throws IOException{
		draftStarted = true;
		getOrder();
		System.out.println();
        setTimer(120);
        startTime = System.currentTimeMillis();
		response.sendRedirect("/");
	}

    @RequestMapping(value = "/getPlayers", method = RequestMethod.GET)
    @ResponseBody
    public  ArrayList<Player> getPlayers(){
            return thePlayers;
    }

    @RequestMapping(value = "/getTeam/{teamName}", method = RequestMethod.GET)
    @ResponseBody
    public Team getTeam(@PathVariable("teamName") String teamName){
        Team theTeam = teamService.getTeamByTeamName(teamName);
    	return theTeam;
    }

    @RequestMapping(value = "/getTime", method = RequestMethod.GET)
    @ResponseBody
    public long getTime(){
        return (System.currentTimeMillis()) - startTime;
    }
	
	@RequestMapping(value = "/draftPlayer", method = RequestMethod.POST)
	public void draftPlayer(@RequestBody Player json, @CookieValue(value = "teamCookie",defaultValue = "defaultCookieValue") String cookieValue, HttpServletResponse response) throws IOException {
        if(theTimeline.get(0).teamName.equals("Round"))
            theTimeline.remove(0);
        if(!cookieValue.equals("defaultCookieValue")){
			Team localTeam = teamService.getTeam(Integer.valueOf(cookieValue));
            if(theTimeline.get(0).teamName.equals(localTeam.teamName)){
                theTimeline.remove(0);
                localTeam.addPlayer(json);
                teamService.updateTeam(localTeam);
                for (Player thePlayer : thePlayers){
                    if(thePlayer.isMatch(json)){
                        thePlayers.remove(thePlayer);
                        break;
                    }
                }
                setTimer(120);
                startTime = System.currentTimeMillis();
            }
		}
        pickNumber++;
        response.sendRedirect("/");
    }

    @RequestMapping(value = "/getMessages", method = RequestMethod.GET)
    @ResponseBody
    public Message getMessages()
    {
        List<Message> messages = messageService.getMessages();
        for(Message m : messages)
        {
            return m;
        }
        return new Message();
    }

    public void setTimer(int seconds) {
        if (timer != null)
            timer.cancel();
        timer = new Timer();
        //Scheduling NextTask() call in 10 second.
        timer.schedule(new NextTask(), seconds * 1000);
    }

    private void autoDraftPlayer(){
        if(theTimeline.get(0).teamName.equals("Round"))
            theTimeline.remove(0);
        Team localTeam = teamService.getTeam(theTimeline.get(0).id);
        theTimeline.remove(0);
        localTeam.addPlayer(thePlayers.get(0));
        thePlayers.remove(0);
        teamService.updateTeam(localTeam);
        setTimer(120);
        startTime = System.currentTimeMillis();
    }
	
	private void getOrder(){
		if (round == -1){
			theTimeline = CreateOrder.order(teamService.getTeams());
			round = 1;
		}
	}

    class NextTask extends TimerTask {
        @Override
        public void run() {
            autoDraftPlayer();
            timer.cancel(); // Terminate the thread
        }
    }

}
