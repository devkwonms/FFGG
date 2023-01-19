package com.project.soccer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassDto {

    private Integer passTry;                  // 패스 시도 수
    private Integer passSuccess;              // 패스 성공 수
    private Integer shortPassTry;             // 숏 패스 시도 수
    private Integer shortPassSuccess;         // 숏 패스 성공 수
    private Integer longPassTry;              // 롱 패스 시도 수
    private Integer longPassSuccess;          // 롱 패스 성공 수
    private Integer bouncingLobPassTry;       // 바운싱 롭 패스 시도 수
    private Integer bouncingLobPassSuccess;   // 바운싱 롭 패스 성공 수
    private Integer drivenGroundPassTry;      // 드리븐 땅볼 패스 시도 수 
    private Integer drivenGroundPassSuccess;  // 드리븐 땅볼 패스 성공 수
    private Integer throughPassTry;           // 스루 패스 시도 수
    private Integer throughPassSuccess;       // 스루 패스 성공 수
    private Integer lobbedThroughPassTry;     // 로빙 스루 패스 시도 수
    private Integer lobbedThroughPassSuccess; // 로빙 스루 패스 성공 수
}
