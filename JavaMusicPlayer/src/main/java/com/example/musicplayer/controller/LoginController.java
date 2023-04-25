package com.example.musicplayer.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.example.musicplayer.helper.SongUploadHelper;
import com.example.musicplayer.model.Login;
import com.example.musicplayer.repository.LoginRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.musicplayer.model.Login;
import com.example.musicplayer.service.LoginService;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class LoginController {
    @Autowired
    private LoginService Service;

    @GetMapping("/register")
    public String add(Model model) {
        model.addAttribute("Login", new Login());
        return "register";
    }

    @RequestMapping(value = "/save-reg", method = RequestMethod.POST)
    public String saveSong(@ModelAttribute("Login") Login lng) {
        Service.save(lng);
        return "redirect:/";
    }

    /*
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password , @ModelAttribute("Login") Login lng) {
        // Check if username exists in database
        Service.findByUsername(username);
        if (lng.isPresent() && lng.get().getPassword().equals(password)) {
            return "redirect:/index";
        } else {
            return "/";
        }
    }
*/
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // Check if username exists in database
        Optional<Login> login = Service.findByUsername(username);
        if (login.isPresent() && login.get().getPassword().equals(password)) {
            return "redirect:/index";
        } else {
            return "login";
        }
    }


    @GetMapping("/")
    public String ViewFirstPage() {
        return "login";
    }

    @GetMapping("/about")
    public String ViewInfoPage() {
        return "about";
    }

    @GetMapping("/contact")
    public String ViewContactPage() {
        return "contact";
    }

}
