package edu.inconcept.netflix.controller;

import edu.inconcept.netflix.entity.SystemUser;
import edu.inconcept.netflix.service.security.SecurityService;
import edu.inconcept.netflix.service.security.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class SystemUserController {
    @Autowired
    SystemUserService systemUserService;

    @Autowired
    SecurityService securityService;

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public String registration(Model model){
         model.addAttribute("userForm", new SystemUser());
         return "registration";
    }



    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") SystemUser userForm, Model model){
        systemUserService.save(userForm);
        return "redirect:/welcome";
    }



    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model , String error ,String logout){
        if(error!=null)
            model.addAttribute("error","Your username or password is invalid");
        if(logout != null)
            model.addAttribute("message","You have been logget out successfully.");

        return "login";
    }



    @RequestMapping(value = {"/","welcome"},method = RequestMethod.GET)
    public String welcome(Model model){
        return "Welcome";
    }

}
