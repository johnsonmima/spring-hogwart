package com.johnsonmima.school.controller;

import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class HomeController {

    @RequestMapping(value = {"","/","/home"})
    public String displayHomePage(){
        return "home.html";
    }

    // Query Params example
//    @RequestMapping("/all")
//    public ModelAndView displayAllCourses(@RequestParam(required = false) String q){
//        String param = q != null ? q : "all";
//
//        log.info(param);
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("home.html");
//        return modelAndView;
//
//    }

// Path Param example
        @RequestMapping("/all/{display}")
    public ModelAndView displayAllCourses(@PathVariable String display){
        String param = display != null ? display : "all";

        log.info(param);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home.html");
        return modelAndView;

    }

}
