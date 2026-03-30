package org.example.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainHomeController {

    @GetMapping("/")
    public String mainHomePage() {
        return "main-home";
    }
}