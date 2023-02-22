package com.project.soccer.service;

import com.project.soccer.dto.TopTierDto;
import com.project.soccer.dto.UserSearchDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UrlConnService urlConnService;

    public UserSearchDto userSearchApi (UserSearchDto userSearchDto,String userName) throws UnsupportedEncodingException {

        userSearchDto.setNickName(userName);

        // 유저 닉네임으로 유저 정보 조회 API
        String userSearchApi = "https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname=" + URLEncoder.encode(userName,"UTF-8");
        String userSearchResult = urlConnService.urlConn(userSearchApi);

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
        String topTierResult = urlConnService.urlConn(topTierApi);

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
}
