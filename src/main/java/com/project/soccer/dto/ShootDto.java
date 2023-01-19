package com.project.soccer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShootDto {

    private Integer shootTotal;             // 총 슛 수
    private Integer effectiveShootTotal;    // 총 유효슛 수
    private Integer shootOutScore;          // 승부차기 슛 수
    private Integer goalTotal;              // 총 골 수 (실제 골 수) goalInPenalty+goalOutPenalty+goalPenaltyKick
    private Integer goalTotalDisplay;       // 게임 종료 후 유저에게 노출되는 골 수
    private Integer ownGoal;                // 자책 골 수
    private Integer shootHeading;           // 헤딩 슛 수
    private Integer goalHeading;            // 헤딩 골 수
    private Integer shootFreekick;          // 프리킥 슛 수
    private Integer goalFreekick;           // 프리킥 골 수
    private Integer shootInPenalty;         // 인패널티 슛 수
    private Integer goalInPenalty;          // 인패널티 골 수
    private Integer shootOutPenalty;        // 아웃패널티 슛 수
    private Integer goalOutPenalty;         // 아웃패널티 골 수
    private Integer shootPenaltyKick;       // 패널티킥 슛 수
    private Integer goalPenaltyKick;        // 패널티킥 골 수

}
