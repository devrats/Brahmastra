/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 10-Sep-21
 *   Time: 11:59 AM
 *   File: HomeController.java
 */

package com.example.brahmastra.controller;


import com.example.brahmastra.entity.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/project")
    public String project(){
        return "projects";
    }

    @RequestMapping("/pricing")
    public String pricing(){
        return "pricing";
    }

    @RequestMapping("/user/cart")
    public String cart(){
        return "cart";
    }

    @RequestMapping("/user/checkout")
    public String billing(){
        return "checkout";
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("client",new Client());
        model.addAttribute("userAvailable",false);
        model.addAttribute("showregister","false");
        return "login";
    }
}