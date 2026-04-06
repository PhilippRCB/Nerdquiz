package com.klarmeister.nerdquiz.model;

import com.klarmeister.nerdquiz.enums.QuizState;

public record QuizStateMachine(QuizState quizState, String kategorieName, Frage frage) {

}