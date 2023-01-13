package com.project.soccer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {

    private String nickName;
    private String accessId;
    private int level;

    private int matchType;
    private int division;
    private String achievementDate;

}
