package com.project.soccer.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Division {
    T800("슈퍼챔피언스", 0),
    T900("챔피언스", 1),
    T1000("슈퍼챌린지", 2),
    T1100("챌린지1", 3),
    T1200("챌린지2", 4),
    T1300("챌린지3", 5),
    T2000("월드클래스1", 6),
    T2100("월드클래스2", 7),
    T2200("월드클래스3", 8),
    T2300("프로1", 9),
    T2400("프로2", 10),
    T2500("프로3", 11),
    T2600("세미프로1", 12),
    T2700("세미프로2", 13),
    T2800("세미프로3", 14),
    T2900("유망주1", 15),
    T3000("유망주2", 16),
    T3100("유망주3", 17);

    private static final String BASE_URL = "https://ssl.nexon.com/s2/game/fo4/obt/rank/large/update_2009/ico_rank";
    private static final String IMAGE_EXTENSION = ".png";
    private static final Map<Integer, Division> BY_INDEX = new HashMap<>();

    static {
        for (Division division : values()) {
            BY_INDEX.put(division.getDivisionIndex(), division);
        }
    }

    private final String label;
    private final int divisionIndex;

    Division(String label, int divisionIndex) {
        this.label = label;
        this.divisionIndex = divisionIndex;
    }

    public String getLabel() {
        return label;
    }

    public String getDivisionImgUrl() {
        return BASE_URL + divisionIndex + IMAGE_EXTENSION;
    }

    public int getDivisionIndex() {
        return divisionIndex;
    }

    public static Division valueOfLabel(String label) {
        return Stream.of(values())
                .filter(division -> division.getLabel().equals(label))
                .findFirst()
                .orElse(null);
    }
}