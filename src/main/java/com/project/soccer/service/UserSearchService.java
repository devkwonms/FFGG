package com.project.soccer.service;

import com.project.soccer.dto.TopTierDto;
import com.project.soccer.dto.UserSearchDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserSearchService {

    public UserSearchDto userSearchApi (UserSearchDto userSearchDto,String userName) throws UnsupportedEncodingException {

        userSearchDto.setNickName(userName);

        // 유저 닉네임으로 유저 정보 조회 API
        String userSearchApi = "https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname=" + URLEncoder.encode(userName,"UTF-8");
        String userSearchResult = urlConn(userSearchApi);

        JSONObject userSearchJson = new JSONObject(userSearchResult);

        String accessId = userSearchJson.getString("accessId");
        Integer level = userSearchJson.getInt("level");

        userSearchDto.setAccessId(accessId);
        userSearchDto.setLevel(level);

        log.info("userSearchDto = {}", userSearchDto);
        return userSearchDto;
    }

    public List<Map<String,Object>> topTierApi(String accessId){

        List<Map<String,Object>> topTierList = new ArrayList<>();

        String topTierApi = "https://api.nexon.co.kr/fifaonline4/v1.0/users/"+accessId+"/maxdivision";
        String topTierResult = urlConn(topTierApi);

        JSONArray topTierJson = new JSONArray(topTierResult);
        log.info("topTierJson  = {}", topTierJson);

        // matchType 값 JsonArray를 통해 추출하기
        for( int i=0; i< topTierJson.length(); i++){

            JSONObject jsonObject = topTierJson.getJSONObject(i);
            int matchType = jsonObject.getInt("matchType");
            int division = jsonObject.getInt("division");
            String achievementDate = jsonObject.getString("achievementDate");

            Map<String,Object> topTierMap = new HashMap<>();
            topTierMap.put("matchType",matchType);
            topTierMap.put("division",division);
            topTierMap.put("achievementDate",achievementDate);

            topTierList.add(topTierMap);
        }
        log.info("topTierList = {}", topTierList);

        return topTierList;
    }
    // api urlConn method
    private String urlConn(String api) {
        StringBuffer result = new StringBuffer();
        System.setProperty("https.protocols", "TLSv1.2");

        try {

            URL url = new URL(api);
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