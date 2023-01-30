package com.project.soccer.service;

import com.google.gson.Gson;
import com.project.soccer.dto.MatchDto;
import com.project.soccer.dto.MatchThumbnailDto;
import com.project.soccer.dto.PlayerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchService {

    private final UrlConnService urlConnService;

    // 유저 고유 식별자로 유저의 매치 기록 썸네일 조회(10개)
    public List<MatchThumbnailDto> matchRecordApi(String accessId){

        // 유저 고유 식별자로 유저의 매치 기록(10경기) 조회 API
        String matchRecordApi = "https://api.nexon.co.kr/fifaonline4/v1.0/users/"+accessId+"/matches?matchtype="+52+"&offset="+0+"&limit="+10;
        String matchRecordResults = urlConnService.urlConn(matchRecordApi);

        List<MatchThumbnailDto> matchThumbnailList = new ArrayList<>();

        JSONArray matchRecordJson = new JSONArray(matchRecordResults);

        // 매치기록 10개 하나씩 뽑아보기
        for(int i =0; i<matchRecordJson.length(); i++){
            String matchId = (String) matchRecordJson.get(i);

            // i번째 리스트 상세기록
            MatchDto matchDto = matchDetailRecordApi(matchId);

            MatchThumbnailDto matchThumbnailDto = new MatchThumbnailDto();
            matchThumbnailDto.setMatchId(matchId);
            matchThumbnailDto.setMatchDate(matchDto.getMatchDate());
            matchThumbnailDto.setAccessId(accessId);

            int j = 0;
            int k = 1;

            if (!matchDto.getMatchInfo().get(0).getAccessId().equals(accessId)) {
                j = 1; k = 0;
            }

            matchThumbnailDto.setMyNickName(matchDto.getMatchInfo().get(j).getNickname());
            matchThumbnailDto.setMyGoal(matchDto.getMatchInfo().get(j).getShoot().getGoalTotal());
            matchThumbnailDto.setMyResult(matchDto.getMatchInfo().get(j).getMatchDetail().getMatchResult());

            matchThumbnailDto.setAnotherNickname(matchDto.getMatchInfo().get(k).getNickname());
            matchThumbnailDto.setAnotherGoal(matchDto.getMatchInfo().get(k).getShoot().getGoalTotal());

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

        Gson gson = new Gson();

        MatchDto matchDto = gson.fromJson(matchDetailRecordJson.toString(), MatchDto.class);
//        log.info("matchDto = {}", matchDto);

        List<Integer> spNameList0 = new ArrayList<>();
        List<Integer> spNameList1 = new ArrayList<>();

        // 나와 상대선수 각각 11명(총22명) 의 주전선수 고유 id 추출하기
        for(int i =0 ; i < 2; i++) {
            for (int j = 0; j < 18; j++) {

                // spPosition = 28 (교체)인 선수 거르기
                if(matchDto.getMatchInfo().get(i).getPlayer().get(j).getSpPosition() != 28){
                    Integer spId = matchDto.getMatchInfo().get(i).getPlayer().get(j).getSpId();

                    if(i == 0){
                        spNameList0.add(spId);
                    }else{
                        spNameList1.add(spId);
                    }
                }
            }

        }
        Collections.sort(spNameList0);
        Collections.sort(spNameList1);

        log.info("spNameList0= {}", spNameList0);
        log.info("spNameList1= {}", spNameList1);

        int index = Collections.binarySearch(spNameList0, 260231838);

        log.info("index= {}", index);

        return matchDto;
    }

    // 선수 고유 id로 선수 이름 추출 api
    public List<PlayerDto> matchPlayerNameApi(int spId) {

        List<PlayerDto> playerNameList = new ArrayList<>();

        // 선수 식별자를 통한 선수 이름 추출
        String spNameApi = "https://static.api.nexon.co.kr/fifaonline4/latest/spid.json";
        String spNameResult = urlConnService.urlConn(spNameApi);

        JSONArray spNameJson = new JSONArray(spNameResult);

        spNameSearch(spNameJson,spId);

        return playerNameList;
    }

    // 이분탐색을 통한 선수 id => 선수 id 매칭 => 선수 이름 추출 method
    private String spNameSearch(JSONArray spNameJson, int spId) {

        int min = 0;
        int max = spNameJson.length()-1;
//        int max = 3;

        while (min <= max) {

            int mid = (min + max) / 2; // 중간 Index를 구하여 검색한다.

            if (Integer.parseInt(spNameJson.getJSONObject(mid).get("id").toString()) < spId) { // 1. 찾는값이 더 큰 경우 우측에서 찾는다.

                min = mid + 1;
//                log.info("if = {}");

            } else if (Integer.parseInt(spNameJson.getJSONObject(mid).get("id").toString()) > spId) { // 2. 찾는값이 더 작은 경우 좌측에서 찾는다.

                max = mid - 1;
//                log.info("else if = {}");

            } else { // 3. 찾는값을 발견한 경우

                spNameJson.getJSONObject(mid).get("name");
//                log.info("@= {}",spNameJson.getJSONObject(mid).get("name"));
//                log.info("@@ = {}",Integer.parseInt(spNameJson.getJSONObject(mid).get("id").toString()));
//                log.info("@@# = {}",spId);

                return (String) spNameJson.getJSONObject(mid).get("name");

            }
        }

        return "failed!!";
    }


}
