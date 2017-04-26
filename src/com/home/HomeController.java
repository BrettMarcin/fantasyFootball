package com.home;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;

@Controller
public class HomeController {
	
	private String theName = null;
	private static HashSet<Player> playersOpen = null;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome(Model model) throws IOException {
    	theData Data = new theData();
    	if(this.playersOpen == null){
    		this.playersOpen = Data.getRankingPlayers(); 
    	}
    	
    	ArrayList<Player> list = new ArrayList<Player>(this.playersOpen);
    	quick_sort sorting_object = new quick_sort(list);
    	sorting_object.quickSort(0, list.size() - 1);
    	list = sorting_object.theList;
    	
    	model.addAttribute("listOfPlayers", list);
    	model.addAttribute("name", this.theName);
    	return "home"; 
    }
	
	@RequestMapping(value = "/changeName", method = RequestMethod.POST)
	public void changeName(HttpServletRequest request, HttpServletResponse response) throws IOException{
		this.theName = request.getParameter("playerName");
		response.sendRedirect("/FantasyFootball/");
    }
	
	public void setHash(Player p1){
		this.playersOpen = new HashSet<Player>();
		this.playersOpen.add(p1);
	}
	
	public static HashSet<Player> getHash(){
		return playersOpen;
	}
	
}
