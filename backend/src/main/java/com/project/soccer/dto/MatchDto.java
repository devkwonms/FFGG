package com.project.soccer.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MatchDto {

    private String matchId; // 매치 고유 식별자
    private String matchDate;   // 매치 일자
    private int matchType;  // 매치 종류

    private List<MatchInfoDto> matchInfo;   // 매치 참여 플레이어별 매치 내용 상세 리스트

    public String getResult(MatchDto matchDto, int index) {
        String myResult = matchDto.getMatchInfo().get(index).getMatchDetail().getMatchResult();
        String anotherResult = matchDto.getMatchInfo().get(index == 0? 1:0).getMatchDetail().getMatchResult();

        int myMatchEndType = matchDto.getMatchInfo().get(index).getMatchDetail().getMatchEndType();
        int anotherMatchEndType = matchDto.getMatchInfo().get(index == 0? 1:0).getMatchDetail().getMatchEndType();

        if(myResult.equals("오류")|| (myMatchEndType != 0 && myMatchEndType != 1)||(matchDto.getMatchInfo().size()==1 && myResult.equals("패"))){
            myResult = "몰수패";
        }else if(anotherResult.equals("오류") || (anotherMatchEndType != 0 && anotherMatchEndType != 1)||(matchDto.getMatchInfo().size()==1 && myResult.equals("승"))){
            myResult = "몰수승";
        }
        return myResult;
    }
}
