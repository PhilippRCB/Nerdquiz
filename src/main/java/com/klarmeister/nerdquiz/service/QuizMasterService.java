package com.klarmeister.nerdquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.klarmeister.nerdquiz.controller.FrageBoardController;
import com.klarmeister.nerdquiz.controller.QuizStateController;
import com.klarmeister.nerdquiz.model.Frage;

public class QuizMasterService {
    @Autowired
    private QuizStateController quizStateController;
    @Autowired
    private FrageBoardController frageBoardController;

    @GetMapping("/quizMaster")
    public String quizMaster(Model model) {
        switch (quizStateController.getCurrentQuizState()) {
            case FRAGE: 
                model.addAttribute("frage", quizStateController.getCurrentFrage());
                return "questionmaster";
            case BOARD:
            default:
                throw new AssertionError();
        }
    }


    @PostMapping("/quizMaster/selectFrage")
    public String frageAuswahl(@RequestParam String kategorieName, @RequestParam int fragePunkte, Model model) {
        Frage frage = frageBoardController.frageNachPunkten(kategorieName, fragePunkte);
        model.addAttribute("kategorieName", kategorieName);
        model.addAttribute("frage", frage);
        quizStateController.selectFrage(kategorieName, frage);
        return "questionmaster";
    }
}
