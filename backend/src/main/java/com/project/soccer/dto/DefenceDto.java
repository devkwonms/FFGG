package com.project.soccer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DefenceDto {

    private Integer blockTry;       // 블락 시도 수
    private Integer blockSuccess;   // 블락 성공 수
    private Integer tackleTry;      // 태클 시도 수
    private Integer tackleSuccess;  // 태클 성공 수
}
