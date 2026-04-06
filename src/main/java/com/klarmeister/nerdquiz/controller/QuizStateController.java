package com.klarmeister.nerdquiz.controller;

import com.klarmeister.nerdquiz.enums.QuizState;
import com.klarmeister.nerdquiz.model.Frage;
import com.klarmeister.nerdquiz.model.QuizStateMachine;

public class QuizStateController {
    private QuizStateMachine quizStateMachine;
    
    public QuizStateController() {
        quizStateMachine = new QuizStateMachine(QuizState.BOARD,null, null);
    }

    public synchronized  Frage getCurrentFrage() {
        return quizStateMachine.frage();
    }

    public synchronized  QuizState getCurrentQuizState() {
        return quizStateMachine.quizState();
    }

    public synchronized void selectFrage(String kategorieName, Frage frage) {
        quizStateMachine = new QuizStateMachine(QuizState.FRAGE, kategorieName, frage);
    }

    public synchronized void returnToBoard() {
        quizStateMachine = new QuizStateMachine(QuizState.BOARD, null, null);
    }
}
