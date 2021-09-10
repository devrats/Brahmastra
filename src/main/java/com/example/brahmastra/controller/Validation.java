/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 10-Sep-21
 *   Time: 7:06 PM
 *   File: Validation.java
 */

package com.example.brahmastra.controller;

import com.example.brahmastra.email.Email;
import com.example.brahmastra.email.EmailService;
import com.example.brahmastra.entity.Client;
import com.example.brahmastra.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class Validation {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EmailService emailService;

    Random rnd = new Random();
    String otp = null;

    @RequestMapping("/email")
    public String email(Model model, Principal principal) {
        Client client = clientRepository.findClientByUsername(principal.getName());
        model.addAttribute("email", client.getUsername());
        model.addAttribute("title", "email verification");
        model.addAttribute("wrongOtp", false);
        model.addAttribute("change", false);
        model.addAttribute("nonchange", true);
        int number = rnd.nextInt(999999);
        otp = String.format("%06d", number);
        Email email = new Email();
        emailService.sendEmail(client.getUsername(), email.getHead2(), email.getMsg1(otp), false);
        return "emailVerification";
    }

    @RequestMapping("/EmailVerification")
    public String emailVerification(Model model, Principal principal, @RequestParam("otp") String pass) {
        Client client = clientRepository.findClientByUsername(principal.getName());
        if (pass.equals(otp)) {
            client.setMail(true);
            clientRepository.save(client);
            return "redirect:/user/home";
        } else {
            model.addAttribute("email", client.getUsername());
            model.addAttribute("title", "email verification");
            model.addAttribute("change", false);
            model.addAttribute("wrongOtp", true);
            model.addAttribute("nonchange", true);
            return "emailVerification";
        }
    }

    @RequestMapping("/changeEmailDisplay")
    public String changeEmailDisplay(Model model, Principal principal) {
        Client client = clientRepository.findClientByUsername(principal.getName());
        model.addAttribute("wrongOtp", false);
        model.addAttribute("email", client.getUsername());
        model.addAttribute("title", "email verification");
        model.addAttribute("change", true);
        model.addAttribute("nonchange", false);
        return "emailVerification";
    }

    @RequestMapping("/changeEmail")
    public String changeEmail(Model model, Principal principal, @RequestParam("newEmail") String newEmail) {
        Client client = clientRepository.findClientByUsername(principal.getName());
        client.setUsername(newEmail);
        clientRepository.save(client);
        return "redirect:/user/email";
    }
}