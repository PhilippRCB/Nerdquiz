package com.klarmeister.nerdquiz.model;

import java.util.Map;

public record FrageKategorie(String kategorieName, Map<Integer,Frage> fragen) {

}
