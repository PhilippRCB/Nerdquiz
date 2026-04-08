package com.klarmeister.nerdquiz.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.klarmeister.nerdquiz.model.Frage;
import com.klarmeister.nerdquiz.model.FrageBoard;
import com.klarmeister.nerdquiz.model.FrageKategorie;

public class FrageBoardControllerTest {
    private final FrageBoard frageBoard = new FrageBoard(new ArrayList<>());
    private final FrageBoardController frageBoardController = new FrageBoardController(frageBoard);

    @BeforeEach
    public void setup() {
        FrageKategorie kategorie1 = new FrageKategorie("Mathematik",List.of( 
                new Frage(100, "Was ist 2+2?", "4", "", "", false),
                new Frage(200,"Was ist 3x3?", "9",  "", "", false)
        ));

        FrageKategorie kategorie2 = new FrageKategorie("Früchte", List.of(
                new Frage(100, "Welche Farbe hat ein Apfel?", "Rot", "", "", false),
                new Frage(200, "Welche Farbe hat eine Birne?", "Grün", "", "", false)
        ) );

        frageBoard.kategorien().add(kategorie1);
        frageBoard.kategorien().add(kategorie2);
    }

    @Test
    public void testGetFrageBoard() {
        String json = frageBoardController.frageBoardToJson();
        assert json.contains("Mathematik");
        assert json.contains("Früchte");
    }

    @Test
    public void testGetFrage() {
        String frageJson = frageBoardController.frageJsonNachPunkten("Mathematik", 100);
        assert frageJson.contains("Was ist 2+2?");
        assert frageJson.contains("\"punkte\":100");    
    }

    @Test
    public void testKonstruktorMitDatei() {
        FrageBoardController controller = new FrageBoardController();
        String json = controller.frageBoardToJson();
        assert json.contains("Mathe");
        assert json.contains("Obst");
        assert !controller.getFrageBoard().kategorien().isEmpty();
    }

}
