package com.project.soccer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchDto {

    private String nickName;    // 닉네임
    private String accessId;    // 유저고유식별자
    private int level;          // 유저레벨

    private int matchType;      // 매치종류
    private int division;       // 등급(티어)
    private String achievementDate; // 등급(티어)경신날짜

}
