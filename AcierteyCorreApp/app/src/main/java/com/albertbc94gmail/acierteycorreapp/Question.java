package com.albertbc94gmail.acierteycorreapp;

import android.graphics.Bitmap;

import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by Albert on 02/12/2016.
 */

public class Question {
    private String question;
    private String[] distractors;
    private String correctAnswer;
    private static Random r = new Random();
    private Bitmap image;

    public Question(String question, String[] distractors, String correctAnswer, Bitmap image){
        this.question = question;
        this.distractors = distractors;
        this.correctAnswer = correctAnswer;
        this.image = image;
    }

    public String getQuestion(){
        return question;
    }

    public String[] getRespostes(){
        String[] respostes = new String[distractors.length+1];
        respostes[0] = correctAnswer;
        for(int i=0; i<distractors.length; i++) {
            respostes[i+1] = distractors[i];
        }
        return mixAnswers(respostes);
    }

    public Bitmap getImage(){
        return image;
    }

    public boolean isCorrect(String answer){
        return correctAnswer.equals(answer);
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
}
