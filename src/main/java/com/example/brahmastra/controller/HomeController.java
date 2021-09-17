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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProjectRepository projectRepository;

    @RequestMapping("/")
    public String home(Model model, Principal principal){
        Project project = new Project("Smart Parking","  •Very good\n  •Bht achha", 15000,"web",
                12999,10,15000,18);
        projectRepository.save(project);
        model.addAttribute("title","Brahmastra");
        model.addAttribute("loginAvailable",false);
        if(!(principal==null)){
            return "redirect:/user/home";
        }
        return "home";
    }

    @RequestMapping("/project")
    public String project(Model model, Principal principal){
        model.addAttribute("title","Projects");
        List<Project> all = projectRepository.findAll();
        model.addAttribute("projects",all);
        model.addAttribute("loginAvailable",false);
        if(!(principal==null)){
            return "redirect:/user/project";
        }
        return "projects";
    }

    @RequestMapping("/pricing")
    public String pricing(Model model, Principal principal){
        model.addAttribute("title","Pricing");
        model.addAttribute("loginAvailable",false);
        if(!(principal==null)){
            return "redirect:/user/pricing";
        }
        return "pricing";
    }

    @RequestMapping("/user/project")
    public String userProject(Model model){
        model.addAttribute("title","Projects");
        List<Project> all = projectRepository.findAll();
        model.addAttribute("projects",all);
        model.addAttribute("loginAvailable",true);
        return "projects";
    }

    @RequestMapping("/user/pricing")
    public String userPricing(Model model){
        model.addAttribute("title","Pricing");
        model.addAttribute("loginAvailable",true);
        return "pricing";
    }

    @RequestMapping("/user/finalBill/{id}")
    public String finalBill(@PathVariable("id") int id, Model model){
        Project projectById = projectRepository.findProjectById(id);
        model.addAttribute("project",projectById);
        model.addAttribute("title","Bill");
        model.addAttribute("loginAvailable",true);
        return "bill";
    }

    @RequestMapping("/user/cart")
    public String cart(Model model){
        model.addAttribute("title","Cart");
        model.addAttribute("loginAvailable",true);
        return "cart";
    }

    @RequestMapping("/user/checkout/{id}")
    public String billing(@PathVariable("id") int id, Model model){
        model.addAttribute("id",id);
        model.addAttribute("title","Billing");
        model.addAttribute("loginAvailable",true);
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