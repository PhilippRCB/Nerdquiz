package com.klarmeister.nerdquiz.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klarmeister.nerdquiz.FrageBoardController;
import com.klarmeister.nerdquiz.TeamController;
import com.klarmeister.nerdquiz.model.Frage;

@RestController
public class QuizTabelleService {

    @Autowired
    private FrageBoardController frageBoard;
    @Autowired
    private TeamController teamController;
    

    @GetMapping("/quizTabelle")
    public String frageBoard()  {return frageBoard.frageBoard(); }


    @GetMapping("/frage/{kategorie}/{frageNummer}")
    public String frage(String kategorie, int frageNummer)  {return frageBoard.frage(kategorie, frageNummer); }

    @GetMapping("/frage/{kategorie}/{frageNummer}/{teamName}/{beantwortet}")
    public void setFrageBeantwortet(String kategorie, int frageNummer, String teamName, boolean beantwortet)  {
        teamController.addTeam(teamName);
        if (!beantwortet) {
            teamController.addPunkte(teamName, -frageNummer);
            return;
        }
        teamController.addPunkte(teamName, frageNummer);
        frageBoard.getFrageBoard().kategorien().stream()
            .filter(k -> k.kategorieName().equals(kategorie))
            .findFirst()
            .ifPresent(kategorieEintrag -> {
                Frage frage = kategorieEintrag.fragen().get(frageNummer);
                if (frage != null) {
                    Frage aktualisierteFrage = new Frage(
                        frage.punkte(),
                        frage.frage(),
                        frage.antwort(),
                        frage.bildFrage(),
                        frage.bildAntwort(),
                        true
                    );
                    kategorieEintrag.fragen().put(frageNummer, aktualisierteFrage);
                }
            });
    }
}