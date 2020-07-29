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

    @GetMapping("/login")
    public String login() {
    	return "/login";
    }

}
