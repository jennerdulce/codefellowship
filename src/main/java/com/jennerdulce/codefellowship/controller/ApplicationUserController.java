package com.jennerdulce.codefellowship.controller;

import com.jennerdulce.codefellowship.model.ApplicationUser;
import com.jennerdulce.codefellowship.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ApplicationUserController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String getHomePage(Principal p, Model m) {
        if(p != null){
            ApplicationUser personalAccount = applicationUserRepository.findByUsername(p.getName());
            m.addAttribute("personalUsername", personalAccount.getUsername());
            m.addAttribute("personalProfileImage", personalAccount.getProfileImage());
        }
        ApplicationUser otherAccount = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("username", otherAccount.getUsername());
        m.addAttribute("firstname", otherAccount.getFirstName());
        m.addAttribute("lastname", otherAccount.getLastName());
        m.addAttribute("bio", otherAccount.getBio());
        m.addAttribute("profileimage", otherAccount.getProfileImage());
        m.addAttribute("posts", otherAccount.getUserPosts());
        return "/userprofile";
    }

    @GetMapping("/login")
    public String getLoginPage() {return "login.html";}

    @GetMapping("/signup")
    public String signUp(String username, String password) {return "signup.html";}

    @GetMapping("/user/{username}")
    public String userProfile(Model m, Principal p, @PathVariable String username){
        if(p != null){
            ApplicationUser personalAccount = applicationUserRepository.findByUsername(p.getName());
            m.addAttribute("personalUsername", personalAccount.getUsername());
            m.addAttribute("personalProfileImage", personalAccount.getProfileImage());
        }
        ApplicationUser otherAccount = applicationUserRepository.findByUsername(username);
        m.addAttribute("username", otherAccount.getUsername());
        m.addAttribute("firstname", otherAccount.getFirstName());
        m.addAttribute("lastname", otherAccount.getLastName());
        m.addAttribute("bio", otherAccount.getBio());
        m.addAttribute("profileimage", otherAccount.getProfileImage());
        m.addAttribute("posts", otherAccount.getUserPosts());
        return "/userprofile";
    }

    @PostMapping("/signup")
    public RedirectView createUser(String username, String firstName, String lastName,  String bio, String password){
        String hashedPassword = passwordEncoder.encode(password);
        ApplicationUser newUser = new ApplicationUser(username, firstName, lastName, bio, hashedPassword);
        applicationUserRepository.save(newUser);
        return new RedirectView("/login");
    }


}
