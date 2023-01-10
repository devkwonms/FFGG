package com.project.soccer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/user/userSearch")
public class UserController {

    @GetMapping
    public String userSearch(){

        return "user/userSearch";
    }
    
}
