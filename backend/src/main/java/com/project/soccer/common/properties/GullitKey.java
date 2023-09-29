package com.project.soccer.common.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GullitKey {
    @Autowired
    private Environment environment;

    public static String API_KEY;
    public static String FC_URL;
    public static String OWNER_URL;
    public static String MATCH_URL;
    public static String PLAYERS_URL;
    public static String PLAYER_IMG_URL;
    public static String PLAYER_DEFAULT_IMG_URL;

    @PostConstruct
    private void initialize() {

        API_KEY = environment.getProperty("API_KEY");
        FC_URL = environment.getProperty("FC_URL");
        OWNER_URL = environment.getProperty("OWNER_URL");
        MATCH_URL = environment.getProperty("MATCH_URL");
        PLAYERS_URL = environment.getProperty("PLAYERS_URL");
        PLAYER_IMG_URL = environment.getProperty("PLAYER_IMG_URL");
        PLAYER_DEFAULT_IMG_URL = environment.getProperty("PLAYER_DEFAULT_IMG_URL");
    }
}
