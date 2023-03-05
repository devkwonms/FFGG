package com.project.soccer.controller;

import com.project.soccer.dto.MatchDto;
import com.project.soccer.dto.MatchThumbnailDto;
import com.project.soccer.dto.UserSearchDto;
import com.project.soccer.service.MatchService;
import com.project.soccer.service.UserSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public Map<String,Object> userSearch(@PathVariable String userName) throws JSONException, UnsupportedEncodingException {

        Map<String,Object> resultMap = new HashMap<>();

        UserSearchDto userSearchDto = new UserSearchDto();
        userSearchDto = userSearchService.userSearchApi(userSearchDto, userName);

        List<Map<String,Object>> topTierList = userSearchService.topTierApi(userSearchDto.getAccessId());
        List<MatchThumbnailDto> matchThumbnailList = matchService.matchRecordApi(userSearchDto.getAccessId());

        resultMap.put("userSearchDto", userSearchDto);
        resultMap.put("topTierList", topTierList);
        resultMap.put("matchThumbnailList", matchThumbnailList);

        return resultMap;
    }

    @ResponseBody
    @GetMapping("/matchRecordDetail/{matchId}")
    public MatchDto matchRecordDetail(@PathVariable String matchId){

        MatchDto matchRecordDetailResult = matchService.matchDetailRecordApi(matchId);

        return matchRecordDetailResult;
    }
}