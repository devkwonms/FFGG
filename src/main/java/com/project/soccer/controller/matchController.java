package com.project.soccer.controller;

import com.project.soccer.dto.TopTierDto;
import com.project.soccer.dto.UserSearchDto;
import com.project.soccer.service.UserSearchService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/user")
public class matchController {

    @Autowired
    private UserSearchService userSearchService;

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

//        log.info("userSearchDto= {}", userSearchDto);

        resultList.add(userSearchDto);
        resultList.add(topTierList);
//        log.info("resultList = {}",resultList);
        return resultList;
    }

}