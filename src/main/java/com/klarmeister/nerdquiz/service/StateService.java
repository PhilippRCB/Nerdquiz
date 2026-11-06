package com.klarmeister.nerdquiz.service;

import com.klarmeister.nerdquiz.controller.QuizStateController;
import com.klarmeister.nerdquiz.enums.QuizState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class StateService {
    @Autowired
    QuizStateController quizStateController;

    @GetMapping("/buzzerActive/")
    @ResponseBody
    public Map<String, Object> buzzerActive(){
        Boolean active = quizStateController.getCurrentQuizState() == QuizState.FRAGE;
        return Map.of("active", active);
    }
}
