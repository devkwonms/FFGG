package com.project.soccer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {

    private int shoot;                 // 슛 수
    private int effectiveShoot;        // 유효 슛 수
    private int assist;                // 어시스트 수
    private int goal;                  // 득점 수
    private int dribble;               // 드리블 거리(야드)
    private int intercept;             // 인터셉트 수
    private int defending;             // 디펜딩 수
    private int passTry;               // 패스 시도 수
    private int passSuccess;           // 패스 성공 수
    private int dribbleTry;            // 드리블 시도 수
    private int dribbleSuccess;        // 드리블 성공 수
    private int ballPossesionTry;      // 볼 소유 시도 수
    private int ballPossesionSuccess;  // 볼 소유 성공 수
    private int aerialTry;             // 공중볼 경합 시도 수
    private int aerialSuccess;         // 공중볼 경합 성공 수
    private int blockTry;              // 블락 시도 수
    private int block;                 // 블락 성공 수
    private int tackleTry;             // 태클 시도 수
    private int tackle;                // 태클 성공 수
    private int yellowCards;           // 옐로카드 수
    private int redCards;              // 레드카드 수
    private Float spRating;                // 선수 평점

}
