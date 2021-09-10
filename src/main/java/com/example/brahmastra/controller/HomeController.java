/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 10-Sep-21
 *   Time: 11:59 AM
 *   File: HomeController.java
 */

package com.example.brahmastra.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String login(){
        return "login";
    }
}