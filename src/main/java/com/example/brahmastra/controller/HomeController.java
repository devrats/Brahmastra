/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 10-Sep-21
 *   Time: 11:59 AM
 *   File: HomeController.java
 */

package com.example.brahmastra.controller;


import com.example.brahmastra.entity.Client;
import com.example.brahmastra.entity.Project;
import com.example.brahmastra.repository.ProjectRepository;
import org.hibernate.criterion.ProjectionList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProjectRepository projectRepository;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("title","Brahmastra");
        return "home";
    }

    @RequestMapping("/project")
    public String project(Model model){
        model.addAttribute("title","Projects");
        List<Project> all = projectRepository.findAll();
        model.addAttribute("projects",all);
        return "projects";
    }

    @RequestMapping("/pricing")
    public String pricing(Model model){
        model.addAttribute("title","Pricing");
        return "pricing";
    }

    @RequestMapping("/user/cart")
    public String cart(Model model){
        model.addAttribute("title","Cart");
        return "cart";
    }

    @RequestMapping("/user/checkout")
    public String billing(Model model){
        model.addAttribute("title","Billing");
        return "checkout";
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("client",new Client());
        model.addAttribute("userAvailable",false);
        model.addAttribute("showregister","false");
        model.addAttribute("title","Login");
        return "login";
    }
}