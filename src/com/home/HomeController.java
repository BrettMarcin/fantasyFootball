package com.home;
import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

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
	@Autowired
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
	    log.info("DRAFT STARTED");
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

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public void send(SentMessage theMessage){
        log.info("message test: " + theMessage.author());
        String json = null;
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            json = ow.writeValueAsString(theMessage);
        } catch (JsonProcessingException e) {
            log.severe(String.valueOf(e));
        }
        log.info("THE MESSAGE: " + json);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        MessageContents message = new MessageContents(theMessage.text(), theMessage.author(), time);
        try {
            json = ow.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            log.severe(String.valueOf(e));
        }
        log.info("inside add: " + json);
        messageService.addMessage(message);
    }

    @RequestMapping(value = "/getMessages", method = RequestMethod.GET)
    @ResponseBody
    public List<MessageContents> getMessages()
    {
        log.info("inside getMessages");
        return messageService.getMessages();
    }

    public void setTimer(int seconds) {
        if (timer != null)
            timer.cancel();
        timer = new Timer();
        //Scheduling NextTask() call in 10 second.
        timer.schedule(new NextTask(),  seconds * 1000);
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
