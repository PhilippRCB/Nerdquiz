package com.klarmeister.nerdquiz.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.klarmeister.nerdquiz.FrageBoardController;
import com.klarmeister.nerdquiz.TeamController;


@Controller
public class QuizTabelleService {

    @Autowired
    private FrageBoardController frageBoard;
    @Autowired
    private TeamController teamController;
    

    @GetMapping("/quizTabelle")
    public String frageBoard(Model model)  {
        model.addAttribute("kategorien", frageBoard.getFrageBoard().kategorien());
        return "quizTabelle"; 
    }


    @GetMapping("/frage/{kategorie}/{frageNummer}")
    public String frage(String kategorie, int frageNummer)  {return frageBoard.frage(kategorie, frageNummer); }

    @GetMapping("/frage/{kategorie}/{frageNummer}/{teamName}/{beantwortet}")
    public void setFrageBeantwortet(String kategorie, String teamName, boolean beantwortet)  {
        teamController.addTeam(teamName);
        // TODO: Frage Abarbeiten
    }
}