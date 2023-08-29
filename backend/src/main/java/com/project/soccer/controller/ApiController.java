package com.project.soccer.controller;

import com.project.soccer.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiController {

    @RequestMapping("/api/user")
    public User getUser(){
        return new User("user@email.com","user name");
    }
}
