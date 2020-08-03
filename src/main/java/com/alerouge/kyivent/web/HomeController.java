package com.alerouge.kyivent.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

	@GetMapping(value ="/")
	public String homePage(@CookieValue(value = "cookiePage", defaultValue = "") String cookiePage, HttpServletResponse response){

		// cookie home page
	    Cookie cookie = new Cookie("cookiePage", "");
	    //add cookie to response
	    response.addCookie(cookie);

		
		return "home";
	}
	
	@GetMapping(value ="/firstPage")
	public String firstPage(HttpServletResponse response){
		
		// cookie first page
	    Cookie cookie = new Cookie("cookiePage", "firstPage");
	    //add cookie to response
	    response.addCookie(cookie);
		
		return "firstPage";
	}
	
	@GetMapping(value ="/secondPage")
	public String secondPage(HttpServletResponse response){

		// cookie second page
	    Cookie cookie = new Cookie("cookiePage", "secondPage");
	    //add cookie to response
	    response.addCookie(cookie);

		return "secondPage";
	}


    @GetMapping("/login")
    public String login() {
    	// se utente gia loggato -> page not found
    	if (utenteLoggato()){
	        return "error/page404";
		} else {
	        return "/login";
		}
    }



}
