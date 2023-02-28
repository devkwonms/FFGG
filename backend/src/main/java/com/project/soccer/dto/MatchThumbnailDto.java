package com.project.soccer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MatchThumbnailDto {

    private String matchId;         // 매치 고유 식별자
    private String accessId;        // 유저고유식별자

    private String matchDate;       // 매치 일자

    private String myResult;        // 내 경기 결과
    private String myNickName;      // 내 닉네임
    private String anotherNickname; // 상대 닉네임
    private int myGoal = 0;         // 내 골 수
    private int anotherGoal = 0;    // 상대 골 수

}
