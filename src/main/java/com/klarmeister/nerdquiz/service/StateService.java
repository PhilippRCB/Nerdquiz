package com.klarmeister.nerdquiz.service;

import com.klarmeister.nerdquiz.controller.QuizStateController;
import com.klarmeister.nerdquiz.controller.TeamController;
import com.klarmeister.nerdquiz.enums.QuizState;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class StateService {
    @Autowired
    private QuizStateController quizStateController;
    @Autowired
    private TeamController teamController;

    @GetMapping("/buzzerActive/")
    @ResponseBody
    public Map<String, Object> buzzerActive(){
        Boolean active = quizStateController.getCurrentQuizState() == QuizState.FRAGE;
        return Map.of("active", active);
    }

    @GetMapping("/quizState/")
    @ResponseBody
    public Map<String, Object> stateChanged(){
        return Map.of("state", quizStateController.getCurrentQuizState().toString());
    }

    @GetMapping("/teamQueued/")
    @ResponseBody
    public Map<String, Object> teamQueued(){
        Boolean teamQueued = teamController.getBuzzedTeams().peek() != null;
        return Map.of("teamQueued", teamQueued);
    }
}
