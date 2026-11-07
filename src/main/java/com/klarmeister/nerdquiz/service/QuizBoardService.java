package com.klarmeister.nerdquiz.service;

import com.klarmeister.nerdquiz.controller.TeamController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.klarmeister.nerdquiz.controller.FrageBoardController;
import com.klarmeister.nerdquiz.controller.QuizStateController;


@Controller
public class QuizBoardService {

    @Autowired
    private FrageBoardController frageBoardController;
    @Autowired
    private QuizStateController quizStateController;
    @Autowired
    private TeamController teamController;
    
    @GetMapping("/quiz")
    public String quiz(Model model)  {
        return frageBoard(model);
    }

    @GetMapping("/quiz/")
    public String frageBoard(Model model)  {
        switch (quizStateController.getCurrentQuizState()) {
            case FRAGE:
                String teamName = teamController.getBuzzedTeams().peek();
                if (teamName == null) {
                    return generiereFrageView(model);
                } 
                return generiereAntwortView(model, teamName);
            case BOARD: 
            default: return generiereQuizTabelleView(model);
        }
    }

    private String generiereAntwortView(Model model, String teamName) {
        model.addAttribute("teamName", teamName);
        return "antwort";
    }

    private String generiereFrageView(Model model) {
        model.addAttribute("frage", quizStateController.getCurrentFrage().toRecord());
        return "quizFrage";
    }


    private String generiereQuizTabelleView(Model model) {
        model.addAttribute("kategorien", frageBoardController.getFrageBoard().kategorien());
        return "quizTabelle"; 
    }
}