package com.project.soccer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopTierDto {

    private int matchType;      // 매치종류
    private int division;       // 등급(티어)
    private String achievementDate; // 등급(티어)경신날짜

}
