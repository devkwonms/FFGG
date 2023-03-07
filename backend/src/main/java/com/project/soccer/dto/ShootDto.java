package com.project.soccer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShootDto {

    private int shootTotal;             // 총 슛 수
    private int effectiveShootTotal;    // 총 유효슛 수
    private int shootOutScore;          // 승부차기 슛 수
    private int goalTotal;              // 총 골 수 (실제 골 수) goalInPenalty+goalOutPenalty+goalPenaltyKick
    private int goalTotalDisplay;       // 게임 종료 후 유저에게 노출되는 골 수
    private int ownGoal;                // 자책 골 수
    private int shootHeading;           // 헤딩 슛 수
    private int goalHeading;            // 헤딩 골 수
    private int shootFreekick;          // 프리킥 슛 수
    private int goalFreekick;           // 프리킥 골 수
    private int shootInPenalty;         // 인패널티 슛 수
    private int goalInPenalty;          // 인패널티 골 수
    private int shootOutPenalty;        // 아웃패널티 슛 수
    private int goalOutPenalty;         // 아웃패널티 골 수
    private int shootPenaltyKick;       // 패널티킥 슛 수
    private int goalPenaltyKick;        // 패널티킥 골 수

}
