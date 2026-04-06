package com.klarmeister.nerdquiz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.klarmeister.nerdquiz.controller.TeamController;

@Controller
public class QuizStandingController {
    @Autowired
    private TeamController teamController;

    @GetMapping("/quizStanding/")
    public String quizStanding(Model model) {
        Map<String, Integer> teams = teamController.getTeams();
        List<String> sortedTeamsMitPunkten = new ArrayList<>();
        teams.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).toList().forEach(e -> sortedTeamsMitPunkten.add(e.getKey() + ": " + e.getValue() + " Punkte"));
        model.addAttribute("teams", teamController.getTeams().entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).toList());
        return "quizStandings"; 
    }
}
