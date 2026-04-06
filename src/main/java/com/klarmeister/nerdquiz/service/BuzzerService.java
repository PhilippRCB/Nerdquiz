package com.klarmeister.nerdquiz.service;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.klarmeister.nerdquiz.controller.TeamController;

@Controller
public class BuzzerService {

    @Autowired
    private TeamController teamController;

    @GetMapping("/buzzer/")
    public String buzzer(Model model) {
        model.addAttribute("name", "Gast");
        return "buzzer";
    }

    @PostMapping("/buzzer/buzz")
    public String buzzer(@RequestParam String teamName, Model model) {
        model.addAttribute("name", teamName);
        teamController.addTeam(teamName);
        Queue<String> buzzedTeams = teamController.getBuzzedTeams();
        if (buzzedTeams.contains(teamName)) {
            return "buzzer";
        }
        buzzedTeams.add(teamName);
        return "buzzer";
    }
}
