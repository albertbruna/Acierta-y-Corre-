package com.albertbc94gmail.acierta_y_corre;

/**
 * Created by Albert on 22/01/2017.
 */

public class Score {
    private int id_h, correctas, fallidas, porcentaje;

    public Score(int id_h, int correctas, int fallidas, int porcentaje) {
        this.id_h = id_h;
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

    public int getId_h() {
        return id_h;
    }
}
