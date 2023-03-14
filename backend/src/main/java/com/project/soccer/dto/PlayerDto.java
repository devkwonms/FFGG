package com.project.soccer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {

    private int spId;         // 선수 고유 식별자 (/metadata/spid API 참고)
    private String  spName;       // 선수 이름
    private int spPosition = 0;   // 선수 포지션 (/metadata/spposition API 참고)
    private int spGrade;      // 선수 강화 등급
    private StatusDto status;     // 선수 경기 스탯
    private String spImgUrl;    // 선수 이미지 url


}
