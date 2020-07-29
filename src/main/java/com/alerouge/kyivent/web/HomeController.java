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

//	@GetMapping(value ="/user")
//	public String userPage(){
//		return "user";
//	}

//	@GetMapping(value ="/admin")
//	public String adminPage(){
//		return "admin";
//	}

    @GetMapping("/login")
    public String login() {
    	// se utente gia loggato -> page not found
    	if (utenteLoggato()){
	        return "error/page404";
		} else {
	        return "/login";
		}
    }

	@GetMapping("/page403")
    public String error403() {
        return "/error/page403";
    }

}
