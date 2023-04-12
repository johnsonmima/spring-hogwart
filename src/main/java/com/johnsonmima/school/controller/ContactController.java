package com.johnsonmima.school.controller;

import com.johnsonmima.school.model.Contact;
import com.johnsonmima.school.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Slf4j
public class ContactController {
    public final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }


    @RequestMapping(value = {"/contact"})
    public ModelAndView displayContactPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("contact", new Contact());
        modelAndView.setViewName("contact.html");
        return modelAndView;
    }

    @RequestMapping(value = "/saveMsg", method = POST)
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact,
                              Errors errors) {
        // make sure no error
        if (errors.hasErrors()) {
            return "contact.html";
        } else {
            contactService.saveMessageDetails(contact);
        }

        return "redirect:/contact";


    }

    // displayMessage
    @RequestMapping(value = "displayMessage")
    public String displayMessages(Model model) {
        List<Contact> contactMsgs = contactService.findMsgsWithOpenStatus();
        model.addAttribute("contactMsgs", contactMsgs);
        return "messages.html";
    }

    // close message
    @RequestMapping(value = "/closeMsg", method = RequestMethod.GET)
    public String closeMsg(Authentication authentication, @RequestParam(name =
            "id", required =
            true) int id) {
        contactService.updateMsgStatus(id, authentication.getName());
        return "redirect:/displayMessage";
    }

}
