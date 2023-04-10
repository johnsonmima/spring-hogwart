package com.johnsonmima.school.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @RequestMapping("/dashboard")
    public String dashboardHome(Model model, Authentication authentication){
        // make sure 
        if(authentication != null) {
            model.addAttribute("username", authentication.getName());
            model.addAttribute("roles", authentication.getAuthorities().toString());
        }

        return "dashboard.html";
    }
}
