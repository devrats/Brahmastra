/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 10-Sep-21
 *   Time: 12:20 PM
 *   File: UserController.java
 */

package com.example.brahmastra.controller;

import com.example.brahmastra.entity.Client;
import com.example.brahmastra.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("/register")
    public String register(@ModelAttribute Client client){
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        client.setRole("ROLE_USER");
        client.setMail(false);
        clientRepository.save(client);
        return "login";
    }
}