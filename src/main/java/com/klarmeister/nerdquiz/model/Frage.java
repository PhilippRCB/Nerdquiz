package com.klarmeister.nerdquiz.model;

public class Frage {
    private final int punkte;
    private final String frage;
    private final String antwort;
    private final String bildFrage;
    private final String bildAntwort;
    private boolean beantwortet;

    public Frage(int punkte, String frage, String antwort, String bildFrage, String bildAntwort, boolean beantwortet) {
        this.punkte = punkte;
        this.frage = frage;
        this.antwort = antwort;
        this.bildFrage = bildFrage;
        this.bildAntwort = bildAntwort;
        this.beantwortet = beantwortet;
    }

    public int getPunkte() {
        return punkte;
    }

    public String getFrage() {
        return frage;
    }

    public String getAntwort() {
        return antwort;
    }

    public String getBildFrage() {
        return bildFrage;
    }

    public String getBildAntwort() {
        return bildAntwort;
    }

    public boolean isBeantwortet() {
        return beantwortet;
    }

    public void setBeantwortet(boolean beantwortet) {
        this.beantwortet = beantwortet;
    }
}
