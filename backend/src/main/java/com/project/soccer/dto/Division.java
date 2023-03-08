package com.project.soccer.dto;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Division {
    T800("슈퍼챔피언스","https://ssl.nexon.com/s2/game/fo4/obt/rank/large/update_2009/ico_rank0.png"),
    T900("챔피언스", "https://ssl.nexon.com/s2/game/fo4/obt/rank/large/update_2009/ico_rank1.png"),
    T1000("슈퍼챌린지", "https://ssl.nexon.com/s2/game/fo4/obt/rank/large/update_2009/ico_rank2.png"),
    T1100("챌린지1", "https://ssl.nexon.com/s2/game/fo4/obt/rank/large/update_2009/ico_rank3.png"),
    T1200("챌린지2", "https://ssl.nexon.com/s2/game/fo4/obt/rank/large/update_2009/ico_rank4.png"),
    T1300("챌린지3", "https://ssl.nexon.com/s2/game/fo4/obt/rank/large/update_2009/ico_rank5.png"),
    T2000("월드클래스1", "https://ssl.nexon.com/s2/game/fo4/obt/rank/large/update_2009/ico_rank6.png"),
    T2100("월드클래스2", "https://ssl.nexon.com/s2/game/fo4/obt/rank/large/update_2009/ico_rank7.png"),
    T2200("월드클래스3", "https://ssl.nexon.com/s2/game/fo4/obt/rank/large/update_2009/ico_rank8.png"),
    T2300("프로1", "https://ssl.nexon.com/s2/game/fo4/obt/rank/large/update_2009/ico_rank9.png"),
    T2400("프로2", "https://ssl.nexon.com/s2/game/fo4/obt/rank/large/update_2009/ico_rank10.png"),
    T2500("프로3", "https://ssl.nexon.com/s2/game/fo4/obt/rank/large/update_2009/ico_rank11.png"),
    T2600("세미프로1", "https://ssl.nexon.com/s2/game/fo4/obt/rank/large/update_2009/ico_rank12.png"),
    T2700("세미프로2", "https://ssl.nexon.com/s2/game/fo4/obt/rank/large/update_2009/ico_rank13.png"),
    T2800("세미프로3", "https://ssl.nexon.com/s2/game/fo4/obt/rank/large/update_2009/ico_rank14.png"),
    T2900("유망주1", "https://ssl.nexon.com/s2/game/fo4/obt/rank/large/update_2009/ico_rank15.png"),
    T3000("유망주2", "https://ssl.nexon.com/s2/game/fo4/obt/rank/large/update_2009/ico_rank16.png"),
    T3100("유망주3", "https://ssl.nexon.com/s2/game/fo4/obt/rank/large/update_2009/ico_rank17.png");


    private final String label;
    private final String divisionImgUrl;

    Division(String label, String divisionImgUrl) {
        this.label = label;
        this.divisionImgUrl = divisionImgUrl;
    }

    public String label() {
        return label;
    }
    public String divisionImgUrl() {
        return divisionImgUrl;
    }
    public static Map<String, String> getAllLabelsAndUrls() {
        return Stream.of(values())
                .collect(Collectors.toMap(Division::label, Division::divisionImgUrl));
    }
    // 캐싱해서 순회를 피해 label 값 추출
    private static final Map<String, Division> BY_LABEL =
            Stream.of(values()).collect(Collectors.toMap(Division::label, e -> e));

    public static Division valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
