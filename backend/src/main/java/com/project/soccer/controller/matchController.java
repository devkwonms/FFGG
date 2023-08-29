package com.project.soccer.controller;

import com.project.soccer.dto.MatchDto;
import com.project.soccer.dto.MatchThumbnailDto;
import com.project.soccer.dto.MatchType;
import com.project.soccer.dto.UserSearchDto;
import com.project.soccer.service.MatchService;
import com.project.soccer.service.UserSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class matchController {

    private final UserSearchService userSearchService;

    private final MatchService matchService;

    @GetMapping
    public String user() {

        return "api/userSearch";
    }

    @ResponseBody
    @GetMapping("/userSearch")
    public Map<String, Object> userSearch(@RequestParam(name = "nickname") String nickname) throws JSONException, IOException {

        Map<String, Object> resultMap = new HashMap<>();

        UserSearchDto userSearchDto = new UserSearchDto();

        userSearchDto = userSearchService.getUserInfo(userSearchDto, nickname);

        List<Map<String, Object>> topTierList = userSearchService.getTopRank(userSearchDto.getAccessId());

        resultMap.put("userSearchDto", userSearchDto);
        resultMap.put("topTierList", topTierList);

        return resultMap;
    }

    @ResponseBody
    @GetMapping("/matches")
    public Map<String, List> getMatchId(@RequestParam(name = "accessId") String accessId,
                                        @RequestParam(name = "matchtype", required = false, defaultValue = "52") int matchtype,
                                        @RequestParam(name = "offset", required = false, defaultValue = "0") int offset,
                                        @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) throws IOException {

        Map<String, List> matchInfoMap = matchService.getMatchId(accessId, matchtype, offset, limit);

        return matchInfoMap;
    }

    @ResponseBody
    @GetMapping("/matchRecordDetail")
    public MatchDto matchRecordDetail(@RequestParam(name = "matchId") String matchId) throws IOException {

        MatchDto matchRecordDetailResult = matchService.matchDetailRecordApi(matchId);

        return matchRecordDetailResult;
    }
}