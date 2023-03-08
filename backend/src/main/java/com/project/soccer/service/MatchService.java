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
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchService {

    private final UrlConnService urlConnService;

    // 유저 고유 식별자로 유저의 매치 기록 썸네일 조회(10개)
    public List<MatchThumbnailDto> getMatchThumbnail(String accessId){

        // 유저 고유 식별자로 유저의 매치 기록(10경기) 조회 API
        String getMatchThumbnail = "https://api.nexon.co.kr/fifaonline4/v1.0/users/"+accessId+"/matches?matchtype="+52+"&offset="+0+"&limit="+1;
        String matchRecordResults = urlConnService.urlConn(getMatchThumbnail);

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

            // l= 나 , r = 상대로 설정하기위한 조건문
            int l = 0;
            int r = 1;

            if (!matchDto.getMatchInfo().get(0).getAccessId().equals(accessId)) {
                l = 1; r = 0;
            }

            if(matchDto.getMatchInfo().get(l).getMatchDetail().getMatchResult().equals("오류")
                    ||matchDto.getMatchInfo().get(r).getMatchDetail().getMatchResult().equals("오류")){
                matchThumbnailDto.setMyNickName(matchDto.getMatchInfo().get(l).getNickname());
                matchThumbnailDto.setAnotherNickname(matchDto.getMatchInfo().get(r).getNickname());
                matchThumbnailDto.setMyResult(matchDto.getResult(matchDto,l));
                matchThumbnailList.add(matchThumbnailDto);
                continue;
            }

            matchThumbnailDto.setMyNickName(matchDto.getMatchInfo().get(l).getNickname());
            matchThumbnailDto.setMyGoal(matchDto.getMatchInfo().get(l).getShoot().getGoalTotal());
            matchThumbnailDto.setMyResult(matchDto.getMatchInfo().get(l).getMatchDetail().getMatchResult());

            matchThumbnailDto.setAnotherNickname(matchDto.getMatchInfo().get(r).getNickname());
            matchThumbnailDto.setAnotherGoal(matchDto.getMatchInfo().get(r).getShoot().getGoalTotal());

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
        if(matchDto.getMatchInfo().get(0).getPlayer().isEmpty() ||
                matchDto.getMatchInfo().get(1).getPlayer().isEmpty()){
            return matchDto;
        }
//        // 선수 이름 추출
//        JSONArray spNameJson = setMatchPlayerNameApi(matchDto);

        log.info("matchDto = {}", matchDto);
        return matchDto;
    }

    // 선수 고유 id로 선수 이름 추출 api
    public JSONArray setMatchPlayerNameApi(MatchDto matchDto) {
        String spNameResult = urlConnService.urlConn("https://static.api.nexon.co.kr/fifaonline4/latest/spid.json");

        JSONArray spNameJson = new JSONArray(spNameResult);

        // 나와 상대선수 각각 18명(총36명) 의 주전선수 고유 id 추출하기
        for (int i = 0; i < 2; i++) {
            List<PlayerDto> players = matchDto.getMatchInfo().get(i).getPlayer();
            for (int j = 0; j < 18; j++) {
                int player = players.get(j).getSpPosition();
                if (player != 28) {
                    int spId = players.get(j).getSpId();
                    players.get(j).setSpName(extractPlayerNameById(spNameJson, spId));
                }
            }
        }
        return spNameJson;

    }

    private static Map<Integer, String> cache = new ConcurrentHashMap<>(); // 캐싱을 위한 Map 객체 생성
    private static Lock lock = new ReentrantLock();

    // 이분탐색을 통한 선수 id => 선수 id 매칭 => 선수 이름 추출 method
    private String extractPlayerNameById(JSONArray spNameJson, int spId) {

    // 캐시된 결과가 있는 경우, 캐시에서 값을 가져와서 반환(락을 확보한 스레드만 해당 블록에 접근할 수 있으므로, 성능 문제가 발생할 확률감소)
        try {
            lock.lock();
            if (cache.containsKey(spId)) {
//                log.info("선수 id {}의 이름을 캐시에서 가져옴", spId);
                return cache.get(spId);
            }
        } finally {
            lock.unlock();
        }

        int min = 0;
        int max = spNameJson.length()-1;

        while (min <= max) {

            int mid = (min + max) / 2; // 중간 Index를 구하여 검색한다.

            if (Integer.parseInt(spNameJson.getJSONObject(mid).get("id").toString()) < spId) { // 1. 찾는값이 더 큰 경우 우측에서 찾는다.
                min = mid + 1;

            } else if (Integer.parseInt(spNameJson.getJSONObject(mid).get("id").toString()) > spId) { // 2. 찾는값이 더 작은 경우 좌측에서 찾는다.
                max = mid - 1;

            } else { // 3. 찾는값을 발견한 경우

                String name = spNameJson.getJSONObject(mid).get("name").toString();
                cache.put(spId, name); // 캐시에 결과를 저장
                return name;
            }
        }

        return "failed!!";
    }


}
