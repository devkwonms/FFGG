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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class matchController {

    private final UserSearchService userSearchService;

    private final MatchService matchService;

    @GetMapping
    public String user(){

        return "api/userSearch";
    }

    @ResponseBody
    @GetMapping("/userSearch/{userName}")
    public Map<String,Object> userSearch(@PathVariable String userName) throws JSONException, UnsupportedEncodingException, IOException {

        Map<String,Object> resultMap = new HashMap<>();

        UserSearchDto userSearchDto = new UserSearchDto();

        userSearchDto = userSearchService.getUserInfo(userSearchDto, userName);

        List<Map<String,Object>> topTierList = userSearchService.getTopRank(userSearchDto.getAccessId());

        resultMap.put("userSearchDto", userSearchDto);
        resultMap.put("topTierList", topTierList);

        return resultMap;
    }
    @ResponseBody
    @GetMapping("/matches/{accessId}/{matchtype}/{offset}")
    public Map<String,List> getMatchId(@PathVariable String accessId,@PathVariable int matchtype, @PathVariable int offset) throws IOException {

        Map<String,List> matchInfoMap = matchService.getMatchId(accessId, matchtype, offset);

        return matchInfoMap;
    }

    @ResponseBody
    @GetMapping("/matchRecordDetail/{matchId}")
    public MatchDto matchRecordDetail(@PathVariable String matchId) throws IOException{

        MatchDto matchRecordDetailResult = matchService.matchDetailRecordApi(matchId);

        return matchRecordDetailResult;
    }
}