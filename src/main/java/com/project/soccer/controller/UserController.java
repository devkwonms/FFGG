package com.project.soccer.controller;

import com.project.soccer.dto.UserSearchDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping
    public String user(){

        return "user/userSearch";
    }

    @ResponseBody
    @GetMapping("/userSearch/{userName}")
    public UserSearchDto userSearch(@PathVariable String userName) throws JSONException, UnsupportedEncodingException {
        // 유저 조회Dto
        UserSearchDto userSearchDto = new UserSearchDto();

        // Dto에 userName 넣어주기
        userSearchDto.setNickName(userName);

//        log.info("userDto.getAchievementDate() = {}", userSearchDto.getAchievementDate() );

        // 유저 닉네임으로 유저 정보 조회 API
        String userSearchApi = "https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname=" + URLEncoder.encode(userSearchDto.getNickName(),"UTF-8");

        String userSearchResult = urlConn(userSearchApi);

        JSONObject userSearchJson = new JSONObject(userSearchResult);

        String accessId = userSearchJson.getString("accessId");
        Integer level = userSearchJson.getInt("level");

        userSearchDto.setAccessId(accessId);
        userSearchDto.setLevel(level);

        log.info("userDto.getNickName() = {}", userSearchDto.getNickName() );
        log.info("userDto.getAccessId() = {}", userSearchDto.getAccessId() );
        log.info("userDto.getLevel() = {}", userSearchDto.getLevel() );

        String userTopTierApi = "https://api.nexon.co.kr/fifaonline4/v1.0/users/"+accessId+"/maxdivision";

        String userTopTierResult = urlConn(userTopTierApi);

        JSONArray matchSearchJson = new JSONArray(userTopTierResult);

        // matchType 값 JsonArray를 통해 추출하기
        for( int i=0; i< matchSearchJson.length(); i++){
            JSONObject jsonObject = matchSearchJson.getJSONObject(i);
            int matchType = jsonObject.getInt("matchType");
            log.info("matchType = {}", matchType);
        }

        log.info("userDto.getDivision() = {}", userSearchDto.getDivision() );
        log.info("userDto.getMatchType() = {}", userSearchDto.getMatchType() );
//        resultMap.put("userSearchResult",userSearchResult);
//        resultMap.put("userTopTierResult",userTopTierResult);



//        log.info("resultMap={}",resultMap);
        log.info("userDto={}",userSearchDto);
        return userSearchDto;
    }


    // api urlConn method
    private String urlConn(String api) {
        StringBuffer result = new StringBuffer();
        System.setProperty("https.protocols", "TLSv1.2");

        try {
            String apiUrl = api ;

            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiNTAwOjEwIiwiYWNjb3VudF9pZCI6IjE1MjY5MjMzNzAiLCJhdXRoX2lkIjoiMiIsImV4cCI6MTY4ODg4ODQyMSwiaWF0IjoxNjczMzM2NDIxLCJuYmYiOjE2NzMzMzY0MjEsInNlcnZpY2VfaWQiOiI0MzAwMTE0ODEiLCJ0b2tlbl90eXBlIjoiQWNjZXNzVG9rZW4ifQ.SqVNR_woA6kakkt-DtclOu0DP5tJNwgd5q1DDqTde_Q");
            urlConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            String returnLine;
        while((returnLine = bufferedReader.readLine()) != null) {
            result.append(returnLine);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}