package com.albertbc94gmail.acierta_y_corre;

/**
 * Created by Albert on 22/01/2017.
 */

public class Score {
    private int correctas, fallidas, porcentaje;

    public Score(int correctas, int fallidas, int porcentaje) {
        this.correctas = correctas;
        this.fallidas = fallidas;
        this.porcentaje = porcentaje;
    }

    public int getCorrectas() {
        return correctas;
    }

    public int getFallidas() {
        return fallidas;
    }

    public int getPorcentaje() {
        return porcentaje;
    }
}
