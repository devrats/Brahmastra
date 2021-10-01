/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 10-Sep-21
 *   Time: 11:59 AM
 *   File: HomeController.java
 */

package com.example.brahmastra.controller;


import com.example.brahmastra.entity.*;
import com.example.brahmastra.repository.AddressRepository;
import com.example.brahmastra.repository.ClientRepository;
import com.example.brahmastra.repository.PricingRepository;
import com.example.brahmastra.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PricingRepository pricingRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;

    @RequestMapping("/")
    public String home(Model model, Principal principal){
        Pricing project = new Pricing("Smart Parking","  •Very good\n  •Bht achha", 15000,"web",
                12999,10,15000,18);
        pricingRepository.save(project);
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
        List<Project> all = projectRepository.findAllByProjectType("project");
        model.addAttribute("projects",all);
        model.addAttribute("loginAvailable",false);
        if(!(principal==null)){
            return "redirect:/user/project";
        }
        return "projects";
    }

    @RequestMapping("/pricing")
    public String pricing(Model model, Principal principal){
        List<Pricing> all = pricingRepository.findAll();
        model.addAttribute("pricing",all);
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
        List<Project> all = projectRepository.findAllByProjectType("project");
        model.addAttribute("projects",all);
        model.addAttribute("loginAvailable",true);
        return "projects";
    }

    @RequestMapping("/user/pricing")
    public String userPricing(Model model){
        List<Pricing> all = pricingRepository.findAll();
        model.addAttribute("pricing",all);
        model.addAttribute("title","Pricing");
        model.addAttribute("loginAvailable",true);
        return "pricing";
    }

    @RequestMapping("/user/finalBill/{type}/{id}")
    public String finalBill( @ModelAttribute Client client, @PathVariable("type") String type, @PathVariable("id") int id,
                            Model model, Principal principal){
        model.addAttribute("title","Bill");
        model.addAttribute("loginAvailable",true);
        model.addAttribute("type",type);
        model.addAttribute("id",id);
        Client clientByUsername = clientRepository.findClientByUsername(principal.getName());
        Address address = client.getAddress();
        address.setClient(clientByUsername);
        clientByUsername.setName(client.getName());
        if(clientByUsername.getAddress()==null){
            clientByUsername.setAddress(address);
            addressRepository.save(address);
        }
        clientRepository.save(clientByUsername);
        if(type.equals("pricing")){
            Pricing pricingById = pricingRepository.findPricingById(id);
            model.addAttribute("project",pricingById);
            return "bill";
        } else{
            Project projectById = projectRepository.findProjectById(id);
            model.addAttribute("project",projectById);
            return "bill";
        }
    }

    @RequestMapping("/user/cart")
    public String cart(Model model,Principal principal){
        Client clientByUsername = clientRepository.findClientByUsername(principal.getName());
        List<ClientProject> projects = clientByUsername.getProjects();
        model.addAttribute("project",projects);
        model.addAttribute("title","Cart");
        model.addAttribute("loginAvailable",true);
        return "cart";
    }

    @RequestMapping("/user/checkout/{type}/{id}")
    public String billing(@PathVariable("type") String type, @PathVariable("id") int id, Model model,
                          Principal principal){
        model.addAttribute("id",id);
        model.addAttribute("type",type);
        model.addAttribute("title","Billing");
        model.addAttribute("loginAvailable",true);
        Client clientByUsername = clientRepository.findClientByUsername(principal.getName());
        model.addAttribute("client",clientByUsername);
        if(clientByUsername.getAddress()==null){
            model.addAttribute("address",new Address());
        }else{
            model.addAttribute("address",clientByUsername.getAddress());
        }
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