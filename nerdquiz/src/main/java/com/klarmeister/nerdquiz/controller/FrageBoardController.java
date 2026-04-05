package com.klarmeister.nerdquiz.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.klarmeister.nerdquiz.model.Frage;
import com.klarmeister.nerdquiz.model.FrageBoard;

public class FrageBoardController {

    private FrageBoard frageBoard;
    private String quizDatei = "quizDaten/quiz.json";

    public FrageBoardController() {
        File quizFile = new File(quizDatei);
        try(Scanner quizReader = new Scanner(quizFile)) {
            StringBuilder jsonInhalt = new StringBuilder();
            while (quizReader.hasNextLine()) {
                jsonInhalt.append(quizReader.nextLine());
            }
            frageBoard = new Gson().fromJson(jsonInhalt.toString(), FrageBoard.class);
        } catch (FileNotFoundException e) {
            System.out.println(String.format("Quiz Datei nicht gefunden: %s", quizFile.getAbsolutePath()));
        }
    }

    public FrageBoardController(FrageBoard frageBoard) {
        this.frageBoard = frageBoard;
    }

    public FrageBoard getFrageBoard() {
        return frageBoard;
    }

    public String frageBoard() {
        return new Gson().toJson(frageBoard);
    }

    public String frageNachPunkten(String kategorie, int fragePunkte) {
        for (var kategorieEintrag : frageBoard.kategorien()) {
            if (kategorieEintrag.kategorieName().equals(kategorie)) {
                for (var frage: kategorieEintrag.fragen()) {
                    if (frage.punkte() == fragePunkte) {
                        return new Gson().toJson(frage);
                    }
                }
            }
        }
        return new Gson().toJson(new Frage(0,"Es ist etws schief gelaufen", "Upps", "", "", false));
    }
}
