package com.klarmeister.nerdquiz.model;

public record Frage(int punkte, String frage, String antwort, String bildFrage, String bildAntwort, boolean beantwortet) {

}
