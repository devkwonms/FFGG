package com.project.soccer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchDetailDto {

    private Integer seasonId;       // 시즌 ID
    private String matchResult;     // 매치 결과 (“승”, “무”, “패”)
    private Integer matchEndType;   // 매치종료 타입 (0: 정상종료, 1: 몰수승, 2:몰수패)
    private Integer systemPause;    // 게임 일시정지 수
    private Integer foul;           // 파울 수
    private Integer injury;         // 부상 수
    private Integer redCards;       // 받은 레드카드 수
    private Integer yellowCards;    // 받은 옐로카드 수
    private Integer dribble;        // 드리블 거리(야드)
    private Integer cornerKick;     // 코너킥 수
    private Integer possession;     // 점유율
    private Integer offsideCount;   // 오프사이드 수
    private Double averageRating;  // 경기 평점
    private String controller;     // 사용한 컨트롤러 타입 (keyboard / pad / etc 중 1)

}
