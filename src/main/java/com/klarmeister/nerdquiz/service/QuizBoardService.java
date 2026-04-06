package com.klarmeister.nerdquiz.service;

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
    

    @GetMapping("/quizTabelle")
    public String frageBoard(Model model)  {
        switch (quizStateController.getCurrentQuizState()) {
            case FRAGE: return generiereFrageView(model);
            case BOARD: 
            default: return generiereQuizTabelleView(model);

        }
    }

    private String generiereFrageView(Model model) {
        model.addAttribute("frage", quizStateController.getCurrentFrage());
        return "quizFrage";
    }


    private String generiereQuizTabelleView(Model model) {
        model.addAttribute("kategorien", frageBoardController.getFrageBoard().kategorien());
        return "quizTabelle"; 
    }
}