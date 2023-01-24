package com.project.soccer.service;

import com.google.gson.Gson;
import com.project.soccer.dto.MatchDto;
import com.project.soccer.dto.MatchThumbnailDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MatchService {
    @Autowired
    private UrlConnService urlConnService;

    // 유저 고유 식별자로 유저의 매치 기록 썸네일 조회(10개)
    public List<MatchThumbnailDto> matchRecordApi(String accessId){
        // 유저 고유 식별자로 유저의 매치 기록(10경기) 조회 API
        String matchRecordApi = "https://api.nexon.co.kr/fifaonline4/v1.0/users/0e73fddc25c0740abbad3401/matches?matchtype="+50+"&offset="+0+"&limit="+10;
        String matchRecordResults = urlConnService.urlConn(matchRecordApi);

        List<MatchThumbnailDto> matchThumbnailList = new ArrayList<>();

        JSONArray matchRecordJson = new JSONArray(matchRecordResults);
//        log.info("matchRecordJson = {}", matchRecordJson);
//        log.info(matchRecordJson.getClass().getName());   => 변수 타입확인법

        // 매치기록 10개 하나씩 뽑아보기
        for(int i =0; i<matchRecordJson.length(); i++){
            String matchId = (String) matchRecordJson.get(i);

            // i번째 리스트 상세기록
            MatchDto matchDto = matchDetailRecordApi(matchId);

            MatchThumbnailDto matchThumbnailDto = new MatchThumbnailDto();
            matchThumbnailDto.setMatchId(matchId);
            matchThumbnailDto.setMatchDate(matchDto.getMatchDate());
            matchThumbnailDto.setAccessId(accessId);

            boolean yn = matchDto.getMatchInfo().get(0).getAccessId().equals(accessId);

            int j = 0;
            int k = 1;

            if (!yn){
                j = 1;
                k = 0;
            }
            log.info("나임");

            matchThumbnailDto.setMyNickName(matchDto.getMatchInfo().get(j).getNickname());
            matchThumbnailDto.setMyGoal(matchDto.getMatchInfo().get(j).getShoot().getGoalTotal());
            matchThumbnailDto.setMyResult(matchDto.getMatchInfo().get(j).getMatchDetail().getMatchResult());

            log.info("상대임");
            matchThumbnailDto.setAnotherNickname(matchDto.getMatchInfo().get(k).getNickname());
            matchThumbnailDto.setAnotherGoal(matchDto.getMatchInfo().get(k).getShoot().getGoalTotal());
            log.info("another matchThumbnailDto = {}",matchThumbnailDto);

            matchThumbnailList.add(matchThumbnailDto);
        }

        return matchThumbnailList;
    }

    // 매치 상세 기록 조회
    public MatchDto matchDetailRecordApi(String matchId){
        // 매치 상세 기록 조회 API

        String matchDetailRecordApi = "https://api.nexon.co.kr/fifaonline4/v1.0/matches/"+matchId;
        String matchDetailRecordResult = urlConnService.urlConn(matchDetailRecordApi);

        JSONObject matchDetailRecordJson = new JSONObject(matchDetailRecordResult);
        log.info("matchDetailRecordJson = {}", matchDetailRecordJson);

        Gson gson = new Gson();

        MatchDto matchDto = gson.fromJson(matchDetailRecordJson.toString(), MatchDto.class);
        log.info("matchDto = {}", matchDto);

        return matchDto;
    }

}
