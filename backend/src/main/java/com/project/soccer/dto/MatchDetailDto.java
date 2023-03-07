package com.project.soccer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MatchDetailDto {

    private int seasonId;       // 시즌 ID
    private String matchResult;     // 매치 결과 (“승”, “무”, “패”)
    private int matchEndType;   // 매치종료 타입 (0: 정상종료, 1: 몰수승, 2:몰수패)
    private int systemPause;    // 게임 일시정지 수
    private int foul;           // 파울 수
    private int injury;         // 부상 수
    private int redCards;       // 받은 레드카드 수
    private int yellowCards;    // 받은 옐로카드 수
    private int dribble;        // 드리블 거리(야드)
    private int cornerKick;     // 코너킥 수
    private int possession;     // 점유율
    private int offsideCount;   // 오프사이드 수
    private Double averageRating;  // 경기 평점
    private String controller;     // 사용한 컨트롤러 타입 (keyboard / pad / etc 중 1)

}
