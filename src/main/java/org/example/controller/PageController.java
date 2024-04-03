package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/index")
    public String homePage() {
        return "index";
    }

//    @GetMapping("/index")
//    public String homePage() {
//        return "index";
//    }
//
//    @GetMapping("/index")
//    public String homePage() {
//        return "index";
//    }
}
