package com.johnsonmima.school.controller;

import com.johnsonmima.school.model.Contact;
import com.johnsonmima.school.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ContactController {
    public final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }


    @RequestMapping(value = {"/contact"})
    public ModelAndView displayContactPage(){
        return new ModelAndView("contact.html");
    }

    @RequestMapping(value = "/saveMsg", method = POST)
    public ModelAndView saveMessage(Contact contact){

        contactService.saveMessageDetails(contact);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/contact");
        return modelAndView;

    }

    // one approach
//    @RequestMapping(value = "/saveMsg", method = POST)
//    public ModelAndView saveMessage(@RequestParam String name,
//                                    @RequestParam String email,
//                                    @RequestParam String message,
//                                    @RequestParam String subject,
//                                    @RequestParam String phone ){
//        log.info("message: " + message);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:/contact");
//        return modelAndView;
//
//    }
}
