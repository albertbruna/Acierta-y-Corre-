package com.albertbc94gmail.acierta_y_corre;

import android.graphics.Bitmap;

import java.util.Random;

/**
 * Created by Albert on 13/01/2017.
 */

public class Question {

    private String question;
    private String[] respostes;
    private String correctAnswer;
    private static Random r = new Random();
    private Bitmap image;
    private int resposta=-1;

    public int getResposta() {
        return resposta;
    }

    public void setResposta(int resposta) {
        this.resposta = resposta;
    }

    public Question(String question, String[] distractors, String correctAnswer, Bitmap image){
        this.question = question;
        this.respostes = new String[distractors.length+1];
        this.respostes[0] = correctAnswer;
        for(int i=0; i<distractors.length; i++) {
            this.respostes[i+1] = distractors[i];
        }
        this.respostes = mixAnswers(this.respostes);
        this.correctAnswer = correctAnswer;
        this.image = image;
    }

    public String getQuestion(){
        return question;
    }

    public String[] getRespostes(){
        return respostes;
    }

    public Bitmap getImage(){
        return image;
    }

    public boolean isCorrect(){
        return correctAnswer.equals(getRespostes()[this.resposta]);
    }

    private static String[] mixAnswers(String[] answers){
        for(int x=0; x < 5; x++) {
            for (int i = 0; i < answers.length; i++) {
                int j = r.nextInt((answers.length) - i) + i;
                String aux = answers[i];
                answers[i] = answers[j];
                answers[j] = aux;
            }
        }
        return answers;
    }

    @Override
    public String toString() {
        return getQuestion();
    }
}
