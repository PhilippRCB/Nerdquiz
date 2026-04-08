package com.klarmeister.nerdquiz.controller;

import com.google.gson.Gson;
import com.klarmeister.nerdquiz.model.Frage;
import com.klarmeister.nerdquiz.model.FrageBoard;

public class FrageBoardController {

    private FrageBoard frageBoard;
    private String quizDatei = "quiz.json";
    private static final String demoQuiz = 
                """
                {
                    "kategorien":
                    [
                        {
                            "kategorieName":"Mathe",
                            "fragen":
                                [
                                    {
                                        "punkte":100,
                                        "frage":"Was ist 2+2?",
                                        "antwort":"4",
                                        "bildFrage":"",
                                        "bildAntwort":"",
                                        "beantwortet":false
                                    },
                                    {
                                        "punkte":200,
                                        "frage":"Was ist 3x3?",
                                        "antwort":"9",
                                        "bildFrage":"",
                                        "bildAntwort":"",
                                        "beantwortet":false
                                    }   
                        ]
                            
                        },
                        {
                            "kategorieName":"Obst",
                            "fragen":
                                [
                                    {
                                        "punkte":100,
                                        "frage":"Welche Farbe hat ein Apfel?",
                                        "antwort":"Rot",
                                        "bildFrage":"https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fimg.icons8.com%2Fsf-black-filled%2F1200%2Fquestion-mark.jpg&f=1&nofb=1&ipt=c37a5de098202fffb3a653d504b7906691c84bc81d4177adea9e9f72968ef4d9",
                                        "bildAntwort":"https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%2Fid%2FOIP.HeNPBZ28l2dAqL1AlQimxwHaE8%3Fpid%3DApi&f=1&ipt=a39ff484612bd3d773a66ba4a5ea4287d765add2ac5587c99e684ab300cacce1&ipo=images",
                                        "beantwortet":false
                                    },
                                    {
                                        "punkte":200,
                                        "frage":"Welche Farbe hat eine Birne?",
                                        "antwort":"Gr\u00fcn",
                                        "bildFrage":"https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fimg.icons8.com%2Fsf-black-filled%2F1200%2Fquestion-mark.jpg&f=1&nofb=1&ipt=c37a5de098202fffb3a653d504b7906691c84bc81d4177adea9e9f72968ef4d9",
                                        "bildAntwort":"https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%2Fid%2FOIP.m_UXxJljlUN0L1Mn1kNo-QHaE8%3Fpid%3DApi&f=1&ipt=280322130865a90b75762bbb4d3e54374c50fc16bda8b3beae2a0395bc34f3d7&ipo=images",
                                        "beantwortet":false
                                    }
                                ]
                        }
                    ]
                }""" 
    ;

    public FrageBoardController() {
        frageBoard = new Gson().fromJson(demoQuiz, FrageBoard.class);
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

    public String frageJsonNachPunkten(String kategorie, int fragePunkte) {
        return new Gson().toJson(frageNachPunkten(kategorie, fragePunkte));
    }

    public Frage frageNachPunkten(String kategorie, int fragePunkte) {
        for (var kategorieEintrag : frageBoard.kategorien()) {
            if (kategorieEintrag.kategorieName().equals(kategorie)) {
                for (var frage: kategorieEintrag.fragen()) {
                    if (frage.getPunkte() == fragePunkte) {
                        return frage;
                    }
                }
            }
        }
        return new Frage(0,"Es ist etws schief gelaufen", "Upps", "", "", false);
    }
}
