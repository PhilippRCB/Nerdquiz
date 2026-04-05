package com.klarmeister.nerdquiz;

import java.util.ArrayList;
import java.util.Map;

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
        FrageKategorie kategorie1 = new FrageKategorie("Mathematik",Map.of( 
                100, new Frage(100, "Was ist 2+2?", "4", "", "", false),
                200, new Frage(200,"Was ist 3x3?", "9",  "", "", false)
        ));

        FrageKategorie kategorie2 = new FrageKategorie("Früchte", Map.of(
                100, new Frage(100, "Welche Farbe hat ein Apfel?", "Rot", "", "", false),
                200, new Frage(200, "Welche Farbe hat eine Birne?", "Grün", "", "", false)
        ) );

        frageBoard.kategorien().add(kategorie1);
        frageBoard.kategorien().add(kategorie2);
    }

    @Test
    public void testGetFrageBoard() {
        String json = frageBoardController.frageBoard();
        assert json.contains("Mathematik");
        assert json.contains("Früchte");
    }

    @Test
    public void testGetFrage() {
        String frageJson = frageBoardController.frage("Mathematik", 100);
        assert frageJson.contains("Was ist 2+2?");
        assert frageJson.contains("\"punkte\":100");    
    }

}
