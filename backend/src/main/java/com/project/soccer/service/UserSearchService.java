package com.project.soccer.service;

import com.project.soccer.common.properties.GullitKey;
import com.project.soccer.dto.Division;
import com.project.soccer.dto.MatchType;
import com.project.soccer.dto.TopTierDto;
import com.project.soccer.dto.UserSearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSearchService {

    @Autowired
    private UrlConnService urlConnService;

    public UserSearchDto getUserInfo (UserSearchDto userSearchDto,String nickname) throws IOException {

        userSearchDto.setNickName(nickname);

        // 유저 닉네임으로 유저 정보 조회 API
        String getUserInfo = GullitKey.OWNER_URL + URLEncoder.encode(nickname,"UTF-8");

        // 존재하지 않는 닉네임 조회 시의 예외처리
        String userSearchResult = urlConnService.urlConn(getUserInfo);
        if(userSearchResult.isEmpty()) {
            log.info("해당 구단주는 존재하지않습니다!");
            return userSearchDto;
        }
        JSONObject userSearchJson = new JSONObject(userSearchResult);

        String accessId = userSearchJson.getString("accessId");
        Integer level = userSearchJson.getInt("level");

        userSearchDto.setAccessId(accessId);
        userSearchDto.setLevel(level);

//        log.info("userSearchDto = {}", userSearchDto);
        return userSearchDto;
    }

    public List<Map<String,Object>> getTopRank(String accessId) throws IOException {

        List<Map<String,Object>> topTierList = new ArrayList<>();

        String getTopRank = GullitKey.FC_URL+accessId+"/maxdivision";
        String topTierResult = urlConnService.urlConn(getTopRank);

        JSONArray topTierJson = new JSONArray(topTierResult);
//        log.info("topTierJson  = {}", topTierJson);

        // matchType 값 JsonArray를 통해 추출하기
        for( int i=0; i< topTierJson.length(); i++){

            JSONObject jsonObject = topTierJson.getJSONObject(i);

            int matchType = jsonObject.getInt("matchType");
            log.info("matchType  = {}", matchType);

            // matchType => enum의 label으로 값 추출
            String matchTypeEnum = MatchType.valueOf("M"+matchType).label();
            int division = jsonObject.getInt("division");
            // matchType => enum의 label으로 값 추출
            String divisionEnum = Division.valueOf("T"+division).getLabel();
            String achievementDate = jsonObject.getString("achievementDate").substring(0,10);   // YYYY-MM-DD 까지만 추출

            // matchType별 각 division에 대한 img를 추출하기위한 url 값 얻기
            String divisionImgUrl = Division.valueOf("T"+division).getDivisionImgUrl();

            Map<String,Object> topTierMap = new HashMap<>();
            topTierMap.put("matchType",matchTypeEnum);
            topTierMap.put("division",divisionEnum);
            topTierMap.put("achievementDate",achievementDate);
            topTierMap.put("divisionImgUrl",divisionImgUrl);

            topTierList.add(topTierMap);
        }
//        log.info("topTierList = {}", topTierList);

        return topTierList;
    }
}
