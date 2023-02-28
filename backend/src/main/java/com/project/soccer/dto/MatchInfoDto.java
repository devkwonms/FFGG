package com.project.soccer.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MatchInfoDto {

    private String accessId;                    // 유저 고유 식별자
    private String nickname;                    // 유저 닉네임
    private MatchDetailDto matchDetail;         // 매치 결과 상세 정보
    private ShootDto shoot;                     // 슈팅 정보
    private List<ShootDetailDto> shootDetail;   // 슈팅 별 상세정보 리스트
    private PassDto pass;                       // 패스 정보
    private DefenceDto defence;                 // 수비 정보
    private List<PlayerDto> player;             // 경기 사용 선수 정보

}
