package com.klarmeister.nerdquiz.model;



public class Frage {
    private final int punkte;
    private final String frage;
    private final String antwort;
    private final String bildFrage;
    private final String bildAntwort;
    private final String audioFrage;
    private boolean beantwortet;

    public record FrageRecord (int punkte, String frage, String antwort, String bildFrage, String bildAntwort, String audioFrage, boolean beantwortet) {}

    public Frage(int punkte, String frage, String antwort, String bildFrage, String bildAntwort, String audioFrage, boolean beantwortet) {
        this.punkte = punkte;
        this.frage = frage;
        this.antwort = antwort;
        this.bildFrage = bildFrage;
        this.bildAntwort = bildAntwort;
        this.audioFrage = audioFrage;
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

    public String getAudioFrage() {
        return audioFrage;
    }

    public boolean isBeantwortet() {
        return beantwortet;
    }

    public void setBeantwortet(boolean beantwortet) {
        this.beantwortet = beantwortet;
    }

    public FrageRecord toRecord() {
        return new FrageRecord(punkte, frage, antwort, bildFrage, bildAntwort, audioFrage, beantwortet);
    }
}
