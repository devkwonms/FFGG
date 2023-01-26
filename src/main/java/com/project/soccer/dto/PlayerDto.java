package com.project.soccer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {

    private Integer spId;         // 선수 고유 식별자 (/metadata/spid API 참고)
    private String  spName;       // 선수 이름
    private Integer spPosition;   // 선수 포지션 (/metadata/spposition API 참고)
    private Integer spGrade;      // 선수 강화 등급
    private StatusDto status;     // 선수 경기 스탯
}
