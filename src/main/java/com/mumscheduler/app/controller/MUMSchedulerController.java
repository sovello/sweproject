package com.mumscheduler.app.controller;


import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MUMSchedulerController {
	
	/**
	 * if is a returning logged in user, take them to the dashboard page
	 * @param principal
	 * @return
	 */
	@RequestMapping(value= {"/", "home"})
	public String home(Principal principal) {
        return principal == null ? "index" : "redirect:/dashboard";
	}
	
	@RequestMapping(value="/login")
	public String login(Model model) {
		model.addAttribute("activeTab", "login");
		return "login";
	}
	
	@RequestMapping(value="/dashboard")
	public String dashboard(Model model) {
		model.addAttribute("activeTab", "dashboard");
		return "dashboard";
	}
	
	@RequestMapping(value="/403")
    public String Error403(){
        return "403";
    }
	
	@RequestMapping(value="/404")
    public String Error404(){
        return "404";
    }
	
	@RequestMapping(value="/500")
    public String Error500(){
        return "500";
    }
}
