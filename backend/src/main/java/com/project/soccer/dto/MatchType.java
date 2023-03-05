package com.project.soccer.dto;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MatchType {
    M30("리그 친선"),
    M40("클래식 1on1"),
    M50("공식경기"),
    M52("감독모드"),
    M60("공식 친선"),
    M204("볼타 친선"),
    M214("볼타 공식"),
    M224("볼타 AI대전"),
    M234("볼타 커스텀");

    private final String label;

    MatchType(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    // 캐싱해서 순회피해 label 값 추출하기
    private static final Map<String, MatchType> BY_LABEL =
            Stream.of(values()).collect(Collectors.toMap(MatchType::label, e -> e));

    public static MatchType valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
