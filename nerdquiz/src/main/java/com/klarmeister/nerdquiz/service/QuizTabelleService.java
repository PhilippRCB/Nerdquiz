package com.klarmeister.nerdquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.klarmeister.nerdquiz.controller.FrageBoardController;


@Controller
public class QuizTabelleService {

    @Autowired
    private FrageBoardController frageBoard;
    

    @GetMapping("/quizTabelle")
    public String frageBoard(Model model)  {
        model.addAttribute("kategorien", frageBoard.getFrageBoard().kategorien());
        return "quizTabelle"; 
    }
}