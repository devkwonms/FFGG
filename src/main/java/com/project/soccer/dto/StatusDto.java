package com.project.soccer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {

    private Integer shoot;                 // 슛 수
    private Integer effectiveShoot;        // 유효 슛 수
    private Integer assist;                // 어시스트 수
    private Integer goal;                  // 득점 수
    private Integer dribble;               // 드리블 거리(야드)
    private Integer intercept;             // 인터셉트 수
    private Integer defending;             // 디펜딩 수
    private Integer passTry;               // 패스 시도 수
    private Integer passSuccess;           // 패스 성공 수
    private Integer dribbleTry;            // 드리블 시도 수
    private Integer dribbleSuccess;        // 드리블 성공 수
    private Integer ballPossesionTry;      // 볼 소유 시도 수
    private Integer ballPossesionSuccess;  // 볼 소유 성공 수
    private Integer aerialTry;             // 공중볼 경합 시도 수
    private Integer aerialSuccess;         // 공중볼 경합 성공 수
    private Integer blockTry;              // 블락 시도 수
    private Integer block;                 // 블락 성공 수
    private Integer tackleTry;             // 태클 시도 수
    private Integer tackle;                // 태클 성공 수
    private Integer yellowCards;           // 옐로카드 수
    private Integer redCards;              // 레드카드 수
    private Float spRating;                // 선수 평점
}
