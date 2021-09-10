/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 10-Sep-21
 *   Time: 11:59 AM
 *   File: ServiceController.java
 */

package com.example.brahmastra.controller;

import com.example.brahmastra.entity.Client;
import com.example.brahmastra.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class ServiceController {

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("/user/home")
    public  String userHome(Model model, Principal principal){
        Client clientByUsername = clientRepository.findClientByUsername(principal.getName());
        if(!clientByUsername.isMail()){
            return "redirect:/user/email";
        } else {
            model.addAttribute("name",clientByUsername);
            return "home";
        }
    }
}