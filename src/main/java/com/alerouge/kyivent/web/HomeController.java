package com.alerouge.kyivent.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

	@GetMapping(value ="/")
	public String homePage(){
		return "home";
	}
	
	@GetMapping(value ="/firstPage")
	public String firstPage(){
		return "firstPage";
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
