package com.project.soccer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchDto {

    private String nickName;    // 닉네임
    private String accessId;    // 유저고유식별자
    private int level;          // 유저레벨

}
