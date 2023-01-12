package com.project.soccer.controller;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Slf4j
@Controller
@RequestMapping("/user/userSearch")
public class UserController {

    @GetMapping
    public String user(){

        return "user/userSearch";
    }
    @ResponseBody
    @PostMapping
    public String userSearch(@RequestBody String userName) throws JSONException {
        StringBuffer result = new StringBuffer();
        System.setProperty("https.protocols", "TLSv1.2");

        try {
            String apiUrl = "https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname="+ URLEncoder.encode(userName,"UTF-8");

            log.info("userName = {}", userName);
            log.info("apiUrl = {}", apiUrl);
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiNTAwOjEwIiwiYWNjb3VudF9pZCI6IjE1MjY5MjMzNzAiLCJhdXRoX2lkIjoiMiIsImV4cCI6MTY4ODg4ODQyMSwiaWF0IjoxNjczMzM2NDIxLCJuYmYiOjE2NzMzMzY0MjEsInNlcnZpY2VfaWQiOiI0MzAwMTE0ODEiLCJ0b2tlbl90eXBlIjoiQWNjZXNzVG9rZW4ifQ.SqVNR_woA6kakkt-DtclOu0DP5tJNwgd5q1DDqTde_Q");
            urlConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            String returnLine;
        while((returnLine = bufferedReader.readLine()) != null) {
            result.append(returnLine);
//            log.info("result ={}", result);
        }
//            log.info("result ={}", result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("result = {}",result);
        log.info("result.toString() = {}",result.toString());
        return result.toString();
    }
}