package com.project.soccer.service;

import com.google.gson.Gson;
import com.project.soccer.common.properties.GullitKey;
import com.project.soccer.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchService {

    private final UrlConnService urlConnService;
    final static int MATCH_LEAGUE = 50;

    static final Gson gson = new Gson();

    // 유저 고유 식별자로 유저의 매치 기록 썸네일 조회(10개)
    public Map<String, List> getMatchId(String accessId, int matchtype, int offset, int limit) throws IOException {

        // 유저 고유 식별자로 유저의 매치 기록(10경기) 조회 API
        String getMatchId = GullitKey.FC_URL + accessId + "/matches?matchtype=" + matchtype + "&offset=" + offset + "&limit=" + limit;
        String matchIdResults = urlConnService.urlConn(getMatchId);

        JSONArray matchIdJson = new JSONArray(matchIdResults);

        // Use parallel stream for concurrent processing
        List<MatchDto> matchDetailList = IntStream.range(0, matchIdJson.length())
                .parallel()
                .mapToObj(i -> {
                    String matchId = (String) matchIdJson.get(i);
                    try {
                        // i번째 리스트 상세기록
                        return matchDetailRecordApi(matchId);
                    } catch (IOException e) {
                        // Handle exception as needed
                        return null;
                    }
                })
                .collect(Collectors.toList());

        Map<String, List> matchInfoMap = new HashMap<>();
        matchInfoMap.put("matchDetailList", matchDetailList);

        return matchInfoMap;

//        Map<String, List> matchInfoMap = new HashMap<>();
//
//        List<MatchDto> matchDetailList = new ArrayList<>();
//
//        // 나의 경기결과
//
//        // 매치기록 (limit)개 하나씩 뽑아보기
//        for (int i = 0; i < matchIdJson.length(); i++) {
//            String matchId = (String) matchIdJson.get(i);
//
//            // i번째 리스트 상세기록
//            MatchDto matchDto = matchDetailRecordApi(matchId);
//            matchDetailList.add(matchDto);
//
//        }
//        matchInfoMap.put("matchDetailList", matchDetailList);
//
//        return matchInfoMap;
    }

    // 매치 상세 기록 조회
    public MatchDto matchDetailRecordApi(String matchId) throws IOException {
        // 매치 상세 기록 조회 API

        String matchDetailRecordApi = GullitKey.MATCH_URL + matchId;
        String matchDetailRecordResult = urlConnService.urlConn(matchDetailRecordApi);

        JSONObject matchDetailRecordJson = new JSONObject(matchDetailRecordResult);

        MatchDto matchDto = gson.fromJson(matchDetailRecordJson.toString(), MatchDto.class);

        // 몰수승or몰수패or오류 로 인한 빈 player 값이 올때의 오류방지 return 처리
        if (matchDto.getMatchInfo().get(0).getPlayer().isEmpty() ||
                matchDto.getMatchInfo().get(1).getPlayer().isEmpty()) {
            return matchDto;
        }
//        // 선수 이름, 선수이미지url 추출
        JSONArray spNameJson = setMatchPlayerNameApi(matchDto);

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
            spNameResult = urlConnService.urlConn(GullitKey.PLAYERS_URL);
            spNameCache.put("spNameResult", spNameResult);
        }

        JSONArray spNameJson = new JSONArray(spNameResult);

        // 나와 상대선수 각각 18명(총36명) 의 주전선수 고유 id 추출하기
        for (int i = 0; i < 2; i++) {
            List<PlayerDto> players = matchDto.getMatchInfo().get(i).getPlayer();
            for (int j = 0; j < players.size(); j++) {
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
        int max = spNameJson.length() - 1;

        while (min <= max) {

            int mid = (min + max) / 2; // 중간 Index를 구하여 검색한다.

            if ((int) spNameJson.getJSONObject(mid).get("id") < spId) { // 1. 찾는값이 더 큰 경우 우측에서 찾는다.
                min = mid + 1;

            } else if ((int) spNameJson.getJSONObject(mid).get("id") > spId) { // 2. 찾는값이 더 작은 경우 좌측에서 찾는다.
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
        String spImgUrl = GullitKey.PLAYER_IMG_URL + spId + ".png";
        String spNameResult = "";

        // 예외처리
        try {
            spNameResult = urlConnService.urlConn(spImgUrl);
        } catch (IOException e) {
//            log.error("URL 연결 중 오류가 발생했습니다. url={}", spImgUrl);
            // 예외발생시 (선수이미지가 원래 없는경우) 디폴트 이미지 삽입
            spImgUrl = GullitKey.PLAYER_DEFAULT_IMG_URL;
            return spImgUrl;
        }
        return spImgUrl;
    }
}
