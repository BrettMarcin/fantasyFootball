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
	private HashSet<Player> playserOpen = new HashSet<Player>();
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome(Model model) throws IOException {
    	theData Data = new theData();
    	model.addAttribute("content", Data.theWord);
    	model.addAttribute("name", this.theName);
    	return "home"; 
    }
	
	@RequestMapping(value = "/changeName", method = RequestMethod.POST)
	public void changeName(HttpServletRequest request, HttpServletResponse response) throws IOException{
		this.theName = request.getParameter("playerName");
		response.sendRedirect("/FantasyFootball/");
    }
	
}
