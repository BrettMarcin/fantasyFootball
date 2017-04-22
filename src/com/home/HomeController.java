package com.home;
import java.io.IOException;

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

@Controller
public class HomeController {
	
	private String theName;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome(Model model) throws IOException {
    	theData Data = new theData();
    	model.addAttribute("content", Data.theWord);
    	model.addAttribute("name", this.theName);
    	return "home"; 
    }
	
	@RequestMapping(value = "/changeName", method = RequestMethod.POST)
	public void changeName(@ModelAttribute String theName, HttpServletResponse response) throws IOException{
		this.theName = theName;
		response.sendRedirect("/");
    }
	
}
