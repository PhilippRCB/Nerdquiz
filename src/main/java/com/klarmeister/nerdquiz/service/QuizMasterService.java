package com.klarmeister.nerdquiz.service;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.klarmeister.nerdquiz.controller.FrageBoardController;
import com.klarmeister.nerdquiz.controller.QuizStateController;
import com.klarmeister.nerdquiz.controller.TeamController;
import com.klarmeister.nerdquiz.model.Frage;
import com.klarmeister.nerdquiz.model.FrageBoard;

@Controller
public class QuizMasterService {
    @Autowired
    private QuizStateController quizStateController;
    @Autowired
    private FrageBoardController frageBoardController;
    @Autowired
    private TeamController teamController;

    @GetMapping("/quizMaster")
    public String redirectDefault(Model model) {
        return quizMaster(model);
    }

    @GetMapping("/quizMaster/")
    public String quizMaster(Model model) {
        return switch (quizStateController.getCurrentQuizState()) {
            case FRAGE -> {
                model.addAttribute("frage", quizStateController.getCurrentFrage());
                yield "questionMaster";
            }
            default -> {
                model.addAttribute("kategorien", frageBoardController.getFrageBoard().kategorien());
                yield "tabelleMaster";
            }
        };
    }


    @PostMapping("/quizMaster/selectFrage")
    public String frageAuswahl(@RequestParam String kategorieName, @RequestParam int fragePunkte, Model model) {
        Frage frage = frageBoardController.frageNachPunkten(kategorieName, fragePunkte);
        teamController.getBuzzedTeams().clear();
        if (frage.isBeantwortet()) {
            model.addAttribute("kategorien", frageBoardController.getFrageBoard().kategorien());
        }
        else{
            model.addAttribute("kategorieName", kategorieName);
            model.addAttribute("frage", frage);
            quizStateController.selectFrage(kategorieName, frage);
        }
        return "redirect:/quizMaster/";
    }

    @PostMapping("/quizMaster/right")
    public String frageKorrekt(Model model) {
        Frage frage = quizStateController.getCurrentFrage();
        if (!frage.isBeantwortet()) {
            String teamName = teamController.getBuzzedTeams().poll();
            if (teamName != null) {
                teamController.addPunkte(teamName, frage.getPunkte());
                frage.setBeantwortet(true);
            }
        }
        model.addAttribute("kategorieName", quizStateController.getQuizStateMachine().kategorieName());
        model.addAttribute("frage", frage);
        return "redirect:/quizMaster/";
    }

    @PostMapping("/quizMaster/wrong")
    public String frageFalsch(Model model) {
        Frage frage = quizStateController.getCurrentFrage();
        if (!frage.isBeantwortet()) {
            String teamName = teamController.getBuzzedTeams().poll();
            if (teamName != null) {
                teamController.addPunkte(teamName, frage.getPunkte() * -1);
            }
        }
        model.addAttribute("kategorieName", quizStateController.getQuizStateMachine().kategorieName());
        model.addAttribute("frage", frage);
        return "redirect:/quizMaster/";
    }

    @PostMapping("/quizMaster/returnToBoard")
    public String returnToBoard(Model model) {
        quizStateController.returnToBoard();
        model.addAttribute("kategorien", frageBoardController.getFrageBoard().kategorien());
        File quizFile = new File("punktestand.json");
        try (FileOutputStream outputStream = new FileOutputStream(quizFile, false)) {
            outputStream.write(teamController.getTeamStand().getBytes());
        } catch (Exception e) {
            System.out.printf("Fehler beim Schreiben der Punktestandsdatei: %s%n", quizFile.getAbsolutePath());
        }
        return "redirect:/quizMaster/";
    }

    @GetMapping("/quizMaster/upload/")
    public String goToUploader(Model model) {
        return "quizUploader";
    }

    @PostMapping("/quizMaster/upload")
    public String quizUpload(@RequestParam String quizJson, Model model) {
        FrageBoard frageBoard = new Gson().fromJson(quizJson, FrageBoard.class);
        frageBoardController.setFrageBoard(frageBoard);
        return returnToBoard(model);
    }
}
