package com.project.soccer.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchDto {

    private String matchId; // 매치 고유 식별자
    private String matchDate;   // 매치 일자
    private Integer matchType;  // 매치 종류

    private List<MatchInfoDto> matchInfo;   // 매치 참여 플레이어별 매치 내용 상세 리스트
}
