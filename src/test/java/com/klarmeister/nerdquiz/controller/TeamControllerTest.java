package com.klarmeister.nerdquiz.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class TeamControllerTest {

    private final TeamController teamController = new TeamController();

    @Test
    public void testAddTeam() {
        teamController.addTeam("TeamA");
        teamController.addTeam("TeamB");
        teamController.addTeam("TeamA"); // Adding the same team again should not create duplicates

        String teamStand = teamController.getTeamStand();

        assert teamStand.contains("TeamA");
        assert teamStand.contains("TeamB");
        assert teamStand.split("TeamA").length - 1 == 1; // Ensure TeamA is only added once
        assertFalse (teamStand.contains("TeamC"));
    }

    @Test
    public void testAddPunkte() {
        teamController.addTeam("TeamA");
        teamController.addPunkte("TeamA", 10);

        String teamStand = teamController.getTeamStand();

        assert teamStand.contains("\"TeamA\":10");

        teamController.addPunkte("TeamA", 5);  
        teamController.addPunkte("TeamB", 20);
        teamController.addTeam("TeamC");
        teamController.addPunkte("TeamC", -5);

        teamStand = teamController.getTeamStand();

        assert teamStand.contains("\"TeamA\":15");
        assert teamStand.contains("\"TeamB\":20");
        assert teamStand.contains("\"TeamC\":-5");
    }

}
