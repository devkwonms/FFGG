package com.project.soccer.dto;

public enum MatchType {
    T800("슈퍼챔피언스"),
    T900("챔피언스"),
    T1000("슈퍼챌린지"),
    T1100("챌린지1"),
    T1200("챌린지2"),
    T1300("챌린지3"),
    T2000("월드클래스1"),
    T2100("월드클래스2"),
    T2200("월드클래스3"),
    T2300("프로1"),
    T2400("프로2"),
    T2500("프로3"),
    T2600("세미프로1"),
    T2700("세미프로2"),
    T2800("세미프로3"),
    T2900("유망주1"),
    T3000("유망주2"),
    T3100("유망주3");


    private final String label;

    MatchType(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

}
