package com.project.soccer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PassDto {

    private int passTry;                  // 패스 시도 수
    private int passSuccess;              // 패스 성공 수
    private int shortPassTry;             // 숏 패스 시도 수
    private int shortPassSuccess;         // 숏 패스 성공 수
    private int longPassTry;              // 롱 패스 시도 수
    private int longPassSuccess;          // 롱 패스 성공 수
    private int bouncingLobPassTry;       // 바운싱 롭 패스 시도 수
    private int bouncingLobPassSuccess;   // 바운싱 롭 패스 성공 수
    private int drivenGroundPassTry;      // 드리븐 땅볼 패스 시도 수 
    private int drivenGroundPassSuccess;  // 드리븐 땅볼 패스 성공 수
    private int throughPassTry;           // 스루 패스 시도 수
    private int throughPassSuccess;       // 스루 패스 성공 수
    private int lobbedThroughPassTry;     // 로빙 스루 패스 시도 수
    private int lobbedThroughPassSuccess; // 로빙 스루 패스 성공 수
}
