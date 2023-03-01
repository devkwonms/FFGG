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
import java.util.ArrayList;
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
    public String user(){

        return "user/userSearch";
    }

    @ResponseBody
    @GetMapping("/userSearch/{userName}")
    public List<Object> userSearch(@PathVariable String userName) throws JSONException, UnsupportedEncodingException {

        List<Object> resultList = new ArrayList<>();
        UserSearchDto userSearchDto = new UserSearchDto();
        userSearchDto = userSearchService.userSearchApi(userSearchDto, userName);

        List<Map<String,Object>> topTierList = userSearchService.topTierApi(userSearchDto.getAccessId());

        resultList.add(userSearchDto);
        resultList.add(topTierList);

        return resultList;
    }
    @ResponseBody
    @GetMapping("/matchRecord/{accessId}")
    public List<MatchThumbnailDto> matchRecord(@PathVariable String accessId) throws JSONException{

        List<MatchThumbnailDto> matchThumbnailList = matchService.matchRecordApi(accessId);

        return matchThumbnailList;
    }

    @ResponseBody
    @GetMapping("/matchRecordDetail/{matchId}")
    public MatchDto matchRecordDetail(@PathVariable String matchId){

        MatchDto matchRecordDetailResult = matchService.matchDetailRecordApi(matchId);

        return matchRecordDetailResult;
    }
}