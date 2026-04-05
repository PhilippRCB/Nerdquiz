package com.klarmeister.nerdquiz;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.Gson;

public class TeamController {
    private final Map<String, Integer> teams = new ConcurrentHashMap<>();

    public synchronized void addTeam(String teamName) {
        teams.putIfAbsent(teamName, 0);
    }

    public synchronized void addPunkte(String teamName, int punkte) {
        teams.put(teamName, teams.getOrDefault(teamName, 0) + punkte);
    }

    public String getTeamStand() {
        return new Gson().toJson(teams);
    }
}
