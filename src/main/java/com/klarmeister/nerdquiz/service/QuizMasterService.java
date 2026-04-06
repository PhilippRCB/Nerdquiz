package com.klarmeister.nerdquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.klarmeister.nerdquiz.controller.FrageBoardController;
import com.klarmeister.nerdquiz.controller.QuizStateController;
import com.klarmeister.nerdquiz.model.Frage;

@Controller
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
                return "questionMaster";
            case BOARD:
            default:
                model.addAttribute("kategorien", frageBoardController.getFrageBoard().kategorien());
                return "tabelleMaster";
        }
    }


    @PostMapping("/quizMaster/selectFrage")
    public String frageAuswahl(@RequestParam String kategorieName, @RequestParam int fragePunkte, Model model) {
        Frage frage = frageBoardController.frageNachPunkten(kategorieName, fragePunkte);
        model.addAttribute("kategorieName", kategorieName);
        model.addAttribute("frage", frage);
        quizStateController.selectFrage(kategorieName, frage);
        return "questionMaster";
    }

    @PostMapping("/quizMaster/right")
    public String frageKorrekt(Model model) {
        Frage frage = quizStateController.getCurrentFrage();
        frage.setBeantwortet(true);
        model.addAttribute("kategorieName", quizStateController.getQuizStateMachine().kategorieName());
        model.addAttribute("frage", frage);
        return "questionMaster";
    }

    @PostMapping("/quizMaster/returnToBoard")
    public String returnToBoard(Model model) {
        quizStateController.returnToBoard();
        model.addAttribute("kategorien", frageBoardController.getFrageBoard().kategorien());
        return "tabelleMaster";
    }

}
