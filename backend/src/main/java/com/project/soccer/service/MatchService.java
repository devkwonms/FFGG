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

import java.io.IOException;
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

    static final Gson gson = new Gson();

    // matchType(공식경기,클래식1on1(친선경기),감독모드) 받는 method
    public List<Integer> getMatchType(){
        List<Integer> matchTypeList = new ArrayList<>();

        int MatchTypeOfficial;
        int MatchTypeClassic;
        int MatchTypeDirection;
        try {
            MatchTypeOfficial = Integer.parseInt(MatchType.valueOfLabel("공식경기").toString().substring(1, 3));
            MatchTypeClassic = Integer.parseInt(MatchType.valueOfLabel("클래식 1on1").toString().substring(1, 3));
            MatchTypeDirection = Integer.parseInt(MatchType.valueOfLabel("감독모드").toString().substring(1, 3));
            
        }catch (NumberFormatException e){
            // 정수형으로 변환할 수 없는 경우 예외 처리
            System.err.println("MatchType을 정수형으로 변환할 수 없습니다!");
            MatchTypeOfficial = 0;
            MatchTypeClassic = 0;
            MatchTypeDirection = 0;
        }
            matchTypeList.add(MatchTypeOfficial);
            matchTypeList.add(MatchTypeClassic);
            matchTypeList.add(MatchTypeDirection);

        return matchTypeList;
    }
    // 유저 고유 식별자로 유저의 매치 기록 썸네일 조회(10개)
    public List<MatchThumbnailDto> getMatchThumbnail(String accessId) throws IOException {

        // 유저 고유 식별자로 유저의 매치 기록(10경기) 조회 API
        String getMatchThumbnail = "https://api.nexon.co.kr/fifaonline4/v1.0/users/"+accessId+"/matches?matchtype="+52+"&offset="+0+"&limit="+10;
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
    public MatchDto matchDetailRecordApi(String matchId) throws IOException {
        // 매치 상세 기록 조회 API

        String matchDetailRecordApi = "https://api.nexon.co.kr/fifaonline4/v1.0/matches/"+matchId;
        String matchDetailRecordResult = urlConnService.urlConn(matchDetailRecordApi);

        JSONObject matchDetailRecordJson = new JSONObject(matchDetailRecordResult);

        MatchDto matchDto = gson.fromJson(matchDetailRecordJson.toString(), MatchDto.class);
        if(matchDto.getMatchInfo().get(0).getPlayer().isEmpty() ||
                matchDto.getMatchInfo().get(1).getPlayer().isEmpty()){
            return matchDto;
        }
//        // 선수 이름, 선수이미지url 추출
//        JSONArray spNameJson = setMatchPlayerNameApi(matchDto);

        log.info("matchDto = {}", matchDto);
        return matchDto;
    }
    // {선수아이디,선수이름}의 데이터를 caching 하기위한 Map 객체선언
    private static Map<String, String> spNameCache = new ConcurrentHashMap<>();

    // 선수 고유 id로 선수 이름 추출 api
    public JSONArray setMatchPlayerNameApi(MatchDto matchDto) throws IOException {

        // 캐시에 저장된 spNameResult 결과가 있으면 캐시에서 가져오고, 없으면 API 호출
        String spNameResult;

        if (spNameCache.containsKey("spNameResult")) {
            spNameResult = spNameCache.get("spNameResult");
        } else {
            spNameResult = urlConnService.urlConn("https://static.api.nexon.co.kr/fifaonline4/latest/spid.json");
            spNameCache.put("spNameResult", spNameResult);
        }

        JSONArray spNameJson = new JSONArray(spNameResult);

        // 나와 상대선수 각각 18명(총36명) 의 주전선수 고유 id 추출하기
        for (int i = 0; i < 2; i++) {
            List<PlayerDto> players = matchDto.getMatchInfo().get(i).getPlayer();
            for (int j = 0; j < 18; j++) {
                int player = players.get(j).getSpPosition();
                if (player != 28) {
                    // 각 spId(선수식별자) 에 대한 이름추출하는 method, Imgurl 추출하는 method를 호출하여 각각 해당변수에 setting
                    int spId = players.get(j).getSpId();
                    players.get(j).setSpName(extractPlayerNameById(spNameJson, spId));
                    players.get(j).setSpImgUrl(extractPlayerImgUrl(spId));
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

        // 선수 이름을 찾지 못한 경우 null 반환
        return null;
    }
    public String extractPlayerImgUrl(int spId) {
        String spImgUrl = "https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/playersAction/p" + spId + ".png";
        String spNameResult = "";

        // 예외처리
        try {
            spNameResult = urlConnService.urlConn(spImgUrl);
        } catch (IOException e) {
            log.error("URL 연결 중 오류가 발생했습니다. url={}", spImgUrl);
            // 예외발생시 (선수이미지가 원래 없는경우) 디폴트 이미지 삽입
            spImgUrl = "https://cdn-icons-png.flaticon.com/512/1909/1909621.png";
            return spImgUrl;
        }
        return spImgUrl;
    }
}
