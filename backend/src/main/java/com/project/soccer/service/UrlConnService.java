package com.project.soccer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Service
public class UrlConnService {

    // api urlConn method
    public String urlConn(String api) {
        StringBuffer result = new StringBuffer();
        System.setProperty("https.protocols", "TLSv1.2");

        try {

            URL url = new URL(api);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiNTAwOjEwIiwiYWNjb3VudF9pZCI6IjE1MjY5MjMzNzAiLCJhdXRoX2lkIjoiMiIsImV4cCI6MTY4ODg4ODQyMSwiaWF0IjoxNjczMzM2NDIxLCJuYmYiOjE2NzMzMzY0MjEsInNlcnZpY2VfaWQiOiI0MzAwMTE0ODEiLCJ0b2tlbl90eXBlIjoiQWNjZXNzVG9rZW4ifQ.SqVNR_woA6kakkt-DtclOu0DP5tJNwgd5q1DDqTde_Q");
            // connect()를 호출하여 서버로 요청을 보냄
            urlConnection.connect();

            try(BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"))){

            String returnLine;

            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine);
            }
          }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
