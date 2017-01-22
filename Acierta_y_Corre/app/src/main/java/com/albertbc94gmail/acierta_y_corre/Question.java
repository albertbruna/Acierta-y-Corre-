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
    //private String image;
    private int resposta=-1;
    private int idQuestion;
    private String recurso;


    public int getResposta() {
        return resposta;
    }

    public void setResposta(int resposta) {
        this.resposta = resposta;
    }

    public String getRecurso() {
        return recurso;
    }

    public Question(int idQuestion, String question, String[] distractors, String correctAnswer, String recurso){
        this.idQuestion = idQuestion;
        this.question = question;
        this.recurso = recurso;
        this.respostes = new String[distractors.length+1];
        this.respostes[0] = correctAnswer;
        for(int i=0; i<distractors.length; i++) {
            this.respostes[i+1] = distractors[i];
        }
        this.respostes = mixAnswers(this.respostes);
        this.correctAnswer = correctAnswer;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public String getQuestion(){
        return question;
    }

    public String[] getRespostes(){
        return respostes;
    }

//    public String getImage(){
//        return image;
//    }

    public boolean isCorrect(){
        if(this.resposta==-1) return false;
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
