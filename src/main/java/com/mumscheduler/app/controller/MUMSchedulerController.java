package com.mumscheduler.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MUMSchedulerController {
	
	@RequestMapping(value= {"/", "home"})
	public String home() {
		return "index";
	}
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/dashboard")
	public String dashboard() {
		return "dashboard";
	}
}
