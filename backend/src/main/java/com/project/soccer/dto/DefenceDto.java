package com.project.soccer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DefenceDto {

    private int blockTry;       // 블락 시도 수
    private int blockSuccess;   // 블락 성공 수
    private int tackleTry;      // 태클 시도 수
    private int tackleSuccess;  // 태클 성공 수
}
