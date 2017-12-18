package com.home;
import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(value = "/api")
public class HomeController {

    private HashMap<Integer, Integer> theAssociation;
    private HashSet<String> playersDrafted;
	private boolean draftStarted = false;
	private ArrayList<Team> theTimeline;
	private final static Logger log = Logger.getLogger(HomeController.class.getName());
	private int round = -1;
	private int pickNumber = 1;
    private Timer timer;
    private Timer cpuTimer;
    private long startTime;
    private Player lastPlayerDrafted;
    private ArrayList<Player> draftHistory;
    private ArrayList<Player> remainingPlayers;

	@Autowired
	private TeamService teamService;
	@Autowired
    private PlayerService playerService;
	private boolean endDraft = true;
	
	@javax.annotation.PostConstruct
	public void init() {
		List<Team> theTeams = teamService.getTeams();
        remainingPlayers = getDBPlayers();
        if (remainingPlayers.size() > 0)
            remainingPlayers = quick_sort.sort((ArrayList<Player>)remainingPlayers, 0, remainingPlayers.size()-1);
        theAssociation = new HashMap<>();
        playersDrafted = new HashSet<>();
        draftHistory = new ArrayList<>();
		if (theTeams.size() > 0)
			teamService.clearTeams(theTeams);
	}

    @RequestMapping(value = "/resetDraft", method = RequestMethod.GET)
    public void resetDraft(HttpServletResponse response) throws IOException {
	    endDraft = true;
        Cookie cookie = new Cookie("teamCookie", null);
        theAssociation = new HashMap<>();
        playersDrafted = new HashSet<>();
        draftHistory = new ArrayList<>();
        timer.purge();
        cpuTimer.purge();
        cookie.setMaxAge(0);
        init();
        lastPlayerDrafted = null;
        round = -1;
        pickNumber = 1;
        response.addCookie(cookie);
        response.sendRedirect("/");
    }

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String getHome(@CookieValue(value = "teamCookie",defaultValue = "defaultCookieValue") String cookieValue, Model model, HttpServletResponse response) throws IOException {
//		Team localTeam = null;
//        if (!cookieValue.equals("defaultCookieValue")){
//            if (theAssociation.containsKey(Integer.valueOf(cookieValue))){
//                int theHashCookie = theAssociation.get(Integer.valueOf(cookieValue));
//                localTeam = teamService.getTeam(theHashCookie);
//            } else {
//                cookieValue = "defaultCookieValue";
//            }
//        }
//        model.addAttribute("localTeam", localTeam);
//        model.addAttribute("theTeams", teamService.getTeams());
//		if (draftStarted){
//            model.addAttribute("listOfPlayers", remainingPlayers);
//            model.addAttribute("theTimeline", theTimeline);
//            model.addAttribute("round", round);
//            model.addAttribute("pickNumber", pickNumber);
//            response.addCookie(new Cookie("teamCookie", cookieValue));
//		    if (localTeam != null) {
//                return "listOfPlayers";
//            } else {
//                return "guestDraft";
//            }
//		} else if(endDraft == true){
//			return "home";
//		} else {
//		    return "end";
//        }
//    }

    @RequestMapping(value = "/setLocalTeam", method = RequestMethod.POST)
    @ResponseBody
	public Team setLocalTeam(@RequestBody Team json, HttpServletResponse response) throws IOException{
        List<Team> theTeams = teamService.getTeams();
	    if (theTeams.size() < 10) {
            Team localTeam = new Team(json.teamName, json.name, false);
            teamService.saveTeam(localTeam);
            int hashCookie = (int) (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) * Math.random());
            theAssociation.put(hashCookie, localTeam.id);
            Cookie newCookie = new Cookie("teamCookie", String.valueOf(hashCookie));
            response.addCookie(newCookie);
            response.setStatus(200);
            return json;
        } else {
	        response.setStatus(400);
	        return null;
        }
    }

    @RequestMapping(value = "/addACpu", method = RequestMethod.POST)
    public void addACpu(HttpServletRequest request, HttpServletResponse response) throws IOException{
        List<Team> theTeams = teamService.getTeams();
        if (theTeams.size() < 10) {
            Team localTeam = new Team(request.getParameter("CpuName"), "Cpu" ,true);
            teamService.saveTeam(localTeam);
            response.sendRedirect("/");
        }
    }

//    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
//    public void sendEmail(@CookieValue(value = "teamCookie",defaultValue = "defaultCookieValue") String cookieValue) throws IOException{
//        if (endDraft == true && draftStarted == false){
//            if (!cookieValue.equals("defaultCookieValue")) {
//                int theHashCookie = theAssociation.get(Integer.valueOf(cookieValue));
//                Team localTeam = teamService.getTeam(theHashCookie);
//            }
//        }
//    }
	
	@RequestMapping(value = "/startDraft", method = RequestMethod.GET)
	@ResponseBody
	public void startDraft(HttpServletResponse response) throws IOException{
	    if (draftStarted == false && teamService.getTeams().size() > 0){
            draftStarted = true;
            getOrder();
            System.out.println();
            setTimer(120);
            setCPUTimer(5);
            startTime = System.currentTimeMillis();
            response.sendRedirect("/");
        }
	}

    @RequestMapping(value = "/getTeam/{teamName}", method = RequestMethod.GET)
    @ResponseBody
    public Team getTeam(@PathVariable("teamName") String teamName){
        Team theTeam = teamService.getTeamByTeamName(teamName);
    	return theTeam;
    }

    @RequestMapping(value = "/getTeams", method = RequestMethod.GET)
    @ResponseBody
    public List<Team> getTeams(){
        return teamService.getTeams();
    }

    @RequestMapping(value = "/getTime", method = RequestMethod.GET)
    @ResponseBody
    public long getTime(){
        if (draftStarted == true){
            return (System.currentTimeMillis()) - startTime;
        } else {
            return -696969;
        }
    }

    @RequestMapping(value = "/checkIfDraftHasStarted", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkIfDraftHasStarted(){
        return draftStarted;
    }

    @RequestMapping(value = "/getTimeline", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Team> getTimeline(){
        return theTimeline;
    }

    @RequestMapping(value = "/getPick", method = RequestMethod.GET)
    @ResponseBody
    public int getPick(){
        return pickNumber;
    }

    @RequestMapping(value = "/isDraftStillGoing", method = RequestMethod.GET)
    @ResponseBody
    public boolean isDraftStillGoing(){
        return draftStarted;
    }

    @RequestMapping(value = "/getRound", method = RequestMethod.GET)
    @ResponseBody
    public int getRound(){
        return round;
    }

    @RequestMapping(value = "/getLastPlayerDrafted", method = RequestMethod.GET)
    @ResponseBody
    public Player getLastPlayerDrafted(){
        return lastPlayerDrafted;
    }

    @RequestMapping(value = "/getDraftHistory", method = RequestMethod.GET)
    @ResponseBody
    public List<Player> getDraftHistory(){
        return draftHistory;
    }

    @RequestMapping(value = "/theEndDraft", method = RequestMethod.GET)
    @ResponseBody
    public boolean theEndDraft(){
        return endDraft;
    }

    @RequestMapping(value = "/updatePlayers", method = RequestMethod.GET)
    public void updatePlayers(HttpServletResponse response) throws IOException{
        try {
            ArrayList<Player> thePlayers = theData.getPlayers();
            thePlayers = quick_sort.sort(thePlayers, 0, thePlayers.size() - 1);
            playerService.clearPlayers(getDBPlayers());
            for (Player p : thePlayers) {
                playerService.savePlayer(p);
            }
            remainingPlayers = getDBPlayers();
        }
        catch (java.io.IOException e){

        }
        response.sendRedirect("/");
    }

    @RequestMapping(value = "/getPlayers", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Player> getRemainingPlayers(){
        return remainingPlayers;
    }

    public ArrayList<Player> getDBPlayers(){
        ArrayList<Player> theDBPlayers = (ArrayList<Player>)playerService.getDBPlayers();
        return theDBPlayers;
    }

	@RequestMapping(value = "/draftPlayer", method = RequestMethod.POST)
	public void draftPlayer(@RequestBody Player json, @CookieValue(value = "teamCookie",defaultValue = "defaultCookieValue") String cookieValue, HttpServletResponse response) throws IOException {
        if (draftStarted == true && theTimeline.size() != 0) {
            if (theTimeline.get(0).teamName.equals("Round")) {
                theTimeline.remove(0);
                round++;
            }
            if(!theTimeline.get(0).isACpu) {
                if (!cookieValue.equals("defaultCookieValue")) {
                    int theHashCookie = theAssociation.get(Integer.valueOf(cookieValue));
                    Team localTeam = teamService.getTeam(theHashCookie);
                    List<Player> thePlayerList = getDBPlayers();
                    if (theTimeline.get(0).teamName.equals(localTeam.teamName) && !playersDrafted.contains(json)) {
                        theTimeline.remove(0);
                        playersDrafted.add(json.first + json.last + json.pos + json.team);
                        for (Player thePlayer : remainingPlayers) {
                            Player current = thePlayer.isMatch(json);
                            if (current != null) {
                                localTeam.addPlayer(current);
                                current.updateTeamOwner(localTeam.teamName);
                                teamService.updateTeam(localTeam);
                                break;
                            }
                        }
                        for (Player thePlayer : remainingPlayers) {
                            if (thePlayer.isMatch(json) != null) {
                                remainingPlayers.remove(thePlayer);
                                break;
                            }
                        }
                        lastPlayerDrafted = json;
                        lastPlayerDrafted.teamOwner = localTeam.teamName;
                        draftHistory.add(lastPlayerDrafted);
                        startTime = System.currentTimeMillis();
                        pickNumber++;
                    }
                }
            }

            if (theTimeline.size() == 0){
                endDraft = false;
                draftStarted = false;
            } else if (!theTimeline.get(0).isACpu){
                setTimer(120);
            } else {
                setCPUTimer(5);
            }

        }
        response.sendRedirect("/");
    }

    public void setTimer(int seconds) {
        if (timer == null){
            timer = new Timer();
        } else {
            timer.purge();
        }
        timer.schedule(new NextTask(), seconds * 1000);
    }

    public void setCPUTimer(int seconds) {
        if (cpuTimer == null){
            cpuTimer = new Timer();
        } else {
            cpuTimer.purge();
        }
        cpuTimer.schedule(new checkComputer(), seconds * 1000);
    }

    private void autoDraftPlayer(){
        if (theTimeline.size() != 0) {
            if (theTimeline.get(0).teamName.equals("Round")) {
                theTimeline.remove(0);
                round++;
            }
            Team localTeam = teamService.getTeam(theTimeline.get(0).id);
            theTimeline.remove(0);
            localTeam.addPlayer(remainingPlayers.get(0));
            lastPlayerDrafted = remainingPlayers.get(0);
            lastPlayerDrafted.teamOwner = localTeam.teamName;
            draftHistory.add(lastPlayerDrafted);
            playersDrafted.add(remainingPlayers.get(0).first + remainingPlayers.get(0).last + remainingPlayers.get(0).pos + remainingPlayers.get(0).team);
            remainingPlayers.remove(0);
            teamService.updateTeam(localTeam);
            setTimer(120);
            setCPUTimer(5);
            pickNumber++;
            startTime = System.currentTimeMillis();
        }
        if (theTimeline.size() == 0) {
            endDraft = false;
            draftStarted = false;
        }
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
        }
    }

    class checkComputer extends TimerTask {
        @Override
        public void run() {
            if (theTimeline.size() > 0){
                if(theTimeline.get(0).teamName.equals("Round")){
                    theTimeline.remove(0);
                    round++;
                }
                if(theTimeline.get(0).isACpu){
                    autoDraftPlayer();
                } else {
                    cpuTimer.purge();
                }
            }
        }
    }
}
